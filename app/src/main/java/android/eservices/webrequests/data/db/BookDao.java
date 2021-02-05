package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.entity.BookEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BookDao {

    @Query("SELECT * FROM bookentity")
    Flowable<List<BookEntity>> getAllFavoris();

    @Insert
    Completable insert(BookEntity bookEntity);

    @Query("DELETE FROM bookentity where id = :id")
    Completable delete(String id);

}
