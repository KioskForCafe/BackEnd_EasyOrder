package com.kiosk.kioskback.dto.request.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPointDto {
    private String telNumber;
    private int value;
    private boolean type;
    private int currentPoint;
}
