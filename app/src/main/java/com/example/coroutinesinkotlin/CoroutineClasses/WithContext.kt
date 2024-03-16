package com.example.coroutinesinkotlin.CoroutineClasses

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class WithContext {

    private val TAG: String = "KotlinCo"

    suspend fun executeTask(){
        Log.d(TAG, "Before")
        withContext(Dispatchers.IO) {   // Suspends till the coroutine is executed (for 1s), if launched coroutine normally that would not have suspended
            delay(1000)
            Log.d(TAG, "Inside")
        }
        Log.d(TAG, "After")
    }

}