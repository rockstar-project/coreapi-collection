package com.rockstar.collection.web;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.collection.domain.Item;

@Component
public class ItemResourceAssembler extends ResourceAssemblerSupport<Item, ItemResource> {
	
	public ItemResourceAssembler() {
		super(CollectionController.class, ItemResource.class);
	}
	
	public ItemResource toResource(Item item) {
		ItemResource itemResource = null;
		
		if (item != null) {
			itemResource = this.createResourceWithId(item.getId(), item);
			itemResource.setName(item.getName());
			itemResource.setLocation(item.getLocation());
		}
		return itemResource;
	}
	
	public Item fromResource(ItemResource itemResource) {
		Item item = null;
		
		if (itemResource != null) {
			item = new Item();
			item.setName(itemResource.getName());
			item.setLocation(itemResource.getLocation());
		}
		return item;
	}
}

