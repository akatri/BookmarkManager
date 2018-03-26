package com.semanticsquare.thrillio.constants;

public enum KidfriendlyStatus {

	APPROVED("Approved"), REJECTED("Rejected"), UNKNOWN("Unknown");

	private String status;

	private KidfriendlyStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
