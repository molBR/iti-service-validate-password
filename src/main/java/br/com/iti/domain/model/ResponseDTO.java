package br.com.iti.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

    private Boolean validPassword;
    private Boolean hasLengthCharacter;
    private Boolean hasDigit;
    private Boolean hasLetterUpper;
    private Boolean hasLetterLower;
    private Boolean hasSpecialCharacter;
    private Boolean hasRepeatedCharacter;
    private Boolean hasSpaceCharacter;

    public void validate() {
        validPassword = hasLengthCharacter &&
                hasDigit &&
                hasLetterUpper &&
                hasLetterLower &&
                hasSpecialCharacter &&
                !hasRepeatedCharacter &&
                !hasSpaceCharacter;
    }
}
