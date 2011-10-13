package com.atlasck.backend.domain;

import javax.persistence.Column;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(versionField = "", table = "questions", schema = "atlasck")
@RooDbManaged(automaticallyDelete = true)
public class Questions {

	@Column(name = "question", columnDefinition="TEXT")
    private String question;
}
