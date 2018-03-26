package com.semanticsquare.thrillio.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpConnect {
	public static String download(String sourceLink) throws MalformedURLException, URISyntaxException, IOException {
		System.out.println("Downloading data...");
		URL url = new URI(sourceLink).toURL();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		int response = con.getResponseCode();
		if (response >= 200 && response < 300) {
			return IOUtil.read(con.getInputStream());
		}
		return null;

	}

}
