package com.hashinology.retrofitmvvm.data

import com.hashinology.retrofitmvvm.models.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitClient {
    @GET("movielist.json")
    fun getMoviesList(): Call<Movies>

    companion object{
        var instance: RetrofitClient? = null

        @JvmName("getInstance1")
        fun getInstance(): RetrofitClient{
            if(instance == null){
                var retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

                instance = retrofit.create(RetrofitClient::class.java)
            }
            return instance!!
        }
    }

}