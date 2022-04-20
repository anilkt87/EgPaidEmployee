package com.egpaid.employeeapp.base.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import com.egpaid.employeeapp.base.di.scope.PerApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module(includes = [ApplicationModule::class])
class NetworkModule {
    @Provides
    @PerApplication
    fun providesRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://console.ezyone.in/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @PerApplication
    fun providesOkHttpClient(context: Context, isNetworkAvailable: Boolean): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .cache(mCache) // make your app offline-friendly without a database!
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    /* If there is Internet, get the cache that was stored 5 seconds ago.
                     * If the cache is older than 5 seconds, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-age' attribute is responsible for this behavior.
                     */
                    request = if (isNetworkAvailable) request.newBuilder().header("Cache-Control", "public, max-age=" + 5).header("apikey","mCH9hxghsSHVmgYDYhVKZJt2bsxJLN").build()
                    /*If there is no Internet, get the cache that was stored 7 days ago.
                     * If the cache is older than 7 days, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-stale' attribute is responsible for this behavior.
                     * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    else request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    chain.proceed(request)
                }
        return client.build()
    }


    @Provides
    @PerApplication
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @PerApplication
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @PerApplication
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @PerApplication
    fun provideIsNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
    @Provides
    @PerApplication
    fun provideConnectvityManagerAvailable(context: Context): ConnectivityManager {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager
    }


    @PerApplication
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}