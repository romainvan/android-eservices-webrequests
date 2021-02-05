package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.repository.bookdisplay.BookDisplayDataRepository;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookViewItem;
import android.eservices.webrequests.presentation.bookdisplay.search.mapper.BookToViewModelMapper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BookSearchViewModel extends ViewModel {
    private BookDisplayDataRepository bookDisplayDataRepository;
    private CompositeDisposable compositeDisposable;
    private BookToViewModelMapper bookToViewModelMapper;

    public BookSearchViewModel(BookDisplayDataRepository BookDisplayDataRepository) {
        this.bookDisplayDataRepository = BookDisplayDataRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.bookToViewModelMapper = new BookToViewModelMapper();
    }

    private MutableLiveData<List<BookViewItem>> books = new MutableLiveData<List<BookViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<BookViewItem>> getBooks() {
        return books;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    //TODO : handle loader
    public void searchBooks(String keywords) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(bookDisplayDataRepository.getBookSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BookSearchResponse>() {

                    @Override
                    public void onSuccess(BookSearchResponse bookSearchResponse) {
                        books.setValue(bookToViewModelMapper.map(bookSearchResponse.getBookList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                    }
                }));
    }


    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }

}