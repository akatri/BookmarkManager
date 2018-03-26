package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.partner.Shareable;

public class WebLink extends BookMark implements Shareable {
	private String urL;
	private String host;

	public String getUrL() {
		return urL;
	}

	public void setUrL(String urL) {
		this.urL = urL;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "WebLink [urL=" + urL + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendlyElgible() {
		if (urL.contains("porn") || getTitle().contains("porn") || urL.contains("Porn") || getTitle().contains("Porn")
				|| getHost().contains("Porn") || getHost().contains("porn")) {
			return false;
		}

		return true;

	}

	@Override
	public String getItemData() {
		StringBuilder sb = new StringBuilder();
		sb.append("<item>");
		sb.append("<type>WebLink</type>");
		sb.append("<title>").append(getTitle()).append("</title>");
		sb.append("<url>").append(urL).append("</url>");
		sb.append("<host>").append(host).append("</host>");
		sb.append("</item>");
		return sb.toString();

	}

}
