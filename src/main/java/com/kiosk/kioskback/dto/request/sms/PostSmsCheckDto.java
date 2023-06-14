package com.kiosk.kioskback.dto.request.sms;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSmsCheckDto {
    @NotBlank
    @Length(min=11, max=13)
    private String telNumber;

    @NotBlank
    @Length(min = 6)
    private String authenticationCode;
}
