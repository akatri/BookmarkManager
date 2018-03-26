package com.semanticsquare.thrillio.controller;

import com.semanticsquare.thrillio.constants.KidfriendlyStatus;
import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookMarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	public static BookmarkController getInstance() {
		return instance;
	}

	private BookmarkController() {
	}

	public void saveUserBookmark(User user, BookMark bookmark) {
		BookMarkManager.getInstance().saveUserBookmark(user, bookmark);

	}

	public void setKidFriendlyStatus(User user, KidfriendlyStatus kidfriendlyStatus, BookMark bookMark) {

		BookMarkManager.getInstance().setKidFriendlyStatus(user, kidfriendlyStatus, bookMark);

	}

	public void share(User user, BookMark bookmark) {
		BookMarkManager.getInstance().share(user, bookmark);
	}
}
