package com.semanticsquare.thrillio;

import java.util.List;

import com.semanticsquare.thrillio.constants.KidfriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controller.BookmarkController;
import com.semanticsquare.thrillio.entities.BookMark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

public class View {

	public static void browse(User user, List<List<BookMark>> bookMarks) {
		System.out.println(user.getEmail() + " is bookmarking items");
		
		for (List<BookMark> bookMarks2 : bookMarks) {
			for (BookMark bookMark : bookMarks2) {
				// BookMarking!!..
				//if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean canBookmark = getBookmarkDecision();

					if (canBookmark) {
						
						BookmarkController.getInstance().saveUserBookmark(user, bookMark);
						System.out.println("Item Bookmarked--" + bookMark);

					}

				//}
				// Mark kid-friendly
				if (user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {

					if (bookMark.isKidFriendlyElgible()
							&& bookMark.getIsKidFriendlyStatus().equals(KidfriendlyStatus.UNKNOWN)) {
						// make a decision
						KidfriendlyStatus kidfriendlyStatus = getKidFriendlyStatusDecision();
						if (!kidfriendlyStatus.equals(KidfriendlyStatus.UNKNOWN)) {

							BookmarkController.getInstance().setKidFriendlyStatus(user, kidfriendlyStatus, bookMark);
						}

					}
					// sharing bookmark,if it is approved
					if (bookMark.getIsKidFriendlyStatus().equals(KidfriendlyStatus.APPROVED)
							&& bookMark instanceof Shareable) {
						boolean shareDecision = getSharingDecision();
						if (shareDecision) {
							BookmarkController.getInstance().share(user, bookMark);
						}
					}
				}
			}

		}
	}

	// TODO : Below methods needs to simulate user I/O and that functionality will
	// be
	// upgraded later.

	private static boolean getSharingDecision() {
		return (Math.random() < 0.5) ? true : false;
	}

	private static KidfriendlyStatus getKidFriendlyStatusDecision() {
		return Math.random() < 0.4 ? KidfriendlyStatus.APPROVED
				: ((Math.random() >= 0.4) && (Math.random()) < 0.8 ? KidfriendlyStatus.REJECTED
						: KidfriendlyStatus.UNKNOWN);
	}

	private static boolean getBookmarkDecision() {

		return (Math.random() < 0.5) ? true : false;
	}
}
