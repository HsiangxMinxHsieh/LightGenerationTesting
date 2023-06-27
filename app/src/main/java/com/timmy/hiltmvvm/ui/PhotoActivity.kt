package com.timmy.hiltmvvm.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.timmy.hiltmvvm.api.Data
import com.timmy.hiltmvvm.api.Photos
import com.timmy.hiltmvvm.api.Shop
import com.timmy.hiltmvvm.databinding.ActivityPhotoBinding
import com.timmy.hiltmvvm.databinding.AdapterPhotoBinding
import com.timmy.hiltmvvm.databinding.AdapterShopAddressBinding
import com.timmymike.componenttool.BaseActivity
import com.timmymike.componenttool.BaseAdapter
import com.timmymike.logtool.loge
import com.timmymike.logtool.toDataBean
import com.timmymike.viewtool.getScreenWidthPixels
import com.timmymike.viewtool.setClickBgState

class PhotoActivity : BaseActivity<ActivityPhotoBinding>() {
    companion object {
        const val KEY_SHOP = "KEY_SHOP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        loge("PhotoActivity 收到的資料是=>${intent.extras?.getString(KEY_SHOP).toDataBean(Shop::class.java)}")

    }

    private fun initView() = binding.run {
        val photos = intent.extras?.getString(KEY_SHOP).toDataBean(Shop::class.java)?: return@run
        title = "${photos.name}的照片"
        val itemCount = 2
        rvPhoto.run {
            layoutManager = GridLayoutManager(this@PhotoActivity, itemCount)
            adapter = BaseAdapter.create<AdapterPhotoBinding, String>(AdapterPhotoBinding::inflate) {
                Glide.with(this@PhotoActivity)
                    .load(it)
                    .centerCrop()
                    .into(this.ivPhoto)

                ivPhoto.run {
                    layoutParams = RecyclerView.LayoutParams(getScreenWidthPixels()/itemCount,getScreenWidthPixels()/itemCount)
                }
            }.apply {
                submitList(photos.photos.url)
            }
        }
    }
}