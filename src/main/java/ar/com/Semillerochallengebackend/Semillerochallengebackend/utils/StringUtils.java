package ar.com.Semillerochallengebackend.Semillerochallengebackend.utils;

public final class StringUtils {

    public static boolean nullOrEmpty(String string) {
        return (string == null ? false : ((string.equals("") || string.trim().equals(""))));
    }
}
