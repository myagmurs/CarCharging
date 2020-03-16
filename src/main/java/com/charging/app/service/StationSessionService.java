package com.charging.app.service;

import java.util.List;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.SessionReport;

public interface StationSessionService {

	SessionEntity addSession(String stationId);

	SessionEntity stopSession(String sessionId);

	List<SessionEntity> getAllSessions();

	SessionReport getSessionReport();

}
