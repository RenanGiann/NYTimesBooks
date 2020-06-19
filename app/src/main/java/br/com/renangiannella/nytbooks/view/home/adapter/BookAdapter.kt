package br.com.renangiannella.nytbooks.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renangiannella.nytbooks.R
import br.com.renangiannella.nytbooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(val books: List<Book>, val itemClickListener: ((book: Book) -> Unit)): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView, itemClickListener)
    }

    override fun getItemCount(): Int = books.count()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BookViewHolder(itemView: View,
                         private val itemClickListener: ((book: Book) -> Unit)): RecyclerView.ViewHolder(itemView) {

        private val title = itemView.txtTitle
        private val author = itemView.txtAuthor

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
               itemClickListener.invoke(book)
            }
        }
    }

}