package com.atlasck.backend.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.sun.xml.internal.bind.v2.TODO;

@RooJavaBean
@RooToString
@RooEntity(versionField = "", table = "questions", schema = "atlasck")
@RooDbManaged(automaticallyDelete = true)
public class Questions {

	@Column(name = "question", columnDefinition="TEXT")
    private String question;

	@PreUpdate
	@PrePersist
	public void updateTimestamps() {
		setUpdatedAt(new Date());

		//TODO createdAt only on create record
		if(getCreatedAt() == null) {
			setCreatedAt(new Date());
		}
	}

	/**
	 * List witht questions without answers
	 *
	 * {@link TODO needs to be implemented}
	 */
	public void findQuestionsWithoutAnswer() {

	}
}
