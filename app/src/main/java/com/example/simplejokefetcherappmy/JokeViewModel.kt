package com.example.simplejokefetcherappmy

// JokeViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JokeViewModel : ViewModel() {

    private val jokeRepository = JokeRepository()
    private val _joke = MutableLiveData<Joke?>()
    private val _error = MutableLiveData<String?>()

    val joke: LiveData<Joke?> get() = _joke
    val error: LiveData<String?> get() = _error

    fun fetchRandomJoke() {
        jokeRepository.getRandomJoke { fetchedJoke, errorMessage ->
            fetchedJoke?.let {
                _joke.postValue(it)
                _error.postValue(null)
            } ?: errorMessage?.let {
                _joke.postValue(null)
                _error.postValue(it)
            }
        }
    }
}