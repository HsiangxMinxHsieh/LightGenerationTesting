package com.timmy.hiltmvvm.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.timmy.hiltmvvm.api.Data
import com.timmy.hiltmvvm.databinding.ActivityShopBinding
import com.timmy.hiltmvvm.databinding.AdapterShopBinding
import com.timmy.hiltmvvm.viewmodel.ShopViewModel
import com.timmymike.componenttool.BaseActivity
import com.timmymike.componenttool.BaseAdapter
import com.timmymike.logtool.*
import com.timmymike.viewtool.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopActivity : BaseActivity<ActivityShopBinding>() {

    private val viewModel: ShopViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        title = "光時代Android入職考試"
        showDialogLoading()
        viewModel.getLiveDataInRealm().observe(this) {
            hideDialogLoading()
            initialRecycleViewByData(it.data)
            loge("ShopActivity 收到的資料是=>${it.toJson()}")
        }
    }

    private fun initialRecycleViewByData(dataFromAPI: List<Data>) {
        binding.rvShop.run {
            adapter = BaseAdapter.create<AdapterShopBinding, Data>(AdapterShopBinding::inflate) {
                val showData = it
                tvShopName.text = showData.shopName
                tvShopName.run {
                    setClickTextColorState(Color.BLUE,Color.RED)
                }
                tvShopName.clickWithTrigger {
                    startActivity(Intent(this@ShopActivity,ShopBranchActivity::class.java).apply{
                        putExtra(ShopBranchActivity.KEY_SHOP_BRANCH, showData.toJson())
                    })

                }
            }.apply {
                submitList(dataFromAPI)
            }
        }
    }

    private fun initData() {
        viewModel.getData()
    }
}