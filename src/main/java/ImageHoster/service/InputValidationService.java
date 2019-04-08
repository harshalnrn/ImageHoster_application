package ImageHoster.service;

import java.util.regex.Pattern;

/**
 *
 */
public class InputValidationService {

    /**
     *
     * @param password
     * @return
     */
    public boolean checkPasswordStrength(String password) {


     //  boolean match = password.matches("^(?=.*[a-z]+)(?=.*[0-9]+)$");
//boolean match=password.matches(".*[a-zA-Z]+.*");
boolean match=password.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*");
        return match;

    }
}
