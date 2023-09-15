package com.example.roomretrofitmvvm

import android.app.Application
import com.example.roomretrofitmvvm.api.QuotesApi
import com.example.roomretrofitmvvm.api.RetrofitHelper
import com.example.roomretrofitmvvm.db.QuoteDatabase
import com.example.roomretrofitmvvm.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }


    private fun initialize() {

        val quoteService = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
       quoteRepository = QuoteRepository(quoteService,database,applicationContext)



    }
}


