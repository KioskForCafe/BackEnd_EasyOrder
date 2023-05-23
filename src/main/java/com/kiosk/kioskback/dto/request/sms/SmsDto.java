package com.kiosk.kioskback.dto.request.sms;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDto {
    private String type;
	private String contentType;
	private String countryCode;
	private String from;
	private String content;
	private List<MessageDto> messages;
}
