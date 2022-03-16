package com.timmy.hiltmvvm.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

    fun getData() {
        sampleRepository.init()
        sampleRepository.getDataFromAPI()
    }

    fun getLiveDataInRealm() = sampleRepository.getLiveDataInRealm()

}