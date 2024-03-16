package com.example.coroutinesinkotlin

import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CoroutineJobs {

    private val TAG: String = "KotlinCo"

    suspend fun execute(){
        val parentJob = GlobalScope.launch(Dispatchers.Main) {

            Log.d(TAG, "Parent Started")    // Output: 1 ->

            val childJob = launch {

                Log.d(TAG, "Child Job Started")     // Output: 2 ->
                delay(5000)
                Log.d(TAG, "Child Job Ended")   // Output: 4 ->

/*

                // Scenario 4
                try {
                    Log.d(TAG, "Child Job Started")     // Output: 2 ->
                    delay(5000)
                    Log.d(TAG, "Child Job Ended")   // Output: 4 ->
                }catch (e: CancellationException){
                    Log.d(TAG, "Child Job Cancelled")
                }
*/

            }

            delay(3000)

            // Uncomment for Scenario 3's implementation
            childJob.cancel()
            //Log.d(TAG, "Child Job Cancelled")   // Scenario 4 -> Move this Log statement to try-catch

            Log.d(TAG, "Parent ended")  // Output: 3 ->

        }
/*

        // Scenario 1
        parentJob.join()    // Waits for all the child jobs to complete
        Log.d(TAG, "Parent Completed")  // Output: 5 ->
*/

/*
        // Scenario 2
        delay(1000)
        parentJob.cancel()  // Parent cancelled before the child's jobs execution
        parentJob.join()    // Waits for all the child jobs to complete
        Log.d(TAG, "Parent Completed")  // Output: 5 ->
*/

        // Scenario 3
        parentJob.join()    // Waits for all the child jobs to complete
        Log.d(TAG, "Parent Completed")  // Output: 5 ->

    }

    suspend fun executeLongRunningTask(){

        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000){
                if (isActive) {
                    longRunningTask()
                    Log.d(TAG, i.toString())
                }
            }
        }
        delay(100)
        parentJob.cancel()
        Log.d(TAG, "Cancelling Job..")
        parentJob.join()
        Log.d(TAG, "Parent Completed")

    }

    private fun longRunningTask(){
        for (i in 1..10000000){

        }
    }

}