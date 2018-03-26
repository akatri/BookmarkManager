package com.semanticsquare.thrillio.controller;

public class UserController {
	private static UserController instance = new UserController();

	public static UserController getInstance() {
		return instance;
	}

	private UserController() {
	}

}
