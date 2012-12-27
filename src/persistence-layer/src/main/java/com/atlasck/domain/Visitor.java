package com.atlasck.domain;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "visitor")
@RooDbManaged(automaticallyDelete = true)
public class Visitor {

    @PrePersist
    @PreUpdate
    public void updateRefDatetime() {
        if (getCreatedAt() == null) {
            setCreatedAt(new Date());
        }
        setUpdatedAt(new Date());
    }
}
