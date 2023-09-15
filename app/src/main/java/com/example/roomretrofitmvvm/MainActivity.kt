package com.example.roomretrofitmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomretrofitmvvm.api.QuotesApi
import com.example.roomretrofitmvvm.api.RetrofitHelper
import com.example.roomretrofitmvvm.repository.QuoteRepository
import com.example.roomretrofitmvvm.viewmodel.MainViewModel
import com.example.roomretrofitmvvm.viewmodel.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository


        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("abc",it.toString())
        })
    }
}