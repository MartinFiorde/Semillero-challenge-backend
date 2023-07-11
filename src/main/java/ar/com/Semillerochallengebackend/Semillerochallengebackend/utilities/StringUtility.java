package ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.User;

public final class StringUtility {

    public static boolean notNullEmpty(String string) {
        return (string == null || string.equals("") || string.trim().equals(""));
    }

    public static String fullName(User user) {
        return (user.getLastName() + " " + user.getFirstName());
    }
}
