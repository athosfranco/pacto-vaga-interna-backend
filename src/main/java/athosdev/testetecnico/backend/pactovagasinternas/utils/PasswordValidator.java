package athosdev.testetecnico.backend.pactovagasinternas.utils;

import java.util.regex.Pattern;

public class PasswordValidator {

    //REGRAS DE VALIDAÇÃO DA SENHA: 8 CARACTERES, POSSUI PELO MENOS 1 LETRA E 1 NUMERO
    private static final int MIN_LENGTH = 8;
    private static final Pattern CONTAINS_LETTER = Pattern.compile(".*[a-zA-Z].*");
    private static final Pattern CONTAINS_NUMBER = Pattern.compile(".*\\d.*");

    public static boolean isValid(String password) {
        return password.length() >= MIN_LENGTH &&
                CONTAINS_LETTER.matcher(password).matches() &&
                CONTAINS_NUMBER.matcher(password).matches();
    }



}
