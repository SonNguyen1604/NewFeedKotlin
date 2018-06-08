package com.example.nguyenngocsonc.myapplication.interfaces

import android.content.Context
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.models.DataFeed
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

interface NewFeedServiceInterface {

    companion object {
        fun create(context: Context): NewFeedServiceInterface {

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .client(getUnsafeOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(context.getString(R.string.server_url))
                    .build()
            return retrofit.create(NewFeedServiceInterface::class.java)
        }
    }

    @GET("v2/5b0ce0cb3300005100b400f1/")
    fun queryNewFeed(): Observable<DataFeed>
}

private fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
        var trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }

        return builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
