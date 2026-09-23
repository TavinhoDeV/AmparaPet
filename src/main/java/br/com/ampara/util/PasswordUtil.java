package br.com.ampara.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hash(String senhaTextoPuro) {
        return BCrypt.hashpw(senhaTextoPuro, BCrypt.gensalt(12));
    }

    public static boolean confere(String senhaTextoPuro, String senhaHash) {
        return BCrypt.checkpw(senhaTextoPuro, senhaHash);
    }
}
