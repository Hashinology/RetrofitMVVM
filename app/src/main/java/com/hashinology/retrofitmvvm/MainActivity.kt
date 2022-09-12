package com.hashinology.retrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hashinology.retrofitmvvm.adapter.MoviesAdapter
import com.hashinology.retrofitmvvm.databinding.ActivityMainBinding
import com.hashinology.retrofitmvvm.models.Movies
import com.hashinology.retrofitmvvm.models.MoviesItem
import com.hashinology.retrofitmvvm.ui.MoviesViewModel

class MainActivity : AppCompatActivity() {
    lateinit var movieViewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding
//    lateinit var movies: Movies
    lateinit var movieAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        movieViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        movieViewModel.getMovies.observe(this, Observer {
//            movies = Movies()
//            movies = it
            setRecyclerView(it)
        })

        movieViewModel.errorMsg.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })
    }

    private fun setRecyclerView(film: Movies?) {
        movieAdapter = MoviesAdapter(film!!, this)

        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }
}