package br.com.renangiannella.nytbooks.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.renangiannella.nytbooks.R
import br.com.renangiannella.nytbooks.view.MainActivity
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        toolbarDetail.title = intent.getStringExtra(MainActivity.TEXT_TITLE)

        titleDetail.text = intent.getStringExtra(MainActivity.TEXT_TITLE)
        descriptionText.text = intent.getStringExtra(MainActivity.TEXT_DESCRIPTION)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

