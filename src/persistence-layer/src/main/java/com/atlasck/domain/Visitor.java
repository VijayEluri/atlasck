package com.atlasck.domain;
import javax.persistence.Column;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "visitor")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "questions" })
public class Visitor {

    @Column(name = "ip_address", length = 255)
    private String ipAddress;
}
