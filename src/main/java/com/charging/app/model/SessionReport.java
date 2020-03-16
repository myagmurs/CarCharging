package com.charging.app.model;

import java.io.Serializable;

public class SessionReport implements Serializable {

	private static final long serialVersionUID = -5684662601640698526L;

	private long total;
	private long started;
	private long stopped;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getStarted() {
		return started;
	}

	public void setStarted(long started) {
		this.started = started;
	}

	public long getStopped() {
		return stopped;
	}

	public void setStopped(long stopped) {
		this.stopped = stopped;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (started ^ (started >>> 32));
		result = prime * result + (int) (stopped ^ (stopped >>> 32));
		result = prime * result + (int) (total ^ (total >>> 32));
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
		SessionReport other = (SessionReport) obj;
		if (started != other.started)
			return false;
		if (stopped != other.stopped)
			return false;
		if (total != other.total)
			return false;
		return true;
	}

}
