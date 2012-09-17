package com.atlasck.backend.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooEntity(versionField = "", table = "answers", schema = "atlasck", entityName = "Answer", finders = { "findAnswersesByQuestionsId" })
public class Answers {

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @PreUpdate
    @PrePersist
    public void updateTimestamps() {
        setUpdatedAt(new Date());
        if (getCreatedAt() == null) {
            setCreatedAt(new Date());
        }
    }
}
