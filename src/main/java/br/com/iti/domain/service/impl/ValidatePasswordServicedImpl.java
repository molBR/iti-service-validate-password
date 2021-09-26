package br.com.iti.domain.service.impl;

import br.com.iti.domain.model.ResponseDTO;
import br.com.iti.domain.service.ValidatePasswordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatePasswordServicedImpl implements ValidatePasswordService {

    @Value("${validate.password.minimum.length:9}")
    private int lengthCharacter;

    @Value("${validate.password.characterSpecial:@!#$%¨&*}")
    private String listCharacterSpecial;

    @Override
    public ResponseDTO validate(String password) {

        ResponseDTO response = ResponseDTO.builder()
                .validPassword(false)
                .hasLengthCharacter(hasLengthCharacter(password))
                .hasDigit(hasDigit(password))
                .hasLetterUpper(hasLetterUpper(password))
                .hasLetterLower(hasLetterLower(password))
                .hasSpecialCharacter(hasSpecialCharacter(password))
                .hasSpaceCharacter(hasSpaceCharacter(password))
                .hasRepeatedCharacter(hasRepeatedCharacter(password))
                .build();

        response.validate();
        return response;
    }

    private Boolean hasRepeatedCharacter(String password) {
        StringBuilder read = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char charAt = password.charAt(i);
            if(read.toString().indexOf(charAt) != -1){
                return true;
            }
            read.append(charAt);
        }
        return false;
    }

    private Boolean hasSpecialCharacter(String password) {
        Pattern special = Pattern.compile ("["+ listCharacterSpecial+"]");
        Matcher hasSpecial = special.matcher(password);
        return hasSpecial.find();
    }

    private Boolean hasLetterLower(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private Boolean hasLetterUpper(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private Boolean hasDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private Boolean hasLengthCharacter(String password) {
        return password.length() >= lengthCharacter;
    }

    private Boolean hasSpaceCharacter(String password){
        return password.chars().anyMatch(Character::isSpaceChar) || password.isEmpty();
    }
}
