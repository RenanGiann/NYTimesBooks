package br.com.renangiannella.nytbooks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.renangiannella.nytbooks.BuildConfig
import br.com.renangiannella.nytbooks.R
import br.com.renangiannella.nytbooks.view.detail.BookDetailActivity
import br.com.renangiannella.nytbooks.view.home.adapter.BookAdapter
import br.com.renangiannella.nytbooks.view.home.repository.BookRepositoryImpl
import br.com.renangiannella.nytbooks.view.home.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarHome.title = "Books NY Times"

        //val viewModel: BookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        val viewModel: BookViewModel = BookViewModel.ViewModelFactory(BookRepositoryImpl()).create(BookViewModel::class.java)

        viewModel.getBooks(BuildConfig.API_KEY, HARDCOVER_FICTION)

        viewModel.isLoading.observe(this, Observer {
            when(it) {
                true -> loading.visibility = View.VISIBLE
                false -> loading.visibility = View.GONE
            }
        })

        viewModel.toastError.observe(this, Observer {
            it.let {
                Toast.makeText(this@MainActivity, "API Error", Toast.LENGTH_SHORT).show()
                //Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.bookLiveData.observe(this, Observer {
            it.let {books ->
                with(recyclerViewMain){
                    layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BookAdapter(books) { book ->
                        val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
                        intent.putExtra(TEXT_TITLE, book.title)
                        intent.putExtra(TEXT_DESCRIPTION, book.description)
                        startActivity(intent)
                    }
                }
            }
        })
    }

    companion object {
        private const val HARDCOVER_FICTION = "hardcover-fiction"
        const val TEXT_TITLE = "TEXT_TITLE"
        const val TEXT_DESCRIPTION = "TEXT_DESCRIPTION"
    }
}
