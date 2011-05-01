package es.us.gescon.domain;


public enum Recurrence {

    SIX_MONTHS, ONE_YEAR, TWO_YEARS, THREE_YEARS;
    
    public String getLabel(){
        return name();
    }
}
