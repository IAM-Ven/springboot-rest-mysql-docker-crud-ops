package com.demo.app.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.model.Item;
import com.demo.app.response.model.ItemResponse;
import com.demo.app.services.ItemService;

@RestController
@RequestMapping("/api/v1/items")
public class ItemAPI {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<ItemResponse> findAll() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@PostMapping
	public ResponseEntity<ItemResponse> create(@Valid @RequestBody Item item) {
		return ResponseEntity.ok(itemService.save(item));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemResponse> findById(@PathVariable @Digits(fraction = 0, integer = 1000) Long id) {
		return ResponseEntity.ok(itemService.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemResponse> update(@PathVariable Long id, @Valid @RequestBody Item item) {
		return ResponseEntity.ok(itemService.updateById(id, item));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ItemResponse> delete(@PathVariable Long id) {
		return ResponseEntity.ok(itemService.deleteById(id));
	}
}
