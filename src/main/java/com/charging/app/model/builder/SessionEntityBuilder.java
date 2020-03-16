package com.charging.app.model.builder;

import java.time.LocalDateTime;
import java.util.UUID;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.StatusEnum;

public class SessionEntityBuilder {

	public static SessionEntity buildSessionEntity(UUID id, String stationId, LocalDateTime startedAt,
			LocalDateTime stoppedAt, StatusEnum statusEnum) {
		SessionEntity se = new SessionEntity();
		se.setId(id);
		se.setStationId(stationId);
		se.setStartedAt(startedAt);
		se.setStoppedAt(stoppedAt);
		se.setStatus(statusEnum);
		return se;
	}

}
