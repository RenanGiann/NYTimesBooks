package br.com.renangiannella.nytbooks.view.home.repository

import br.com.renangiannella.nytbooks.data.ResultCallback

interface BooksRepository {

    fun getBooks(apiKey: String, listType: String, resultCallback: (result: ResultCallback) -> Unit)

}