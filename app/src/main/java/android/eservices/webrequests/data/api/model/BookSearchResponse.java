package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookSearchResponse {

    //recherche items dans le gson on change le nom en booklist
    @SerializedName("items")
    List<Book> bookList;

    //recherche totalItems dans le gson
    int totalItems;

    public List<Book> getBookList() {
        return bookList;
    }

    public int getTotalItems() {
        return totalItems;
    }


}
