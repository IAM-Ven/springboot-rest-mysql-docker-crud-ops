package com.demo.app.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.ItemRespository;
import com.demo.app.exception.ItemNotFoundException;
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
		log.debug("Getting item for id {}", id);
		Optional<Item> itemOP = itemRespository.findById(id);
		itemOP.orElseThrow(() -> new ItemNotFoundException(ItemConstants.MSG_ITEM_NOT_FOUND));
		log.debug(ItemConstants.MSG_ITEM_FOUND);
		return createSuccessResponse(itemOP.get(), ItemConstants.MSG_ITEM_FOUND);
	}

	public ItemResponse save(Item item) {
		log.debug("Getting item with name {}", item.getName());
		Item newItem = itemRespository.save(item);
		log.debug(ItemConstants.MSG_ITEM_ADDED);
		return createSuccessResponse(newItem, ItemConstants.MSG_ITEM_ADDED);
	}

	public ItemResponse deleteById(Long id) {
		log.debug("Deleting item with id {}", id);
		Optional<Item> itemOP = itemRespository.findById(id);
		itemOP.orElseThrow(() -> new ItemNotFoundException(ItemConstants.MSG_ITEM_NOT_FOUND));
		itemRespository.deleteById(id);
		log.debug(ItemConstants.MSG_ITEM_DELETED);
		return createSuccessResponse(itemOP.get(), ItemConstants.MSG_ITEM_DELETED);
	}

	public ItemResponse updateById(Long id, Item item) {
		log.debug("Updating item with id {}", id);
		Optional<Item> itemOP = itemRespository.findById(id);
		itemOP.orElseThrow(() -> new ItemNotFoundException(ItemConstants.MSG_ITEM_NOT_FOUND));
		Item newItem = itemRespository.save(item);
		log.debug(ItemConstants.MSG_ITEM_UPDATED);
		return createSuccessResponse(newItem, ItemConstants.MSG_ITEM_UPDATED);
	}
}
