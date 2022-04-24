package com;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.egpaid.DeviceInfoUtils;
import com.egpaid.GetImeiDevice;
import com.egpaid.employeeapp.R;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private static final int CREDENTIAL_PICKER_REQUEST = 1;
    private TextView textView;
    private static final int REQUEST_CODE = 101;
    TelephonyManager telephonyManager;
    GetImeiDevice getimei;
    SubscriptionManager mSubscriptionManager;

    public static boolean isMultiSimEnabled = false;
    public static String defaultSimName;

    public static List<SubscriptionInfo> subInfoList;
    public static ArrayList<String> Numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_items_layout);
        textView = findViewById(R.id.editText);
        getimei = new GetImeiDevice(this);
        Numbers = new ArrayList<String>();
        mSubscriptionManager = SubscriptionManager.from(this);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PRECISE_PHONE_STATE}, REQUEST_CODE);


        } else {
            subInfoList = mSubscriptionManager.getActiveSubscriptionInfoList();
            GetCarriorsInformation();
            String textData = "Phone Number:::  " + telephonyManager.getLine1Number() + "\n" +
                    "GetIMSI:::  " + DeviceInfoUtils.getIMSI(this) + "\n" +
                    "GetICCID:::   " + DeviceInfoUtils.getICCID(this) + "\n" +
                    "getAndroidId:::  " + DeviceInfoUtils.getAndroidId(this)+"\n"+
                    "GenerateIMEI::: "+ DeviceInfoUtils.generateIMEI(this);

            textView.setText(textData);

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
//                SubscriptionManager subscriptionManager = SubscriptionManager.from(getApplicationContext());
//                List<SubscriptionInfo> subsInfoList = subscriptionManager.getActiveSubscriptionInfoList();
//
//                Log.d("Test", "Current list = " + subsInfoList);
//
//                for (SubscriptionInfo subscriptionInfo : subsInfoList) {
//
//                    String number = subscriptionInfo.getNumber();
//
//                    Log.d("Test", " Number is  " + number);
//                }
//            }

        }


    }
    private void GetCarriorsInformation() {
        if (subInfoList.size() > 1) {
            isMultiSimEnabled = true;
        }
        for (SubscriptionInfo subscriptionInfo : subInfoList) {
            Numbers.add(subscriptionInfo.getNumber());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    String textData = "Phone Number:::  " + telephonyManager.getLine1Number() + "\n" +
                            "GetIMSI:::  " + DeviceInfoUtils.getIMSI(this) + "\n" +
                            "GetICCID:::   " + DeviceInfoUtils.getICCID(this) + "\n" +
                            "getAndroidId:::  " + DeviceInfoUtils.getAndroidId(this);

                    textView.setText(textData);
                    return;
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == RESULT_OK) {
            // Obtain the phone number from the result
            Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);
           // editText.setText(credentials.getId().substring(3)); //get the selected phone number
//Do what ever you want to do with your selected phone number here


        } else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE) {
            // *** No phone numbers available ***
            Toast.makeText(this, "No phone numbers found", Toast.LENGTH_LONG).show();
        }


    }
}
