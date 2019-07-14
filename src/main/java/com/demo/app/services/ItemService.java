package com.demo.app.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.ItemRespository;
import com.demo.app.model.Item;
import com.demo.app.response.model.ItemResponse;
import com.demo.app.util.ItemConstants;

@Service
public class ItemService extends AbstractItemService {

	private static Logger log = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private ItemRespository itemRespository;

	public ItemResponse findAll() {
		log.debug("Getting list of items...");
		return createSuccessResponse(itemRespository.findAll());
	}

	public ItemResponse findById(Long id) {
		ItemResponse itemResponse = null;
		Optional<Item> itemOP = itemRespository.findById(id);
		if (!itemOP.isPresent()) {
			log.debug(ItemConstants.MSG_ITEM_NOT_FOUND);
			itemResponse = createFailureResponse(null, ItemConstants.MSG_ITEM_NOT_FOUND);
		} else {
			log.debug(ItemConstants.MSG_ITEM_FOUND);
			itemResponse = createSuccessResponse(itemOP.get(), ItemConstants.MSG_ITEM_FOUND);
		}
		return itemResponse;
	}

	public ItemResponse save(Item item) {
		Item newItem = itemRespository.save(item);
		log.debug(ItemConstants.MSG_ITEM_ADDED);
		return createSuccessResponse(newItem, ItemConstants.MSG_ITEM_ADDED);
	}

	public ItemResponse deleteById(Long id) {
		ItemResponse itemResponse = null;
		Optional<Item> itemOP = itemRespository.findById(id);
		if (!itemOP.isPresent()) {
			itemResponse = createFailureResponse(new Item(), ItemConstants.MSG_ITEM_NOT_FOUND);
			log.debug(ItemConstants.MSG_ITEM_NOT_FOUND);
		} else {
			itemRespository.deleteById(id);
			log.debug(ItemConstants.MSG_ITEM_DELETED);
			itemResponse = createSuccessResponse(itemOP.get(), ItemConstants.MSG_ITEM_DELETED);
		}
		return itemResponse;
	}

	public ItemResponse updateById(Long id, Item item) {
		ItemResponse itemResponse = null;
		Optional<Item> itemOP = itemRespository.findById(id);
		if (!itemOP.isPresent()) {
			itemResponse = createFailureResponse(item, ItemConstants.MSG_ITEM_NOT_FOUND);
			log.debug(ItemConstants.MSG_ITEM_NOT_FOUND);
		} else {
			Item newItem = itemRespository.save(item);
			log.debug(ItemConstants.MSG_ITEM_UPDATED);
			itemResponse = createSuccessResponse(newItem, ItemConstants.MSG_ITEM_UPDATED);
		}
		return itemResponse;
	}
}
