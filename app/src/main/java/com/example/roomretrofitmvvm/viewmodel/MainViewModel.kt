package com.example.roomretrofitmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofitmvvm.Models.QuoteList
import com.example.roomretrofitmvvm.repository.QuoteRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository)  : ViewModel(){

    init{
        viewModelScope.launch {
            repository.getQuotes(1)
        }
    }

    val quotes : LiveData<QuoteList>
        get() = repository.quotes

}