package com.atlasck.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "question")
@RooDbManaged(automaticallyDelete = true)
public class Question {

    @Column(name = "body", columnDefinition = "TEXT")
    @NotNull
    private String body;

    @Column(name = "visible")
    private Boolean visible = false;

    @Column(name = "email_answer")
    private Boolean emailAnswer = true;
}
