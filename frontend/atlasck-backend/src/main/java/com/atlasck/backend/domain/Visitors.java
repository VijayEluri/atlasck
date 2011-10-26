package com.atlasck.backend.domain;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(versionField = "", table = "visitors", schema = "atlasck", entityName="Visitor")
@RooDbManaged(automaticallyDelete = true)
public class Visitors {

	@PreUpdate
	@PrePersist
	public void updateTimestamps() {
		setUpdatedAt(new Date());

		//TODO createdAt only on create record
		if(getCreatedAt() == null) {
			setCreatedAt(new Date());
		}
	}
}
