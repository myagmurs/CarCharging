package com.charging.app.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.StatusEnum;

@SpringBootTest
public class CarChargingServiceUnitTests {
	
	@Autowired
	private StationSessionService stationSessionService;
	
	@Test
	void addSessionTest() {
		SessionEntity se1 = new SessionEntity();
		se1.setStationId("1");
		se1.setStatus(StatusEnum.IN_PROGRESS);
		
		SessionEntity returnedSe = stationSessionService.addSession(se1.getStationId());
		
		assertNotNull(returnedSe);
		assertEquals(returnedSe.getStationId(), se1.getStationId());
		assertNull(returnedSe.getStoppedAt());
		assertEquals(returnedSe.getStatus(), se1.getStatus());
	}
	
	@Test
	void stopSessionTest() {
		String stationId = "2";
		
		SessionEntity startedSe = stationSessionService.addSession(stationId);
		SessionEntity stoppedSe = stationSessionService.stopSession(startedSe.getId().toString());
		
		assertNotNull(stoppedSe);
		assertEquals(startedSe.getStationId(), startedSe.getStationId());
		assertNotNull(stoppedSe.getStoppedAt());
		assertEquals(stoppedSe.getStatus(), StatusEnum.FINISHED);
	}
	
	@Test
	void getAllSessionsTest() {
		String stationId1 = "1";
		stationSessionService.addSession(stationId1);
		
		String stationId2 = "2";
		stationSessionService.addSession(stationId2);
		
		String stationId3 = "3";
		SessionEntity se = stationSessionService.addSession(stationId3);
		stationSessionService.stopSession(se.getId().toString());
		
		List<SessionEntity> sessionEntities = stationSessionService.getAllSessions();
		
		assertNotNull(sessionEntities);
		assertEquals(3, sessionEntities.size());
			
	}

}
