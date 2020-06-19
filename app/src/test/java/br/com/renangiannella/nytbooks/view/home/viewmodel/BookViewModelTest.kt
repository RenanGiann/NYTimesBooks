package br.com.renangiannella.nytbooks.view.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.renangiannella.nytbooks.BuildConfig
import br.com.renangiannella.nytbooks.data.ResultCallback
import br.com.renangiannella.nytbooks.data.model.Book
import br.com.renangiannella.nytbooks.view.home.repository.BookRepositoryImpl
import br.com.renangiannella.nytbooks.view.home.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookViewModelTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookLiveDataObserver: Observer<List<Book>>
    @Mock
    private lateinit var isLoadingObserver: Observer<Boolean>
    @Mock
    private lateinit var toastErrorObserver: Observer<Pair<Any, String>>

    @Test
    fun `when ViewModel getBooks get success then set bookLiveData` () {
        // Arrange
        val books = listOf(Book("Title1", "Author1", "Description1"))
        val resultSuccess = MockBookRepositoryImpl(ResultCallback.Success(books))
        val viewModel = BookViewModel(resultSuccess)
        viewModel.bookLiveData.observeForever(bookLiveDataObserver)

        // Act

        viewModel.getBooks(BuildConfig.API_KEY, "hardcover-fiction")

        // Assert

        verify(bookLiveDataObserver).onChanged(books)
    }

    @Test
    fun `when viewModel getBooks get success isLoading set false`() {
        // Arrange
        val books= listOf(Book("Title2", "Author2", "Description2"))
        val resultSuccess =  MockBookRepositoryImpl(ResultCallback.Success(books))
        val viewModel = BookViewModel(resultSuccess)
        viewModel.isLoading.observeForever(isLoadingObserver)

        // Act

        viewModel.getBooks(BuildConfig.API_KEY, "hardcover-fiction")

        // Assert

        verify(isLoadingObserver).onChanged(true)
    }

    @Test
    fun `when viewModel getBooks return a Api error and toast set code and string`() {
        // Arrange
        val resultApiError = MockBookRepositoryImpl(ResultCallback.ApiError(401))
        val viewModel = BookViewModel(resultApiError)
        viewModel.toastError.observeForever(toastErrorObserver)

        // Act

        viewModel.getBooks(BuildConfig.API_KEY, "hardcover-fiction")

        // Assert

       verify(toastErrorObserver).onChanged(Pair(401, "Erro na API"))
    }

    @Test
    fun `when viewModel getBooks return ServerError and toast set string`() {
        // Arrange
        val resultServerError = MockBookRepositoryImpl(ResultCallback.ServerError(500))
        val viewModel = BookViewModel(resultServerError)
        viewModel.toastError.observeForever(toastErrorObserver)
        var callback: ResultCallback

        // Act

        viewModel.getBooks(BuildConfig.API_KEY, "hardcover-fiction")

        // Assert

        verify(toastErrorObserver).onChanged(Pair(500, " "))
    }

    class MockBookRepositoryImpl(private val result: ResultCallback) : BooksRepository {
        override fun getBooks(apiKey: String, listType: String, resultCallback: (result: ResultCallback) -> Unit) {
           resultCallback(result)
        }

    }


}