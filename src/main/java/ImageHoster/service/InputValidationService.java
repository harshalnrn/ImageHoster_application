package ImageHoster.service;

import java.util.regex.Pattern;

public class InputValidationService {


    public boolean checkPasswordStrength(String password) {


        boolean match = Pattern.matches("[a-zA-z&&0-9&&[^a-zA-z0-9]]", password);

        return match;

    }
}
