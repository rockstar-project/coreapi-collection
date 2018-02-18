package com.rockstar.collection.domain;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="COLLECTION")
public class Collection {
	
	@Id
	@Column(name="ID")
    private String id;
	
	@Column(name="SLUG")
    private String slug;
	
	@Column(name="TITLE")
    private String title;
	
	@Column(name="DESCRIPTION")
    private String description;
	
	@Column(name="IMAGE")
    private String image;
	
	@Column(name="VISIBILITY")
	private String visibility;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="COLLECTION_ID")
    private Set<Item> items;
    
    public Collection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	@PrePersist
	public void assignId() {
		if (this.getId() == null || this.getId().isEmpty()) {
			this.setId(UUID.randomUUID().toString());
		}
	}
	
}