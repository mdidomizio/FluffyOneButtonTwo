package com.example.flyffykitty04

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("v1/images/search?format=json")
    fun getRandomPic(): Call<List<PhotoGalleryClass>>
}