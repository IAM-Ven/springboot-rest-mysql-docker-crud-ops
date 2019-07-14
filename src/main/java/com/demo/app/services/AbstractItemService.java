package com.demo.app.services;

import java.util.ArrayList;
import java.util.List;

import com.demo.app.model.Item;
import com.demo.app.response.model.ItemResponse;
import com.demo.app.util.ItemConstants;

public class AbstractItemService {

	public ItemResponse createSuccessResponse(List<Item> items) {
		return new ItemResponse(ItemConstants.MSG_ITEM_LIST, ItemConstants.STATUS_SUCCESS, items);
	}

	public ItemResponse createSuccessResponse(Item item, String message) {
		List<Item> items = new ArrayList<>();
		items.add(item);
		return new ItemResponse(message, ItemConstants.STATUS_SUCCESS, items);
	}
}
