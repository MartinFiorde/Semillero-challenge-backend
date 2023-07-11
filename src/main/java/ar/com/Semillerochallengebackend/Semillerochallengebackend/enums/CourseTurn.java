package ar.com.Semillerochallengebackend.Semillerochallengebackend.enums;

public enum CourseTurn {
    MORNING("A la mañana"), 
    AFTERNOON("A la tarde"), 
    NIGHT("A la noche");

    private final String displayValue;
    
    private CourseTurn(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
