package com.timmy.hiltmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.timmy.hiltmvvm.api.ApiService
import com.timmy.hiltmvvm.api.SampleDataFromAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class ShopRepository @Inject constructor() {

    @Inject
    lateinit var retrofit: Retrofit


    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val result by lazy { MutableLiveData<SampleDataFromAPI>() }

    fun getLiveDataInRealm() = result

    fun init() { // 初始化，用於偵測資料庫是否有變化，或直接回傳值
    }

    fun getDataFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val responseBody = apiService.getData()
            result.postValue(responseBody)
        }
    }


}
