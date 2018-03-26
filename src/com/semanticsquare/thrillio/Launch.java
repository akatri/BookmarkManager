package com.semanticsquare.thrillio;

import java.util.List;

import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookMarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<BookMark>> bookMarks;

	private static void loadData() {

		System.out.println("Loading Data..");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookMarks = BookMarkManager.getInstance().getBookMarks();

		// printUsers();
		// printBookMarks();
	}

	private static void start() {

		// System.out.println("Bookmarking starts..");
		for (User user : users) {
			View.browse(user, bookMarks);
			System.out.println();

		}

	}

	private static void printBookMarks() {
		System.out.println("Printing BookMarks..");
		for (List<BookMark> bookMarks2 : bookMarks) {
			for (BookMark bookMark : bookMarks2) {
				System.out.println(bookMark);
			}
			System.out.println();
		}
	}

	private static void printUsers() {
		System.out.println("Printing Users..");
		for (User user : users) {
			System.out.println(user);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		loadData();
		start();

	}

}
