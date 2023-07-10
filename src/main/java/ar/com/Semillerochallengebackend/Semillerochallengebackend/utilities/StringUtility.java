package ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities;

public final class StringUtility {

	public static boolean notNullEmpty(String string) {
		return (string == null || string.equals("") || string.trim().equals(""));
	}
	
}