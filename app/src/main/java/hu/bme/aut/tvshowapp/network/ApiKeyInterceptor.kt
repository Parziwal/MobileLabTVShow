package hu.bme.aut.tvshowapp.network

import android.util.Log
import hu.bme.aut.tvshowapp.Config
import hu.bme.aut.tvshowapp.Config.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request)
    }
}