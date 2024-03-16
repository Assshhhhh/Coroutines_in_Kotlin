package com.example.coroutinesinkotlin.CoroutineClasses

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineBuilders {

    private val TAG: String = "KotlinCo"

    // Another method for async
    suspend fun printFollowers(){
        CoroutineScope(Dispatchers.IO).launch {
            var fb = async { getFbFollowers() }
            var insta = async { getInstaFollowers() }

            //Takes 2 seconds for execution, because it will wait for 1 second for first suspend function "getFbFollowers()" to complete then 1 ssecond for the next..
            // Log.d(TAG, "printFollowers() -> FB: $fb, Insta: $insta")

            // Takes only 1 second for execution
            Log.d(TAG, "async() -> printFollowers() -> FB: ${fb.await()}, Insta: ${insta.await()}")
        }
    }

    suspend fun launchCoroutineBuilder(){
        printFbFollowers()
    }

    suspend fun asyncCoroutineBuilder() {
        printInstaFollowers()
    }

    private suspend fun printFbFollowers() {
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }
        //job.cancel()  // To cancel our coroutine
        job.join()    // Keeps the coroutine in waiting/suspended state till the coroutine is completed without blocking the thread
        Log.d(TAG, "launch() -> printFbFollowers() -> FB Followers: $fbFollowers")
    }

    private suspend fun printInstaFollowers() {
        val job = CoroutineScope(Dispatchers.IO).async {
            getInstaFollowers()
        }
        //Log.d(TAG, "Insta Followers: ${job.await()}")

        // If we had to call multiple jobs and wait for their completion then in case of launch() we had to use job1.join(), job2.join()... jobN.join()
        val job2 = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        Log.d(TAG, "async() -> printInstaFollowers() -> Insta Followers: ${job.await()}, Fb Followers: ${job2.await()}")


    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }

    private suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 113
    }

}