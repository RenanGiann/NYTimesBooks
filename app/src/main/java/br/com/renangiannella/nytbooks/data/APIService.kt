package br.com.renangiannella.nytbooks.data

import br.com.renangiannella.nytbooks.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private fun initRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service: Services = initRetroFit().create(Services::class.java)
}