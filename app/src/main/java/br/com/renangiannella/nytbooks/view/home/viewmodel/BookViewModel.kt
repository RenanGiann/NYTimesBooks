package br.com.renangiannella.nytbooks.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.renangiannella.nytbooks.data.ResultCallback
import br.com.renangiannella.nytbooks.data.model.Book
import br.com.renangiannella.nytbooks.view.home.repository.BooksRepository

class BookViewModel(val repository: BooksRepository) : ViewModel() {

    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val toastError: MutableLiveData<Pair<Any, String>> = MutableLiveData()

    fun getBooks(apiKey: String, listType: String) {
        isLoading.postValue(true)
        //isLoading.value = true
        repository.getBooks(apiKey, listType) {callback: ResultCallback ->
            when (callback) {
                is ResultCallback.Success -> {
                    isLoading.postValue(false)
                    bookLiveData.value = callback.books

                }
                is ResultCallback.ApiError -> {
                    isLoading.postValue(false)
                    toastError.postValue(Pair(callback.statusCode, "Erro na API"))

                }
                is ResultCallback.ServerError -> {
                    isLoading.postValue(false)
                    toastError.postValue(Pair(callback.statusErrorServer, " "))

                }
            }

        }

    }

    class ViewModelFactory(val repository: BooksRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(BookViewModel::class.java)){
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
}