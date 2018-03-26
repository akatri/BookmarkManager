package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.managers.BookMarkManager;

class MovieTest {

	@Test
	void testIsKidFriendlyElgible() {
		// Test 1:Movie is Horror
		Movie movie = BookMarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		boolean isKidFriendlyEligible = movie.isKidFriendlyElgible();
		assertFalse("Horror movie is not for kids", isKidFriendlyEligible);

		// Test 2:Movie is Thriller
		// Test 1:Movie is Horror
		movie = BookMarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		isKidFriendlyEligible = movie.isKidFriendlyElgible();
		assertFalse("Thriller movie is not for kids", isKidFriendlyEligible);
	}

}
