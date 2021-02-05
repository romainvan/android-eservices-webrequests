package android.eservices.webrequests.data.repository.bookdisplay;

import android.eservices.webrequests.BookApplication;
import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.entity.BookEntity;
import android.eservices.webrequests.data.repository.bookdisplay.local.BookDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.bookdisplay.mapper.BookToBookEntityMapper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class BookDisplayDataRepository {

    private BookDisplayRemoteDataSource bookDisplayRemoteDataSource;
    private BookDisplayLocalDataSource bookDisplayLocalDataSource;
    private BookToBookEntityMapper bookToBookEntityMapper;

    public BookDisplayDataRepository(BookDisplayRemoteDataSource bookDisplayRemoteDataSource,BookDisplayLocalDataSource bookDisplayLocalDataSource){
        this.bookDisplayRemoteDataSource = bookDisplayRemoteDataSource;
        this.bookDisplayLocalDataSource = bookDisplayLocalDataSource;
    }
    public Single<BookSearchResponse> getBookSearchResponse(String keyword){
        return bookDisplayRemoteDataSource.getBookSearchResults(keyword);
    }

    public Single<Book> getBookDetails(String bookId){
        return bookDisplayRemoteDataSource.getBookDetails(bookId);
    }

    public Flowable<List<BookEntity>> getFavoriteBooks() {
        return bookDisplayLocalDataSource.getFavorites();
    }

    public Completable addBookToFavorites(String bookId){
       return bookDisplayRemoteDataSource.getBookDetails(bookId)
                .map(new Function<Book, BookEntity>() {
                    @Override
                    public BookEntity apply(Book book) throws Exception {
                        return bookToBookEntityMapper.map(book);
                    }
                })
                .flatMapCompletable(new Function<BookEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(BookEntity bookEntity) throws Exception {
                        return bookDisplayLocalDataSource.addFavorite(bookEntity);
                    }
                });
    }
}
