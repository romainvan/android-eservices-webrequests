package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BooksService {

    //https://www.googleapis.com/books/v1/volumes?q=search+terms
    @GET("volumes")
    Single<BookSearchResponse> getBookSearchResults(@Query("q") String search,@Query("key")String key);

    @GET("volumes/{bookId}")
    Single<Book> getBookDetails(@Path("bookId") String bookId, @Query("key") String apiKey);
}
