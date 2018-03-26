package com.semanticsquare.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.semanticsquare.thrillio.managers.BookMarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyElgible() {

		// Test 1:porn in{url,title}--false
		WebLink weblink = BookMarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/porn-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		boolean IsKidFriendlyElgible = weblink.isKidFriendlyElgible();
		assertFalse("porn in url,method must return false", IsKidFriendlyElgible);
		//
		weblink = BookMarkManager.getInstance().createWeblink(2000, "Taming Porn, Part 2",
				"http://www.javaworld.com/article/2072759/-java/taming-tiger--part-2.html", "http://www.javaworld.com");

		IsKidFriendlyElgible = weblink.isKidFriendlyElgible();
		assertFalse("porn in title,method must return false", IsKidFriendlyElgible);
		// Test 2:adult in host--false
		weblink = BookMarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/-java/taming-tiger--part-2.html", "http://www.javporn.com");

		IsKidFriendlyElgible = weblink.isKidFriendlyElgible();
		assertFalse("adult in host,method must return false", IsKidFriendlyElgible);
		// Test 3: adult in url but not in host--true
		weblink = BookMarkManager.getInstance().createWeblink(2000, "Taming Tiger, Part 2",
				"http://www.javadult.com/article/2072759/-java/taming-tiger--part-2.html", "http://www.javaworld.com");

		IsKidFriendlyElgible = weblink.isKidFriendlyElgible();
		assertTrue("adult in url but not in host,method must return  true", IsKidFriendlyElgible);
		// Test 4: adult in title only--true
		weblink = BookMarkManager.getInstance().createWeblink(2000, "Adult Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/-java/taming-tiger--part-2.html", "http://www.javaworld.com");

		IsKidFriendlyElgible = weblink.isKidFriendlyElgible();
		assertTrue("adult in  title,method must return  true", IsKidFriendlyElgible);
	}
}
