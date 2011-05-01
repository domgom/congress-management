// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.us.gescon.domain;

import es.us.gescon.domain.Exposition;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Exposition_Roo_Entity {
    
    declare @type: Exposition: @Entity;
    
    @PersistenceContext
    transient EntityManager Exposition.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Exposition.id;
    
    @Version
    @Column(name = "version")
    private Integer Exposition.version;
    
    public Long Exposition.getId() {
        return this.id;
    }
    
    public void Exposition.setId(Long id) {
        this.id = id;
    }
    
    public Integer Exposition.getVersion() {
        return this.version;
    }
    
    public void Exposition.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Exposition.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Exposition.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Exposition attached = Exposition.findExposition(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Exposition.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Exposition.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Exposition Exposition.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Exposition merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Exposition.entityManager() {
        EntityManager em = new Exposition().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Exposition.countExpositions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Exposition o", Long.class).getSingleResult();
    }
    
    public static List<Exposition> Exposition.findAllExpositions() {
        return entityManager().createQuery("SELECT o FROM Exposition o", Exposition.class).getResultList();
    }
    
    public static Exposition Exposition.findExposition(Long id) {
        if (id == null) return null;
        return entityManager().find(Exposition.class, id);
    }
    
    public static List<Exposition> Exposition.findExpositionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Exposition o", Exposition.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}