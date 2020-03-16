package com.charging.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.charging.app.model.SessionEntity;
import com.charging.app.model.SessionReport;
import com.charging.app.service.StationSessionService;

@RestController
public class SessionEntityController {

	@Autowired
	private StationSessionService stationSessionService;

	@PostMapping("/chargingSessions")
	public @ResponseBody Object submitSession(@RequestBody String stationId) {
		try {
			SessionEntity se = stationSessionService.addSession(stationId);
			return se;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation cannot be completed", e);
		}

	}

	@PutMapping("/chargingSessions/{id}")
	public @ResponseBody SessionEntity stopSession(@PathVariable(name = "id") String sessionId) {
		try {
			SessionEntity se = stationSessionService.stopSession(sessionId);
			return se;
		} catch (NullPointerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such session to stop", e);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation cannot be completed", e);
		}
		
	}

	@GetMapping("/chargingSessions")
	public @ResponseBody List<SessionEntity> getAllSessions() {
		try {
			List<SessionEntity> sessions = stationSessionService.getAllSessions();
			return sessions;	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation cannot be completed", e);
		}
		
	}

	@GetMapping("/chargingSessions/summary")
	public @ResponseBody SessionReport getSummaryReport() {
		try {
			SessionReport sr = stationSessionService.getSessionReport();
			return sr;	
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation cannot be completed", e);
		}
	}

}
