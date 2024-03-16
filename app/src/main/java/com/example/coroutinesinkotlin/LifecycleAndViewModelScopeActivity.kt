package com.example.coroutinesinkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesinkotlin.CoroutineClasses.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LifecycleAndViewModelScopeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val TAG: String = "KotlinCo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lifecycle_and_view_model_scope)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        lifecycleScope.launch {
            Log.d(TAG, "Lifecycle Scope.. Finishing in 3s..")
            delay(3000)
            startActivity(Intent(this@LifecycleAndViewModelScopeActivity, MainActivity::class.java))
            finish()
        }

    }
}