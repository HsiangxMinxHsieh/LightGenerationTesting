package com.timmy.hiltmvvm.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.timmy.hiltmvvm.api.Data
import com.timmy.hiltmvvm.api.Shop
import com.timmy.hiltmvvm.databinding.ActivityShopBinding
import com.timmy.hiltmvvm.databinding.ActivityShopBranchBinding
import com.timmy.hiltmvvm.databinding.AdapterShopAddressBinding
import com.timmymike.componenttool.BaseActivity
import com.timmymike.componenttool.BaseAdapter
import com.timmymike.logtool.loge
import com.timmymike.logtool.toDataBean
import com.timmymike.logtool.toJson
import com.timmymike.viewtool.clickWithTrigger
import com.timmymike.viewtool.setClickTextColorState


class ShopBranchActivity : BaseActivity<ActivityShopBranchBinding>() {
    companion object {
        const val KEY_SHOP_BRANCH = "KEY_SHOP_BRANCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        loge("ShopBranchActivity 收到的資料是=>${intent.extras?.getString(KEY_SHOP_BRANCH).toDataBean(Data::class.java)}")
    }

    private fun initView() = binding.run {
        val dataShow = intent.extras?.getString(KEY_SHOP_BRANCH).toDataBean(Data::class.java) ?: return@run
        title = "${dataShow.shopName}的分店列表"

        rvShopBranch.run {

            adapter = BaseAdapter.create<AdapterShopAddressBinding, Shop>(AdapterShopAddressBinding::inflate) {
                val showData = it
                tvShopName.text = showData.name
                tvShopAddress.text = showData.address

                tvShopName.run {
//                    setClickTextColorState(Color.RED)
                    clickWithTrigger {
                        startActivity(Intent(this@ShopBranchActivity, PhotoActivity::class.java).apply {
                            putExtra(PhotoActivity.KEY_SHOP, showData.toJson())
                        })
                    }
                }
            }.apply {
                submitList(dataShow.shops)
            }

        }

    }
}