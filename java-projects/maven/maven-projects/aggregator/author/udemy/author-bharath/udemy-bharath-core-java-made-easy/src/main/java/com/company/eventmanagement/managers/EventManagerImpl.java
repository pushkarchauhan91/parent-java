package com.company.eventmanagement.managers;

import com.company.eventmanagement.entities.Event;

public class EventManagerImpl implements EventManager {

	@Override
	public Event create(Long id) {
		return new Event(id, null, null);
	}

}
