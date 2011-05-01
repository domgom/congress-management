package es.us.gescon.domain;

public enum Act {

	SPRINGER, IEEE, ACM, OTHER;
	
	 public String getLabel(){
	        return name();
	    }
}
