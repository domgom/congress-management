package es.us.gescon.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import es.us.gescon.domain.RoleType;
import es.us.gescon.domain.UserRole;

@Component
public class AuthService implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		UserRole user = UserRole.findUserRolesByLogin(username)
				.getSingleResult();
		if (user != null) {
			for(RoleType role : user.getRoles()){
				authorities.add(new GrantedAuthorityImpl(role.getValue()));	
			}
		}

		UserDetails result = new User(user.getLogin(), user.getPassword(),
				true, true, true, true, authorities);

		return result;

	}

	public void registerUser(UserRole user, RoleType role) throws DuplicateKeyException{
		Set<RoleType> roles = new HashSet<RoleType>();
		roles.add(role);
		user.setRoles(roles);
		// User does preexists in db
		if(UserRole.findUserRolesByLogin(user.getLogin()).getResultList().size() > 0){
			throw new DuplicateKeyException( "There is a user with this login already registered in the DB");
		}
		user.persist();	
		
	}
}