package br.com.renangiannella.nytbooks.view.home.repository

import br.com.renangiannella.nytbooks.data.APIService
import br.com.renangiannella.nytbooks.data.ResultCallback
import br.com.renangiannella.nytbooks.data.model.Book
import br.com.renangiannella.nytbooks.data.response.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepositoryImpl: BooksRepository {
    override fun getBooks(apiKey: String, listType: String, resultCallback: (result: ResultCallback) -> Unit) {
        APIService.service.getBookList(apiKey, listType).enqueue(object: Callback<BookResponse> {

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookResponse ->
                            for (result in bookResponse.booksResult) {
                                val book = result.bookDetails[0].getBooks()
                                books.add(book)
                            }
                        }
                        resultCallback(ResultCallback.Success(books))
                    }
                    response.code() == 401 -> resultCallback(ResultCallback.ApiError(401))
                    else -> resultCallback(ResultCallback.ApiError(400))
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                resultCallback(ResultCallback.ServerError(500))
            }
        })
    }
}