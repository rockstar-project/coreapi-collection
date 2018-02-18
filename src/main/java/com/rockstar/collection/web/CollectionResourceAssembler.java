package com.rockstar.collection.web;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.rockstar.collection.domain.Collection;

@Component
public class CollectionResourceAssembler extends ResourceAssemblerSupport<Collection, CollectionResource> {
	
	public CollectionResourceAssembler() {
		super(CollectionController.class, CollectionResource.class);
	}
	
	public CollectionResource toResource(Collection collection) {
		CollectionResource collectionResource = null;
		
		if (collection != null) {
			collectionResource = this.createResourceWithId(collection.getId(), collection);
			collectionResource.setSlug(collection.getSlug());
			collectionResource.setTitle(collection.getTitle());
			collectionResource.setDescription(collection.getDescription());
			collectionResource.setImage(collection.getImage());
			collectionResource.setVisibility(collection.getVisibility());
		}
		return collectionResource;
	}
	
	public Collection fromResource(CollectionResource collectionResource) {
		Collection collection = null;
		
		if (collectionResource != null) {
			collection = new Collection();
			collection.setSlug(collectionResource.getSlug());
			collection.setTitle(collectionResource.getTitle());
			collection.setDescription(collectionResource.getDescription());
			collection.setImage(collectionResource.getImage());
			collection.setVisibility(collectionResource.getVisibility());
		}
		return collection;
	}
}

