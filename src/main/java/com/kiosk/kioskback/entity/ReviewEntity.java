package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    private int reviewId;
    private String reviewContent;
    private String createdAt;
    private int orderId;
    
}
