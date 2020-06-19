package br.com.renangiannella.nytbooks.data

import br.com.renangiannella.nytbooks.data.model.Book

sealed class ResultCallback {

    class Success(val books: List<Book>): ResultCallback()
    class ApiError(val statusCode: Int): ResultCallback()
    class ServerError(val statusErrorServer: Int): ResultCallback()
    //object ServerError: ResultCallback()

}