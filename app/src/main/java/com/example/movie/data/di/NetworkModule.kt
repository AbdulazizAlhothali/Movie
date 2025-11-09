package com.example.movie.data.di

import com.example.movie.data.di.Constants.AUTHORIZATION_HEADER
import com.example.movie.data.di.Constants.BASE_URL
import com.example.movie.data.di.Constants.CONTENT_TYPE
import com.example.movie.data.di.Constants.READ_TIME_OUT
import com.example.movie.data.di.Constants.TOKEN
import com.example.movie.data.di.Constants.WRITE_TIME_OUT
import com.example.movie.data.remote.ResultCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor { interceptorChain ->
                interceptorChain.proceed(
                    interceptorChain
                        .request()
                        .newBuilder()
                        .header(
                            AUTHORIZATION_HEADER,
                            TOKEN
                        ).build()
                )

            }


        return okHttpBuilder.build()
    }
}