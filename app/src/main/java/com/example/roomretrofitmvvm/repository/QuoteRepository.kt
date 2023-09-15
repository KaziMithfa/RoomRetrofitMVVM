package com.example.roomretrofitmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomretrofitmvvm.Models.QuoteList
import com.example.roomretrofitmvvm.Utils.NetworkUtils
import com.example.roomretrofitmvvm.api.QuotesApi
import com.example.roomretrofitmvvm.db.QuoteDatabase

class QuoteRepository(
    private val quoteService: QuotesApi,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData



    suspend fun getQuotes(page: Int) {


        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteService.getQuotes(page)

            if (result != null && result.body() != null) {

                quoteDatabase.quoteDao().addQuote(result.body()!!.results)

                quotesLiveData.postValue(result.body())

            }
        }
        else{

            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quotelist = QuoteList(1,1,1,quotes,1,1);
            quotesLiveData.postValue(quotelist)

        }


    }
}