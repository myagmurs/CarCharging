package com.charging.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class SessionEntity implements Serializable {

	private static final long serialVersionUID = 7622530043854243533L;

	private UUID id;
	private String stationId;
	private LocalDateTime startedAt;
	private LocalDateTime stoppedAt;
	private StatusEnum status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public LocalDateTime getStoppedAt() {
		return stoppedAt;
	}

	public void setStoppedAt(LocalDateTime stoppedAt) {
		this.stoppedAt = stoppedAt;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startedAt == null) ? 0 : startedAt.hashCode());
		result = prime * result + ((stationId == null) ? 0 : stationId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((stoppedAt == null) ? 0 : stoppedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionEntity other = (SessionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
