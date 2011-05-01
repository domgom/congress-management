package es.us.gescon.domain;


public enum RoleType {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");
    
    private String role;
    
    private RoleType(String role){
    	this.role = role;
    }
    public String getValue(){
    	return role;
    }
}
