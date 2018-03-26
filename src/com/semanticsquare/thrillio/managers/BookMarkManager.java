package com.semanticsquare.thrillio.managers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.KidfriendlyStatus;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.dao.BookMarkDao;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookMark;
import com.semanticsquare.thrillio.entities.WebLink;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

public class BookMarkManager {
	private static BookMarkManager instance = new BookMarkManager();
	private static BookMarkDao bookMarkDao = new BookMarkDao();

	private BookMarkManager() {
	}

	public static BookMarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {

		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);
		return movie;

	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, BookGenre genre,
			double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);
		return book;

	}

	public WebLink createWeblink(long id, String title, String urL, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrL(urL);
		weblink.setHost(host);
		return weblink;
	}

	public List<List<BookMark>> getBookMarks() {

		return bookMarkDao.getBookMarks();
	}

	public void saveUserBookmark(User user, BookMark bookmark) {

		UserBookMark userBookmark = new UserBookMark();
		userBookmark.setUser(user);
		userBookmark.setBookMark(bookmark);
		
		if (bookmark instanceof WebLink) {

			String url = ((WebLink) bookmark).getUrL();
			if (!url.endsWith(".pdf")) {
				String webpage = null;
				try {
					webpage = HttpConnect.download(((WebLink) bookmark).getUrL());
				} catch (URISyntaxException | IOException e) {

					e.printStackTrace();
				}
				if (webpage != null) {
					IOUtil.write(webpage, bookmark.getId());
				}
			}

		}
		bookMarkDao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, KidfriendlyStatus kidfriendlyStatus, BookMark bookMark) {

		bookMark.setIsKidFriendlyStatus(kidfriendlyStatus);
		bookMark.setKidFriendlyMarkedBy(user);
		System.out.println(
				"Kid-Friendly Status :" + kidfriendlyStatus + ", Marked By :" + user.getEmail() + ", " + bookMark);
	}

	public void share(User user, BookMark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println("SharedBy :" + user.getEmail());

		if (bookmark instanceof Book) {
			System.out.println("Data to be shared :" + ((Book) bookmark).getItemData());
		} else if (bookmark instanceof WebLink) {
			System.out.println("Data to be shared :" + ((WebLink) bookmark).getItemData());
		}

	}

}
