package com.rockstar.collection.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	List<Item> findByCollectionId(String collectionId);
	public Item findByCollectionIdAndName(String collectionId, String name);

}
