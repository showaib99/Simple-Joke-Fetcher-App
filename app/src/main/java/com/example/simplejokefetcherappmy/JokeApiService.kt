package com.example.simplejokefetcherappmy

// JokeApiService.kt
import retrofit2.Call
import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")
    fun getRandomJoke(): Call<Joke>
}