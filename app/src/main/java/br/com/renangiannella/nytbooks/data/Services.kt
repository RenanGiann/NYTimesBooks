package br.com.renangiannella.nytbooks.data

import br.com.renangiannella.nytbooks.data.response.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("lists.json")
    fun getBookList(@Query("api-key") apiKey:String,
                    @Query("list") list:String
    ): Call<BookResponse>

}