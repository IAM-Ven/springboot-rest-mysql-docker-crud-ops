package com.demo.app.response.model;

import java.util.List;

import com.demo.app.model.Item;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemResponse {
	private String message;
	private String status;
	private List<Item> items;

	public ItemResponse() {
		super();
	}

	public ItemResponse(String message, String status, List<Item> items) {
		super();
		this.message = message;
		this.status = status;
		this.items = items;
	}

	public ItemResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
