package com.aaron.walkcore.data.container

import com.aaron.walkcore.data.repository.NetworkWalkcoreRepository
import com.aaron.walkcore.data.repository.WalkcoreRepository
import com.aaron.walkcore.data.service.WalkcoreApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val walkcoreRepository: WalkcoreRepository
}

class WalkcoreContainer : AppContainer {
    // Set your backend URL here
    private val baseUrl = "http://10.0.2.2:3000/walkcore-backend/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: WalkcoreApiService by lazy {
        retrofit.create(WalkcoreApiService::class.java)
    }

    // Singleton repository instance
    override val walkcoreRepository: WalkcoreRepository by lazy {
        NetworkWalkcoreRepository(retrofitService)
    }
}