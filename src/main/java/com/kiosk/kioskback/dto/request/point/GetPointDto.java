package com.kiosk.kioskback.dto.request.point;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPointDto {
    @NotBlank
    @Length(min=11, max=13)
    private String telNumber;
}
