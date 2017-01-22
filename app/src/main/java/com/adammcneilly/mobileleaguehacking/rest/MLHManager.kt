package com.adammcneilly.mobileleaguehacking.rest

import com.adammcneilly.mobileleaguehacking.BuildConfig
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Manager for performing service calls.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class MLHManager {
    /**
     * Interceptor that will log Retrofit calls to the console for us.
     */
    private val loggingInterceptor = HttpLoggingInterceptor()

    /**
     * The service interface used to make calls to the API.
     */
    val service: MLHService

    init {
        if (BuildConfig.DEBUG) loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://fa997472.ngrok.io/")
                .client(client)
                .build()

        service = retrofit.create(MLHService::class.java)
    }

    /**
     * Retrieve a list of all hackathons from the JSON file.
     */
    fun getHackathons(region: String): Observable<List<Hackathon>> {
        return service.getHackathons(region)
    }
}