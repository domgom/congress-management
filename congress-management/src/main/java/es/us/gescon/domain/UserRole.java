package es.us.gescon.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import es.us.gescon.domain.Exposition;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import es.us.gescon.domain.Congress;
import es.us.gescon.domain.RoleType;
import javax.persistence.ElementCollection;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findUserRolesByLoginLike", "findUserRolesByNameOrSurnameLike", "findUserRolesByEmailLike", "findUserRolesByLogin" })
public class UserRole {

    private String name;

    private String surname;

    private String login;

    private String password;

    private String email;

    private String profile;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Exposition> expositions = new HashSet<Exposition>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Congress> congresses = new HashSet<Congress>();

    @ElementCollection
    private Set<RoleType> roles = new HashSet<RoleType>();
}
