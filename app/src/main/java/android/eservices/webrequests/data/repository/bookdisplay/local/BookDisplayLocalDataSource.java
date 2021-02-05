package android.eservices.webrequests.data.repository.bookdisplay.local;

import android.eservices.webrequests.data.db.BookDao;
import android.eservices.webrequests.data.db.BookDatabase;
import android.eservices.webrequests.data.entity.BookEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class BookDisplayLocalDataSource  {

    private BookDatabase bookDatabase;

    public BookDisplayLocalDataSource(BookDatabase bookDatabase){
        this.bookDatabase = bookDatabase;
    }

    public Flowable<List<BookEntity>> getFavorites(){
        return bookDatabase.bookDao().getAllFavoris();
    }

    public Completable addFavorite(BookEntity book){
        return bookDatabase.bookDao().insert(book);
    }

    public Completable removeFavorite(String id){
        return bookDatabase.bookDao().delete(id);
    }

}
