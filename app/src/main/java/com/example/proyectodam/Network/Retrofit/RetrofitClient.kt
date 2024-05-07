package com.example.proyectodam.Network.Retrofit

import com.example.proyectodam.Network.MetodosAPI.FichajeApi
import com.example.proyectodam.Network.MetodosAPI.NominaAPI
import com.example.proyectodam.Network.MetodosAPI.ProyectoApi
import com.example.proyectodam.Network.MetodosAPI.UsuarioAPI
import com.example.proyectodam.utils.ConnectionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Singleton
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ConnectionUtils.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getUsuarioService(): UsuarioAPI {
        return retrofit.create(UsuarioAPI::class.java)
    }

    @Singleton
    @Provides
    fun getProyectoService(): ProyectoApi {
        return retrofit.create(ProyectoApi::class.java)
    }

    @Singleton
    @Provides
    fun getFichajeService(): FichajeApi {
        return retrofit.create(FichajeApi::class.java)
    }

    @Singleton
    @Provides
    fun getNominaService(): NominaAPI {
        return retrofit.create(NominaAPI::class.java)
    }
}