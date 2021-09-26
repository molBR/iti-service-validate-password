package br.com.iti.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties({"hasLengthCharacter","hasDigit","hasLetterUpper",
        "hasLetterLower","hasSpecialCharacter","hasRepeatedCharacter","hasSpaceCharacter"})
public class ResponseDTO {

    @JsonProperty("valid_password")
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
