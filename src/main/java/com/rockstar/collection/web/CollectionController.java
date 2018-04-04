package com.rockstar.collection.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rockstar.collection.domain.Collection;
import com.rockstar.collection.domain.Item;
import com.rockstar.collection.service.CollectionService;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    
    @Autowired
    private CollectionResourceAssembler collectionResourceAssembler;
    
    @Autowired
    private ItemResourceAssembler itemResourceAssembler;

    @GetMapping
    public HttpEntity<List<CollectionResource>> getCollections(@RequestParam("slug") String slug) {
        List<Collection> collectionList = null;
		List<CollectionResource> collectionResourceList = null;
		
        collectionList = this.collectionService.search(slug);
        collectionResourceList = collectionResourceAssembler.toResources(collectionList);
        return new ResponseEntity<List<CollectionResource>>(collectionResourceList, HttpStatus.OK);
    }
    
    @PostMapping
    public HttpEntity<Void> createCollection(@Valid @RequestBody CollectionResource collectionResource) {
    		HttpHeaders headers = null;
    		Collection newCollection = null;
    		Collection updatedCollection = null;
    		
    		newCollection = this.collectionResourceAssembler.fromResource(collectionResource);
    		updatedCollection = this.collectionService.createCollection(newCollection);
    		headers = new HttpHeaders();
    		headers.setLocation(linkTo(CollectionController.class).slash(updatedCollection.getId()).toUri());
    		
    		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    	}

    @GetMapping(value="/{id}")
    public ResponseEntity<CollectionResource> getCollectionById(@PathVariable("id") String id) {
    		Collection collection = this.collectionService.getCollection(id);
		CollectionResource collectionResource = this.collectionResourceAssembler.toResource(collection);
		
		return new ResponseEntity<CollectionResource>(collectionResource, HttpStatus.OK);
    }

    @PatchMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCollection(@PathVariable("id") String id, @RequestBody Collection collection) {
    		collection.setId(id);
        this.collectionService.updateCollection(collection);
    }

    @DeleteMapping(value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable("id") String id) {
        this.collectionService.deleteCollection(id);
    }
    
    @RequestMapping(value="/{id}/items", method=RequestMethod.GET)
	public HttpEntity<List<ItemResource>> getCollectionItems(@PathVariable("id") String collectionId) {
		List<Item> collectionItems = this.collectionService.getItems(collectionId);
		List<ItemResource> itemResourceList = this.itemResourceAssembler.toResources(collectionItems);
		return new ResponseEntity<List<ItemResource>>(itemResourceList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/items", method=RequestMethod.POST)
	public HttpEntity<Void> createCollectionItem(@PathVariable("id") String collectionId, @RequestBody ItemResource itemResource) {
		HttpHeaders headers = null;
		Item updatedItem = null;
		Item item = this.itemResourceAssembler.fromResource(itemResource);
		item.setCollectionId(collectionId);
		updatedItem = this.collectionService.addItem(item);
		headers = new HttpHeaders();
		headers.setLocation(linkTo(CollectionController.class).slash(collectionId).slash("items").slash(updatedItem.getId().toString()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}/items/{item_id}", method=RequestMethod.GET)
	public HttpEntity<ItemResource> getCollectionItem(@PathVariable("id") String collectionId, @PathVariable("item_id") String itemId) {
		ItemResource itemResource = null;
		itemResource = this.itemResourceAssembler.toResource(this.collectionService.getItem(collectionId, itemId));
		return new ResponseEntity<ItemResource>(itemResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/items/{item_id}", method=RequestMethod.DELETE)
	public void deleteCollectionItem(@PathVariable("id") String collectionId, @PathVariable("item_id") String itemId) {
		Item item = new Item();
		item.setId(itemId);
		item.setCollectionId(collectionId);
		this.collectionService.removeItem(collectionId, itemId);
	}
	
}