package com.charging.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.charging.app.model.SessionEntity;

public interface SessionEntityRepository {
	
	static List<SessionEntity> sessions = new ArrayList<>();
	
	SessionEntity add(SessionEntity sessionEntity);
	
	SessionEntity update(SessionEntity sessionEntity);
	
	List<SessionEntity> getAll();

}
