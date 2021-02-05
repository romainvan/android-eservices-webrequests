package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.BookApplication;
import android.eservices.webrequests.data.api.BooksService;
import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;

import io.reactivex.Single;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class BookDisplayRemoteDataSource {

    private BooksService booksService;

    public BookDisplayRemoteDataSource(BooksService booksService){
        this.booksService=booksService;
    }

    public Single<BookSearchResponse> getBookSearchResults(String keywords){
        return booksService.getBookSearchResults(keywords, BookApplication.API_KEY);
    }

    public Single<Book> getBookDetails(String bookId){
        return booksService.getBookDetails(bookId,BookApplication.API_KEY);
    }



}
