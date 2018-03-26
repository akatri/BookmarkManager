package com.semanticsquare.thrillio.dao;

import java.util.List;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.UserBookMark;

public class BookMarkDao {
	public List<List<BookMark>> getBookMarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookMark userBookmark) {
		DataStore.add(userBookmark);

	}

}
