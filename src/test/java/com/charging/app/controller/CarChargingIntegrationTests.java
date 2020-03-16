package com.charging.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.StatusEnum;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionEntityController.class)
public class CarChargingIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SessionEntityController sessionEntityController;

	@Test
	public void submitSessionTest() throws Exception {
		SessionEntity se1 = new SessionEntity();
		se1.setId(UUID.randomUUID());
		se1.setStationId("1");
		se1.setStartedAt(LocalDateTime.now());
		se1.setStoppedAt(null);
		se1.setStatus(StatusEnum.IN_PROGRESS);
		String stationId = "1";

		when(sessionEntityController.submitSession(stationId)).thenReturn(se1);

		mockMvc.perform(post("http://localhost:8080" + "chargingSessions").content(stationId)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("stationId", is(stationId))).andExpect(jsonPath("status", is("IN_PROGRESS")));
	}

	@Test
	public void stopSessionTest() throws Exception {
		String stationId = "2";
		SessionEntity se1 = new SessionEntity();
		se1.setId(UUID.randomUUID());
		se1.setStationId("2");
		se1.setStartedAt(LocalDateTime.now());
		se1.setStoppedAt(LocalDateTime.now());
		se1.setStatus(StatusEnum.FINISHED);

		when(sessionEntityController.stopSession(se1.getId().toString())).thenReturn(se1);

		mockMvc.perform(put("http://localhost:8080" + "chargingSessions/" + se1.getId().toString())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("stationId", is(stationId))).andExpect(jsonPath("status", is("FINISHED")))
				.andExpect(jsonPath("stoppedAt", is(notNullValue())));
	}

	@Test
	public void getSessionsTest() throws Exception {
		SessionEntity se1 = new SessionEntity();
		se1.setId(UUID.randomUUID());
		se1.setStationId("1");
		se1.setStartedAt(LocalDateTime.now());
		se1.setStoppedAt(null);
		se1.setStatus(StatusEnum.IN_PROGRESS);

		List<SessionEntity> sessions = new ArrayList<SessionEntity>();
		sessions.add(se1);

		when(sessionEntityController.getAllSessions()).thenReturn(sessions);

		mockMvc.perform(get("http://localhost:8080" + "chargingSessions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].stationId", is(se1.getStationId())))
				.andExpect(jsonPath("$[0].status", is("IN_PROGRESS")));
	}

}
