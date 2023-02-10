package com.timmy.hiltmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.timmy.hiltmvvm.R
import com.timmy.hiltmvvm.databinding.ActivitySampleBinding
import com.timmy.hiltmvvm.viewmodel.SampleViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
//    private val viewModel: SampleViewModel by lazy { ViewModelProvider(this).get(SampleViewModel::class.java) }

    private val viewModel: SampleViewModel by viewModels()


    private lateinit var mBinding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        mBinding.lifecycleOwner = this
        mBinding.vm = viewModel
        initData()

        viewModel.getLiveDataInRealm().observe(this) {
            Timber.d("結果是=>$it")
        }
    }


    private fun initData() {
        viewModel.getData()
    }
}