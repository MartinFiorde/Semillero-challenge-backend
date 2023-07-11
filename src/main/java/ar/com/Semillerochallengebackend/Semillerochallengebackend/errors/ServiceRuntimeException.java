package ar.com.Semillerochallengebackend.Semillerochallengebackend.errors;

public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String msn) {
        super(msn);
    }
}