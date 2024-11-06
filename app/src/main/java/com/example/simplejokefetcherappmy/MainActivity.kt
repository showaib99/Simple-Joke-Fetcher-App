package com.example.simplejokefetcherappmy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import com.example.simplejokefetcherappmy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val jokeViewModel: JokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view using Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Observe the joke LiveData from the ViewModel
        jokeViewModel.joke.observe(this, Observer { joke ->
            joke?.let {
                binding.setupTextView.text = it.setup
                binding.punchlineTextView.text = it.punchline
                binding.punchlineTextView.visibility = android.view.View.VISIBLE
                binding.errorTextView.visibility = android.view.View.GONE
            }
        })

        // Observe the error LiveData from the ViewModel
        jokeViewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                binding.errorTextView.text = it
                binding.errorTextView.visibility = android.view.View.VISIBLE
                binding.punchlineTextView.visibility = android.view.View.GONE
            }
        })

        // Set click listener for the button to fetch a new joke
        binding.getJokeButton.setOnClickListener {
            jokeViewModel.fetchRandomJoke()
        }
    }
}