package com.hashinology.retrofitmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hashinology.retrofitmvvm.data.RetrofitClient
import com.hashinology.retrofitmvvm.models.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel: ViewModel() {
    private var _getMovies: MutableLiveData<Movies> = MutableLiveData()
    var getMovies: LiveData<Movies> = _getMovies
    private var _errorMsg: MutableLiveData<String> = MutableLiveData()
    var errorMsg: LiveData<String> = _errorMsg

    init{
        getMoviesList()
    }

    fun getMoviesList(){
        RetrofitClient.getInstance().getMoviesList().enqueue(object : Callback<Movies?> {
            override fun onResponse(call: Call<Movies?>, response: Response<Movies?>) {
                if(response.isSuccessful){
                    _getMovies.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Movies?>, t: Throwable) {
                _errorMsg.postValue(t.message)
            }
        })
    }
}