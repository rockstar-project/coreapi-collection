package com.rockstar.collection.service;

import java.util.List;

import com.rockstar.collection.domain.Collection;
import com.rockstar.collection.domain.Item;

public interface CollectionService {
	
	public Collection getCollection(String identifier);
	public Collection createCollection(Collection collection);
	public void updateCollection(Collection collection);
	public void deleteCollection(String identifier);
	public List<Collection> search(String slug);
	
	public List<Item> getItems(String collectionId);
	public Item getItem(String collectionId, String itemId);
	public Item addItem(Item item);
	public void removeItem(String collectionId, String itemId);

}
