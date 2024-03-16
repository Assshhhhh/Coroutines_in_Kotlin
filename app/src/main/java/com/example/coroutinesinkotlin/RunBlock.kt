package com.example.coroutinesinkotlin

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunBlock {

    private val TAG: String = "KotlinCo"

    fun executeTask() {
        runBlocking {   // Can be better understood in IntelliJ Idea
            launch {
                delay(1000)
                Log.d(TAG, "World")
            }
            Log.d(TAG, "Hello")
        }

    }

}