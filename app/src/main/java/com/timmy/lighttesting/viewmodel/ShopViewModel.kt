package com.timmy.lighttesting.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val shopRepository: ShopRepository) : ViewModel() {

    fun getData() {
        shopRepository.init()
        shopRepository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = shopRepository.getLiveDataInRealm()

}