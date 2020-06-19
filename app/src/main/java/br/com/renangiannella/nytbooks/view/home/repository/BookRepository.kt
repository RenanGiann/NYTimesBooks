package br.com.renangiannella.nytbooks.view.home.repository

import br.com.renangiannella.nytbooks.data.APIService
import br.com.renangiannella.nytbooks.data.response.BookResponse
import retrofit2.Call

class BookRepository {
    fun getBookList(apiKey: String, listType: String): Call<BookResponse> {
        return APIService.service.getBookList(apiKey, listType)
    }
}