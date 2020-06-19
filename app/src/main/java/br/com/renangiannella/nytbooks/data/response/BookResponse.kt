package br.com.renangiannella.nytbooks.data.response

import br.com.renangiannella.nytbooks.data.model.BooksResult
import com.google.gson.annotations.SerializedName

data class BookResponse(

    @SerializedName("results")
    val booksResult: List<BooksResult>

)