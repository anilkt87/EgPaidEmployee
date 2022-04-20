package com.egpaid.employeeapp.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetAppCategory extends AsyncTask<String, Void, String> {
     public static final Map<String,String> categoryMap = new HashMap<>();

    //Main URL for each app on Play Store
    public static final String APP_URL = "https://play.google.com/store/apps/details?id=";

    //Use below String if extracting 'CATEGORY' from href tag.
    private final String CATEGORY_STRING = "category/";

    private final int cat_size = 9;

    /*Use below String for identified 'GAME' apps, which must start with this prefix.
    Here I only display 'Games' as category for all Games app instead of showing their subCategory also*/
    private final String CATEGORY_GAME_STRING = "GAME_";

    //Name of the app package for which you want to get category.
    private String packageName = null;

    private PackageManager pm = null;

    //Activity or Application context as per requirement.
    private Context appContext;

    /* You can add default system app OR non play store app package name here as comma seprated for ignore them
     and set their category directly 'Others' OR anythings you wish. */
    private final String extractionApps = "com.android.providers.downloads.ui, com.android.contacts," +
            " com.android.gallery3d, com.android.vending, com.android.calculator2, com.android.calculator," +
            " com.android.deskclock, com.android.messaging, com.android.settings, com.android.stk";

    //Class level TAG, use for Logging.
    private final String TAG = "GetAppCategory";

    /**
     * @param packageName: package name of the app, you want to extract category.
     * @param appContext:  Activity/Application level Context ap per requirement.
     */
    public GetAppCategory(String packageName, Context appContext) {
        this.packageName = packageName;
        this.appContext = appContext;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            pm = appContext.getPackageManager();

            if (packageName != null && packageName.length() > 1) {

                if (packageName.contains("package:")) {
                    packageName = packageName.replace("package:", "");
                }
                /**
                 * Mathod used for parse play store html page and extract category from their.
                 */
                String appCategoryType = parseAndExtractCategory(packageName);

                Log.i(TAG, "package :" + packageName);
                Log.i(TAG, "APP_CATEGORY: " + appCategoryType);
                categoryMap.put(packageName,appCategoryType);
            }

        } catch (Exception e) {
            //TODO:: Handle Exception
            e.printStackTrace();
        } finally {
            //TODO::
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }


    /**
     * @param packageName
     * @return
     */

    public String parseAndExtractCategory(String packageName) {

        //You can pass hl={language_code} for get category in some other langauage also other than English.
        //String url = APP_URL + packageName + "&hl=" + appContext.getString(R.string.app_lang);

        String url = APP_URL + packageName + "&hl=en"; //{https://play.google.com/store/apps/details?id=com.example.app&hl=en}
        String appCategoryType = null;
        String appName = null;

        try {

            if (!extractionApps.contains(packageName)) {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();

                    if (doc != null) {

                        //TODO: START_METHOD_1
                        //Extract category String from a <anchor> tag value directly.
                        //NOTE: its return sub category text, for apps with multiple sub category.
                        //Comment this method {METHOD_1}, if you wish to extract category by href value.
                        Element CATEGORY_SUB_CATEGORY = doc.select("a[itemprop=genre]").first();
                        if (CATEGORY_SUB_CATEGORY != null) {
                            appCategoryType = CATEGORY_SUB_CATEGORY.text();
                        }
                        //TODO: END_METHOD_1

                        //TODO: START_METHOD_2
                        // Use below code only if you wist to extract category by href value.
                        //Its return parent or Main Category Text for all app.
                        //Comment this method {METHOD_2}, If you wihs to extract category from a<anchor> value.
                        if (appCategoryType == null || appCategoryType.length() < 1) {
                            Elements text = doc.select("a[itemprop=genre]");

                            if (text != null) {
                                if (appCategoryType == null || appCategoryType.length() < 2) {
                                    String href = text.attr("abs:href");
                                    if (href != null && href.length() > 4 && href.contains(CATEGORY_STRING)) {
                                        appCategoryType = getCategoryTypeByHref(href);
                                    }
                                }
                            }
                        }
                        //TODO: END_METHOD_2

                        if (appCategoryType != null && appCategoryType.length() > 1) {
                            /**
                             * Ger formatted category String by removing special character.
                             */
                            appCategoryType = replaceSpecialCharacter(appCategoryType);
                        }

                    }
                } catch (IOException e) {
                    //appCategoryType = appContext.getString(R.string.category_others);
                    appCategoryType = "OTHERS";
                    //TODO:: Handle Exception
                    e.printStackTrace();
                }
            } else {
                //appCategoryType = appContext.getString(R.string.category_others);
                appCategoryType = "OTHERS";
            }
        } catch (Exception e) {
            //TODO:: Handle Exception
            e.printStackTrace();
        }
        return appCategoryType;
    }

    /**
     * @param href
     * @return
     */
    private String getCategoryTypeByHref(String href) {
        String appCategoryType = null;
        try {
            appCategoryType = href.substring((href.indexOf(CATEGORY_STRING) + cat_size), href.length());
            if (appCategoryType != null && appCategoryType.length() > 1) {
                if (appCategoryType.contains(CATEGORY_GAME_STRING)) {
                    //appCategoryType = appContext.getString(R.string.games);
                    appCategoryType = "GAMES";
                }
            }
        } catch (Exception e) {
            //TODO:: Handle Exception
            e.printStackTrace();
        }
        return appCategoryType;
    }

    /**
     * @param appCategoryType
     * @return: formatted String
     */
    private String replaceSpecialCharacter(String appCategoryType) {
        try {
            //Find and Replace '&amp;' with '&' in category Text
            if (appCategoryType.contains("&amp;")) {
                appCategoryType = appCategoryType.replace("&amp;", " & ");
            }

            //Find and Replace '_AND_' with ' & ' in category Text
            if (appCategoryType.contains("_AND_")) {
                appCategoryType = appCategoryType.replace("_AND_", " & ");
            }

            //Find and Replace '_' with ' ' <space> in category Text
            if (appCategoryType.contains("_")) {
                appCategoryType = appCategoryType.replace("_", " ");
            }
        } catch (Exception e) {
            //TODO:: Handle Exception
            e.printStackTrace();
        }
        return appCategoryType;
    }
}