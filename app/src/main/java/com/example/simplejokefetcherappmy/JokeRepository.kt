package com.example.simplejokefetcherappmy

// JokeRepository.kt
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeRepository {

    private val api: JokeApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://official-joke-api.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(JokeApiService::class.java)
    }

    fun getRandomJoke(callback: (Joke?, String?) -> Unit) {
        api.getRandomJoke().enqueue(object : retrofit2.Callback<Joke> {
            override fun onResponse(call: Call<Joke>, response: retrofit2.Response<Joke>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error fetching joke")
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }
}