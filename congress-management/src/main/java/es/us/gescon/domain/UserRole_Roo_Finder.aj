// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.us.gescon.domain;

import es.us.gescon.domain.UserRole;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserRole_Roo_Finder {
    
    public static TypedQuery<UserRole> UserRole.findUserRolesByEmailLike(String email) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        email = email.replace('*', '%');
        if (email.charAt(0) != '%') {
            email = "%" + email;
        }
        if (email.charAt(email.length() - 1) != '%') {
            email = email + "%";
        }
        EntityManager em = UserRole.entityManager();
        TypedQuery<UserRole> q = em.createQuery("SELECT o FROM UserRole AS o WHERE LOWER(o.email) LIKE LOWER(:email)", UserRole.class);
        q.setParameter("email", email);
        return q;
    }
    
    public static TypedQuery<UserRole> UserRole.findUserRolesByLogin(String login) {
        if (login == null || login.length() == 0) throw new IllegalArgumentException("The login argument is required");
        EntityManager em = UserRole.entityManager();
        TypedQuery<UserRole> q = em.createQuery("SELECT o FROM UserRole AS o WHERE o.login = :login", UserRole.class);
        q.setParameter("login", login);
        return q;
    }
    
    public static TypedQuery<UserRole> UserRole.findUserRolesByLoginLike(String login) {
        if (login == null || login.length() == 0) throw new IllegalArgumentException("The login argument is required");
        login = login.replace('*', '%');
        if (login.charAt(0) != '%') {
            login = "%" + login;
        }
        if (login.charAt(login.length() - 1) != '%') {
            login = login + "%";
        }
        EntityManager em = UserRole.entityManager();
        TypedQuery<UserRole> q = em.createQuery("SELECT o FROM UserRole AS o WHERE LOWER(o.login) LIKE LOWER(:login)", UserRole.class);
        q.setParameter("login", login);
        return q;
    }
    
    public static TypedQuery<UserRole> UserRole.findUserRolesByNameOrSurnameLike(String name, String surname) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        if (surname == null || surname.length() == 0) throw new IllegalArgumentException("The surname argument is required");
        surname = surname.replace('*', '%');
        if (surname.charAt(0) != '%') {
            surname = "%" + surname;
        }
        if (surname.charAt(surname.length() - 1) != '%') {
            surname = surname + "%";
        }
        EntityManager em = UserRole.entityManager();
        TypedQuery<UserRole> q = em.createQuery("SELECT o FROM UserRole AS o WHERE o.name = :name OR LOWER(o.surname) LIKE LOWER(:surname)", UserRole.class);
        q.setParameter("name", name);
        q.setParameter("surname", surname);
        return q;
    }
    
}