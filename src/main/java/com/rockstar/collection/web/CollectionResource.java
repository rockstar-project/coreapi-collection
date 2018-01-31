package com.rockstar.collection.web;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CollectionResource extends ResourceSupport {
	
	@NotEmpty
    @Size(max=25)
    private String slug;
    
	@NotEmpty
    @Size(max=50)
    private String title;
    
    @Size(max=100)
    private String description;
    private String image;
    private List<ItemResource> items;
    
    public CollectionResource() {
    }

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ItemResource> getItems() {
		return items;
	}

	public void setItems(List<ItemResource> items) {
		this.items = items;
	}

}
