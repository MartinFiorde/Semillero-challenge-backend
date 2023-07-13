package ar.com.Semillerochallengebackend.Semillerochallengebackend.enums;

// Como usar Enums en el front para generar listas desplegables: https://www.baeldung.com/thymeleaf-enums
// Otro tutorial del tema: https://frontbackend.com/thymeleaf/using-enums-in-thymeleaf
public enum CourseStatusEnum {
    PROGRAMED("Programado"), 
    IN_PROGRESS("En progreso"), 
    CANCELLED("Cancelado"), 
    FINISHED("Terminado");

    private final String displayValue;
    
    private CourseStatusEnum(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
