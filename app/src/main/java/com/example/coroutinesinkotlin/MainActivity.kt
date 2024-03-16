package com.example.coroutinesinkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coroutinesinkotlin.CoroutineClasses.CoroutineBuilders
import com.example.coroutinesinkotlin.CoroutineClasses.CoroutineJobs
import com.example.coroutinesinkotlin.CoroutineClasses.RunBlock
import com.example.coroutinesinkotlin.CoroutineClasses.WithContext
import com.example.coroutinesinkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

private lateinit var binding: ActivityMainBinding

    private val TAG: String = "KotlinCo"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "${Thread.currentThread().name}")

        val coroutineBuilders = CoroutineBuilders()
        val coroutineJobs = CoroutineJobs()
        val withContext = WithContext()
        val runBlock = RunBlock()

        // Counter Updation
        binding.buttonUpdateCounter.setOnClickListener {

            Log.d(TAG, "${Thread.currentThread().name}")
            binding.tvCounter.text = "${binding.tvCounter.text.toString().toInt() + 1}"

        }

        // View Model Activity
        binding.buttonMoveToNextActivity.setOnClickListener {
            finish()
            startActivity(Intent(this, LifecycleAndViewModelScopeActivity::class.java))
        }

        // Coroutine Scope, Context and Dispatchers
        binding.buttonExecuteTask.setOnClickListener {

            // Coroutine Scope, Context and Dispatchers
            CoroutineScope(Dispatchers.IO).launch {
                Log.d(TAG, "${Thread.currentThread().name}")
                executeLongTask()
            }

            GlobalScope.launch(Dispatchers.Main) {
                Log.d(TAG, "${Thread.currentThread().name}")
            }

            MainScope().launch(Dispatchers.Default) {
                Log.d(TAG, "${Thread.currentThread().name}")
            }

            /*

                        // Before we used Threads like this
                        thread(start = true){
                            executeLongTask()
                        }
            */

        }

        //Suspending function
        CoroutineScope(Dispatchers.Main).launch {
            Task1()
        }

        //Suspending function
        CoroutineScope(Dispatchers.Main).launch {
            Task2()
        }

        // Coroutine Builders
        CoroutineScope(Dispatchers.IO).launch {
            // launch() // When you don't care about the result (Fire & Forget)
            coroutineBuilders.launchCoroutineBuilder()
            // async()  // When you expect some result/output from your coroutine
            coroutineBuilders.asyncCoroutineBuilder()
            // Another for async
            coroutineBuilders.printFollowers()
        }

        // Coroutine Jobs, Hierarchy & Cancellation
/*
        GlobalScope.launch(Dispatchers.Main) {
            coroutineJobs.execute()
            coroutineJobs.executeLongRunningTask()
        }
*/

        // withContext()    // Suspend Function, Blocking Nature
        GlobalScope.launch {
            withContext.executeTask()
        }

        // runBlock()   //
        GlobalScope.launch {
            runBlock.executeTask()
        }

    }

    private suspend fun Task1(){
        Log.d(TAG, "Starting Task 1")
        delay(5000)
        //yield() // Suspending Point
        Log.d(TAG, "Ending Task 1")
    }

    private suspend fun Task2(){
        Log.d(TAG, "Starting Task 2")
        delay(2000)
        //yield()
        Log.d(TAG, "Ending Task 2")
    }

    private fun executeLongTask(){
        thread(start = true){
            for(i in 1..100000000L){

            }
        }

    }

}