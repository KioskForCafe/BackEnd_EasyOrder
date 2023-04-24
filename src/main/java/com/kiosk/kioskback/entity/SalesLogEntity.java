package com.kiosk.kioskback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesLogEntity {

    private int salesLogId;
    private int storeId;
    private String createdAt;
    private String salesLogcol;
    
}
