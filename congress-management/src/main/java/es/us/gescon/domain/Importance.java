package es.us.gescon.domain;


public enum Importance {

    A_PLUS, A, B, C;
    
    public String getLabel(){
        return name();
    }
}
