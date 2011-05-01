// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.us.gescon.domain;

import es.us.gescon.domain.Congress;
import es.us.gescon.domain.Importance;
import java.lang.String;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Congress_Roo_Finder {
    
    public static TypedQuery<Congress> Congress.findCongressesByNameLikeOrUrlLikeOrMatterLikeOrLocationLikeOrEmailLike(String name, String url, String matter, String location, String email) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        if (url == null || url.length() == 0) throw new IllegalArgumentException("The url argument is required");
        url = url.replace('*', '%');
        if (url.charAt(0) != '%') {
            url = "%" + url;
        }
        if (url.charAt(url.length() - 1) != '%') {
            url = url + "%";
        }
        if (matter == null || matter.length() == 0) throw new IllegalArgumentException("The matter argument is required");
        matter = matter.replace('*', '%');
        if (matter.charAt(0) != '%') {
            matter = "%" + matter;
        }
        if (matter.charAt(matter.length() - 1) != '%') {
            matter = matter + "%";
        }
        if (location == null || location.length() == 0) throw new IllegalArgumentException("The location argument is required");
        location = location.replace('*', '%');
        if (location.charAt(0) != '%') {
            location = "%" + location;
        }
        if (location.charAt(location.length() - 1) != '%') {
            location = location + "%";
        }
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        email = email.replace('*', '%');
        if (email.charAt(0) != '%') {
            email = "%" + email;
        }
        if (email.charAt(email.length() - 1) != '%') {
            email = email + "%";
        }
        EntityManager em = Congress.entityManager();
        TypedQuery<Congress> q = em.createQuery("SELECT o FROM Congress AS o WHERE LOWER(o.name) LIKE LOWER(:name)  OR LOWER(o.url) LIKE LOWER(:url)  OR LOWER(o.matter) LIKE LOWER(:matter)  OR LOWER(o.location) LIKE LOWER(:location)  OR LOWER(o.email) LIKE LOWER(:email)", Congress.class);
        q.setParameter("name", name);
        q.setParameter("url", url);
        q.setParameter("matter", matter);
        q.setParameter("location", location);
        q.setParameter("email", email);
        return q;
    }
    
    public static TypedQuery<Congress> Congress.findCongressesByOpeningBetween(Date minOpening, Date maxOpening) {
        if (minOpening == null) throw new IllegalArgumentException("The minOpening argument is required");
        if (maxOpening == null) throw new IllegalArgumentException("The maxOpening argument is required");
        EntityManager em = Congress.entityManager();
        TypedQuery<Congress> q = em.createQuery("SELECT o FROM Congress AS o WHERE o.opening BETWEEN :minOpening AND :maxOpening", Congress.class);
        q.setParameter("minOpening", minOpening);
        q.setParameter("maxOpening", maxOpening);
        return q;
    }
    
    public static TypedQuery<Congress> Congress.findCongressesByOpeningBetweenAndUrlLikeAndMatterLikeAndLikeAndLocationLikeAndNameLikeAndImportance(Date minOpening, Date maxOpening, String url, String matter, String location, String name, Importance importance) {
        if (minOpening == null) throw new IllegalArgumentException("The minOpening argument is required");
        if (maxOpening == null) throw new IllegalArgumentException("The maxOpening argument is required");
        if (url == null || url.length() == 0) throw new IllegalArgumentException("The url argument is required");
        url = url.replace('*', '%');
        if (url.charAt(0) != '%') {
            url = "%" + url;
        }
        if (url.charAt(url.length() - 1) != '%') {
            url = url + "%";
        }
        if (matter == null || matter.length() == 0) throw new IllegalArgumentException("The matter argument is required");
        matter = matter.replace('*', '%');
        if (matter.charAt(0) != '%') {
            matter = "%" + matter;
        }
        if (matter.charAt(matter.length() - 1) != '%') {
            matter = matter + "%";
        }
        if (location == null || location.length() == 0) throw new IllegalArgumentException("The location argument is required");
        location = location.replace('*', '%');
        if (location.charAt(0) != '%') {
            location = "%" + location;
        }
        if (location.charAt(location.length() - 1) != '%') {
            location = location + "%";
        }
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        if (importance == null) throw new IllegalArgumentException("The importance argument is required");
        EntityManager em = Congress.entityManager();
        TypedQuery<Congress> q = em.createQuery("SELECT o FROM Congress AS o WHERE o.opening BETWEEN :minOpening AND :maxOpening  AND LOWER(o.url) LIKE LOWER(:url)  AND LOWER(o.matter) LIKE LOWER(:matter)  AND  LIKE LOWER(:matter)  AND LOWER(o.location) LIKE LOWER(:location)  AND LOWER(o.name) LIKE LOWER(:name)  AND o.importance = :importance", Congress.class);
        q.setParameter("minOpening", minOpening);
        q.setParameter("maxOpening", maxOpening);
        q.setParameter("url", url);
        q.setParameter("matter", matter);
        q.setParameter("location", location);
        q.setParameter("name", name);
        q.setParameter("importance", importance);
        return q;
    }
    
    public static TypedQuery<Congress> Congress.findCongressesByOpeningBetweenOrUrlLikeOrMatterLikeOrLocationLikeOrNameLikeOrImportance(Date minOpening, Date maxOpening, String url, String matter, String location, String name, Importance importance) {
        if (minOpening == null) throw new IllegalArgumentException("The minOpening argument is required");
        if (maxOpening == null) throw new IllegalArgumentException("The maxOpening argument is required");
        if (url == null || url.length() == 0) throw new IllegalArgumentException("The url argument is required");
        url = url.replace('*', '%');
        if (url.charAt(0) != '%') {
            url = "%" + url;
        }
        if (url.charAt(url.length() - 1) != '%') {
            url = url + "%";
        }
        if (matter == null || matter.length() == 0) throw new IllegalArgumentException("The matter argument is required");
        matter = matter.replace('*', '%');
        if (matter.charAt(0) != '%') {
            matter = "%" + matter;
        }
        if (matter.charAt(matter.length() - 1) != '%') {
            matter = matter + "%";
        }
        if (location == null || location.length() == 0) throw new IllegalArgumentException("The location argument is required");
        location = location.replace('*', '%');
        if (location.charAt(0) != '%') {
            location = "%" + location;
        }
        if (location.charAt(location.length() - 1) != '%') {
            location = location + "%";
        }
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        if (importance == null) throw new IllegalArgumentException("The importance argument is required");
        EntityManager em = Congress.entityManager();
        TypedQuery<Congress> q = em.createQuery("SELECT o FROM Congress AS o WHERE o.opening BETWEEN :minOpening AND :maxOpening  OR LOWER(o.url) LIKE LOWER(:url)  OR LOWER(o.matter) LIKE LOWER(:matter)  OR LOWER(o.location) LIKE LOWER(:location)  OR LOWER(o.name) LIKE LOWER(:name)  OR o.importance = :importance", Congress.class);
        q.setParameter("minOpening", minOpening);
        q.setParameter("maxOpening", maxOpening);
        q.setParameter("url", url);
        q.setParameter("matter", matter);
        q.setParameter("location", location);
        q.setParameter("name", name);
        q.setParameter("importance", importance);
        return q;
    }
    
}
