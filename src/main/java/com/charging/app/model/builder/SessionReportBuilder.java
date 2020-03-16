package com.charging.app.model.builder;

import com.charging.app.model.SessionReport;

public class SessionReportBuilder {

	public static SessionReport buildSessionReport(long total, long started, long stopped) {
		SessionReport sr = new SessionReport();
		sr.setTotal(total);
		sr.setStarted(started);
		sr.setStopped(stopped);
		return sr;
	}

}
