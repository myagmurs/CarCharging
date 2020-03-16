package com.charging.app.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.SessionReport;
import com.charging.app.model.StatusEnum;
import com.charging.app.model.builder.SessionEntityBuilder;
import com.charging.app.model.builder.SessionReportBuilder;
import com.charging.app.repository.SessionEntityRepository;

@Service
public class StationSessionServiceImpl implements StationSessionService {

	@Autowired
	private SessionEntityRepository sessionEntityRepository;

	@Override
	public SessionEntity addSession(String stationId) {
		SessionEntity se = SessionEntityBuilder.buildSessionEntity(null, stationId, LocalDateTime.now(), null,
				StatusEnum.IN_PROGRESS);
		return sessionEntityRepository.add(se);
	}

	@Override
	public SessionEntity stopSession(String sessionId) {
		SessionEntity se = new SessionEntity();
		se.setId(UUID.fromString(sessionId));
		return sessionEntityRepository.update(se);
	}

	@Override
	public List<SessionEntity> getAllSessions() {
		return sessionEntityRepository.getAll();
	}

	@Override
	public SessionReport getSessionReport() {
		List<SessionEntity> sessions = sessionEntityRepository.getAll();
		LocalDateTime current = LocalDateTime.now();
		long started = sessions.stream()
				.filter(se -> ((1 > Duration.between(se.getStartedAt(), current).toMinutes()
						|| (se.getStoppedAt() != null && 1 > Duration.between(se.getStoppedAt(), current).toMinutes()))
						&& StatusEnum.IN_PROGRESS.equals(se.getStatus())))
				.count();
		long stopped = sessions.stream()
				.filter(se -> ((1 > Duration.between(se.getStartedAt(), current).toMinutes()
						|| (se.getStoppedAt() != null && 1 > Duration.between(se.getStoppedAt(), current).toMinutes()))
						&& StatusEnum.FINISHED.equals(se.getStatus())))
				.count();
		long total = started + stopped;

		return SessionReportBuilder.buildSessionReport(total, started, stopped);
	}

}
