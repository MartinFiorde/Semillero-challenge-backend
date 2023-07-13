package ar.com.Semillerochallengebackend.Semillerochallengebackend.enums;

public enum CourseTurnEnum {
    MORNING("A la mañana"), 
    AFTERNOON("A la tarde"), 
    NIGHT("A la noche");

    private final String displayValue;
    
    private CourseTurnEnum(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
