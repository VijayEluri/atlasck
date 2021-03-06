// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.atlasck.backend.domain;

import com.atlasck.backend.domain.CurrentVersion;
import java.lang.Integer;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CurrentVersion_Roo_Entity {
    
    declare @type: CurrentVersion: @Entity;
    
    declare @type: CurrentVersion: @Table(name = "current_version", schema = "atlasck");
    
    @PersistenceContext
    transient EntityManager CurrentVersion.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer CurrentVersion.id;
    
    public Integer CurrentVersion.getId() {
        return this.id;
    }
    
    public void CurrentVersion.setId(Integer id) {
        this.id = id;
    }
    
    @Transactional
    public void CurrentVersion.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void CurrentVersion.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CurrentVersion attached = CurrentVersion.findCurrentVersion(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void CurrentVersion.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void CurrentVersion.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public CurrentVersion CurrentVersion.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CurrentVersion merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager CurrentVersion.entityManager() {
        EntityManager em = new CurrentVersion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long CurrentVersion.countCurrentVersions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CurrentVersion o", Long.class).getSingleResult();
    }
    
    public static List<CurrentVersion> CurrentVersion.findAllCurrentVersions() {
        return entityManager().createQuery("SELECT o FROM CurrentVersion o", CurrentVersion.class).getResultList();
    }
    
    public static CurrentVersion CurrentVersion.findCurrentVersion(Integer id) {
        if (id == null) return null;
        return entityManager().find(CurrentVersion.class, id);
    }
    
    public static List<CurrentVersion> CurrentVersion.findCurrentVersionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CurrentVersion o", CurrentVersion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
