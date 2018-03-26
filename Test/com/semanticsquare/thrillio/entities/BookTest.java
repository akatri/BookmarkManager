package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.managers.BookMarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyElgible() {
		//Philosophy book ain't for kids
		Book book=BookMarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);

		boolean isKidFriendlyEligible=book.isKidFriendlyElgible();
		assertFalse("Philosophy book ain't for kids",isKidFriendlyEligible);
		//Self-help books ain't for kids
		 book=BookMarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);

		 isKidFriendlyEligible=book.isKidFriendlyElgible();
		assertFalse("Self help book ain't for kids",isKidFriendlyEligible);
}
}
