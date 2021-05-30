package com.example.localstack.model;

import lombok.Data;

@Data
public class Event {

	private String id;

	private String type;

	private String originator;
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", subject=" + ", type=" + type + ", originator=" + originator + "]";
	}
}