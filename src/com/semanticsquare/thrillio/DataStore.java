package com.semanticsquare.thrillio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookMark;
import com.semanticsquare.thrillio.managers.BookMarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class DataStore {
	private static List<User> users = new ArrayList<>();
	private static List<List<BookMark>> bookmarks = new ArrayList<>();
	private static List<UserBookMark> userBookMarks = new ArrayList<>();
	private static Properties props = new Properties();

	static {
		try (FileInputStream fis = new FileInputStream("/home/aka_tri/workspace/Thrillio/src/Config.properties")) {

			props.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static List<User> getUsers() {
		return users;
	}

	public static List<List<BookMark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadMovies();
		loadBooks();
		loadWebLinks();
	}

	private static void loadUsers() {

		try (Connection con = DriverManager.getConnection(props.getProperty("jdbc.url.users"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
				PreparedStatement ps = con.prepareStatement("SELECT * FROM users");) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Gender gender = Gender.MALE;
				if (rs.getString(6).equals("f")) {
					gender = Gender.FEMALE;
				} else {
					gender = Gender.TRANSGENDER;

				}
				User user = UserManager.getInstance().createUser(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), gender, UserType.valueOf(rs.getString(7)));
				users.add(user);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	private static void loadMovies() {

		List<BookMark> bookmarklist = new ArrayList<>();

		BookMark bookmark;
		try (Connection con = DriverManager.getConnection(props.getProperty("jdbc.url.movies"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
				PreparedStatement ps = con.prepareStatement("SELECT * FROM movies");) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String[] cast = rs.getString(4).split(",");
				String[] directors = rs.getString(5).split(",");

				bookmark = BookMarkManager.getInstance().createMovie(rs.getInt(1), rs.getString(2), "", rs.getInt(3),
						cast, directors, MovieGenre.valueOf(rs.getString(6)), rs.getDouble(7));
				bookmarklist.add(bookmark);
			}
			bookmarks.add(bookmarklist);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	private static void loadBooks() {
		List<BookMark> bookmarklist = new ArrayList<>();

		BookMark bookmark;
		try (Connection con = DriverManager.getConnection(props.getProperty("jdbc.url.books"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
				PreparedStatement ps = con.prepareStatement("SELECT * FROM books");) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String[] author = rs.getString(5).split(",");
				bookmark = BookMarkManager.getInstance().createBook(rs.getLong(1), rs.getString(2), rs.getInt(3),
						rs.getString(4), author, BookGenre.valueOf(rs.getString(6)), rs.getDouble(7));
				bookmarklist.add(bookmark);
			}
			bookmarks.add(bookmarklist);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}

	}

	private static void loadWebLinks() {

		List<BookMark> bookmarklist = new ArrayList<>();

		BookMark bookmark;
		try (Connection con = DriverManager.getConnection(props.getProperty("jdbc.url.weblinks"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
				PreparedStatement ps = con.prepareStatement("SELECT * FROM weblinks");) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bookmark = BookMarkManager.getInstance().createWeblink(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4));

				bookmarklist.add(bookmark);
			}
			bookmarks.add(bookmarklist);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

	public static void add(UserBookMark userBookmark) {
		userBookMarks.add(userBookmark);

	}

}
