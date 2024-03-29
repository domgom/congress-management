// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.us.gescon.domain;

import es.us.gescon.domain.Congress;
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

privileged aspect Congress_Roo_Entity {
    
    declare @type: Congress: @Entity;
    
    @PersistenceContext
    transient EntityManager Congress.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Congress.id;
    
    @Version
    @Column(name = "version")
    private Integer Congress.version;
    
    public Long Congress.getId() {
        return this.id;
    }
    
    public void Congress.setId(Long id) {
        this.id = id;
    }
    
    public Integer Congress.getVersion() {
        return this.version;
    }
    
    public void Congress.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Congress.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Congress.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Congress attached = Congress.findCongress(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Congress.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Congress.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Congress Congress.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Congress merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Congress.entityManager() {
        EntityManager em = new Congress().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Congress.countCongresses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Congress o", Long.class).getSingleResult();
    }
    
    public static List<Congress> Congress.findAllCongresses() {
        return entityManager().createQuery("SELECT o FROM Congress o", Congress.class).getResultList();
    }
    
    public static Congress Congress.findCongress(Long id) {
        if (id == null) return null;
        return entityManager().find(Congress.class, id);
    }
    
    public static List<Congress> Congress.findCongressEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Congress o", Congress.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
