package com.rockstar.collection.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.rockstar.collection.service.CollectionService;
import com.rockstar.collection.service.NotFoundException;
import com.rockstar.collection.service.NotUniqueException;

@Service
@Transactional(readOnly=true)
public class CollectionServiceImpl implements CollectionService {
	
	@Autowired private CollectionRepository collectionRepository;
	@Autowired private ItemRepository itemRepository;
	
	public List<Collection> search(String slug) {
		return this.collectionRepository.findBySlug(slug);
	}
	
	public Collection getCollection(String collectionId) {
		return this.retrieveCollectionById(collectionId);
	}
	
	@Transactional(readOnly=false)
	public Collection createCollection(Collection collection) {
		Collection updatedCollection = null;
		if (collection != null) {
			this.validateUniqueCollection(collection);
			updatedCollection = this.collectionRepository.save(collection);
		}
		return updatedCollection;
	}
	
	@Transactional(readOnly=false)
	public void updateCollection(Collection collection) {
		Collection currentCollection = null;
		Boolean modifyFlag = false;
		currentCollection = this.retrieveCollectionById(collection.getId());

		if (StringUtils.hasText(collection.getSlug())) {
			currentCollection.setSlug(collection.getSlug());
			modifyFlag = true;
		}
		if (StringUtils.hasText(collection.getTitle())) {
			currentCollection.setTitle(collection.getTitle());
			modifyFlag = true;
		}
		if (StringUtils.hasText(collection.getDescription())) {
			currentCollection.setDescription(collection.getDescription());
			modifyFlag = true;
		}
		if (StringUtils.hasText(collection.getImage())) {
			currentCollection.setImage(collection.getImage());
			modifyFlag = true;
		}
		if (StringUtils.hasText(collection.getVisibility())) {
			currentCollection.setVisibility(collection.getVisibility());
			modifyFlag = true;
		}
		if (modifyFlag) {
			this.collectionRepository.save(currentCollection);
		}
	}
	
	@Transactional(readOnly=false)
	public void deleteCollection(String collectionId) {
		this.collectionRepository.delete(this.retrieveCollectionById(collectionId));
	}
	
	private void validateUniqueCollection(Collection collection) {
		List<Collection> persistedCollectionList = null;
		
		if (collection != null) {
			persistedCollectionList = this.collectionRepository.findBySlug(collection.getSlug());
			if (persistedCollectionList != null && !persistedCollectionList.isEmpty()) {
				throw new NotUniqueException("collection");
			}
		}
	}
	
	private Collection retrieveCollectionById(String collectionId) {
		Collection collection = this.collectionRepository.findOne(collectionId);
		if (collection == null) {
			throw new NotFoundException("collection");
		}
		return collection;
	}
	
	public List<Item> getItems(String collectionId) {
		return this.itemRepository.findByCollectionId(collectionId);
	}

	public Item getItem(String collectionId, String itemId) {
		Item existingItem = null;
		
		this.getCollection(collectionId);
		
		existingItem = this.retrieveItemById(itemId);
		
		if (existingItem == null) {
			throw new NotFoundException("item");
		}
		
		return existingItem;
	}
	
	@Transactional(readOnly=false)
	public Item addItem(Item item) {
		Item  updatedItem = null;
		if (item != null) {
			this.validateUniqueItem(item);
			updatedItem = this.itemRepository.save(item);
		}
		return updatedItem;
	}
	
	@Transactional(readOnly=false)
	public void removeItem(String collectionId, String itemId) {
		this.getCollection(collectionId);
		this.itemRepository.delete(this.retrieveItemById(itemId));
	}

	private void validateUniqueItem(Item item) {
		Item persistedItem = null;
		
		persistedItem = this.itemRepository.findByCollectionIdAndName(item.getCollectionId(), item.getName());
		if (persistedItem != null) {
			throw new NotUniqueException("item");
		}
	}
	
	private Item retrieveItemById(String itemId) {
		Item item = this.itemRepository.findOne(itemId);
		if (item == null) {
			throw new NotFoundException("item");
		}
		return item;
	}
}