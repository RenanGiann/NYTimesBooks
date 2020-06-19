package br.com.renangiannella.nytbooks.data.model

import com.google.gson.annotations.SerializedName

data class BookDetails(

    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String

){
    fun getBooks(): Book = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}
