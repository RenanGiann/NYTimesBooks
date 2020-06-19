package br.com.renangiannella.nytbooks.data.model

import com.google.gson.annotations.SerializedName

data class BooksResult(

    @SerializedName("book_details")
    val bookDetails: List<BookDetails>
)