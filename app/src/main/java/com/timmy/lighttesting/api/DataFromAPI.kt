package com.timmy.lighttesting.api

import com.google.gson.annotations.SerializedName


data class SampleDataFromAPI(
    @SerializedName("data")
    val `data`: List<Data>
)

data class Data(
    @SerializedName("shopName")
    val shopName: String,
    @SerializedName("shops")
    val shops: List<Shop>
)

data class Shop(
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("photos")
    val photos: Photos
)

data class Photos(
    @SerializedName("url")
    val url: List<String>
)

