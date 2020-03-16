package com.charging.app.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.charging.app.CarChagingApplication;
import com.charging.app.model.SessionEntity;
import com.charging.app.model.StatusEnum;

@Repository
public class SessionEntityRepositoryImpl implements SessionEntityRepository {

	Logger logger = LoggerFactory.getLogger(CarChagingApplication.class);

	@Override
	public SessionEntity add(SessionEntity sessionEntity) {
		sessionEntity.setId(UUID.randomUUID());
		sessions.add(sessionEntity);
		return sessionEntity;
	}

	@Override
	public SessionEntity update(SessionEntity sessionEntity) {
		if (!sessions.contains(sessionEntity)) {
			logger.warn("No such charging session to stop: " + sessionEntity.getId());
			throw new NullPointerException();
		}
		sessions.get(sessions.indexOf(sessionEntity)).setStoppedAt(LocalDateTime.now());
		sessions.get(sessions.indexOf(sessionEntity)).setStatus(StatusEnum.FINISHED);
		return sessions.get(sessions.indexOf(sessionEntity));
	}

	@Override
	public List<SessionEntity> getAll() {
		return sessions;
	}

}
