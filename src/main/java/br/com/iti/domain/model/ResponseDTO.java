package br.com.iti.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder(value = {"valid_password", "has_length_character","has_digit","has_letter_upper",
        "has_letter_lower","has_special_character","has_repeated_character","has_space_character"})
public class ResponseDTO {

    @JsonProperty("valid_password")
    private Boolean validPassword;

    @JsonProperty("has_length_character")
    private Boolean hasLengthCharacter;

    @JsonProperty("has_digit")
    private Boolean hasDigit;

    @JsonProperty("has_letter_upper")
    private Boolean hasLetterUpper;

    @JsonProperty("has_letter_lower")
    private Boolean hasLetterLower;

    @JsonProperty("has_special_character")
    private Boolean hasSpecialCharacter;

    @JsonProperty("has_repeated_character")
    private Boolean hasRepeatedCharacter;

    @JsonProperty("has_space_character")
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
