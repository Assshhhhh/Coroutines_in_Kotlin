package com.example.coroutinesinkotlin.CoroutineClasses


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG: String = "KotlinCo"

    init {

        viewModelScope.launch {
            while (true){
                delay(500)
                Log.d(TAG, "Kotlin Coroutines")
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "View Model Destroyed")
    }

}