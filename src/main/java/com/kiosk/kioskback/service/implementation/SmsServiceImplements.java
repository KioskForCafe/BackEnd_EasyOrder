package com.kiosk.kioskback.service.implementation;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiosk.kioskback.common.constants.ResponseMessage;
import com.kiosk.kioskback.dto.request.sms.MessageDto;
import com.kiosk.kioskback.dto.request.sms.PostSmsCheckDto;
import com.kiosk.kioskback.dto.request.sms.SmsDto;
import com.kiosk.kioskback.dto.response.ResponseDto;
import com.kiosk.kioskback.dto.response.sms.PostSmsCheckResponseDto;
import com.kiosk.kioskback.dto.response.sms.SmsResponseDto;
import com.kiosk.kioskback.entity.SmsCertificationEntity;
import com.kiosk.kioskback.repository.SmsCertificationRepository;
import com.kiosk.kioskback.service.SmsService;

@Service
public class SmsServiceImplements implements SmsService{

    @Value("${naver-cloud-sms.accessKey}")
	private String accessKey;
	
	@Value("${naver-cloud-sms.secretKey}")
	private String secretKey;
	
	@Value("${naver-cloud-sms.serviceId}")
	private String serviceId;
 
	@Value("${naver-cloud-sms.senderPhone}")
	private String phone;

	@Autowired private SmsCertificationRepository smsCertificationRepository;

    @Override
    public String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;
 
        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();
 
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
 
        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);
 
        return encodeBase64String;
	}

    @Override
    public SmsResponseDto postSms(String telNumber) throws HttpMessageNotReadableException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException, RestClientException, URISyntaxException {
		telNumber = telNumber.replaceAll("-","").replaceAll("=", "");
        Random random = new Random();
		String rand = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(random.nextInt(10));
			rand += ran;
		}

		SmsCertificationEntity sms = new SmsCertificationEntity(telNumber, rand);
		sms = smsCertificationRepository.save(sms);

		MessageDto messageDto = new MessageDto(telNumber, "Kiosk 본인인증 ["+rand+"]");

		Long time = System.currentTimeMillis();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ncp-apigw-timestamp", time.toString());
		headers.set("x-ncp-iam-access-key", accessKey);
		headers.set("x-ncp-apigw-signature-v2", makeSignature(time));
		
		List<MessageDto> messages = new ArrayList<>();
		messages.add(messageDto);
		
		SmsDto request = SmsDto.builder()
				.type("SMS")
				.contentType("COMM")
				.countryCode("82")
				.from(phone)
				.content(messageDto.getContent())
				.messages(messages)
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(request);
		HttpEntity<String> httpBody = new HttpEntity<>(body, headers);
		
		RestTemplate restTemplate = new RestTemplate();
	    SmsResponseDto response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponseDto.class);
	    return response;
    }

	@Override
	public ResponseDto<PostSmsCheckResponseDto> postSmsCheck(PostSmsCheckDto dto) {
		PostSmsCheckResponseDto data = null;
		String code = dto.getAuthenticationCode();
		String telNumber = dto.getTelNumber();
		telNumber = telNumber.replaceAll("-","").replaceAll("=", "");
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		try {
			
			SmsCertificationEntity smsCertificationEntity = smsCertificationRepository.findByTelNumberAndAuthenticationCode(telNumber,code);
			if(smsCertificationEntity == null) return ResponseDto.setFailed(ResponseMessage.FAIL_AUTHENTICATION_CODE);
			Date createdAt = smsCertificationEntity.getCreatedAt();
			Calendar expiredAt = Calendar.getInstance();
			expiredAt.setTime(createdAt);
			expiredAt.add(Calendar.SECOND,60);
			int result = expiredAt.compareTo(now);
			if(result < 0) return ResponseDto.setFailed(ResponseMessage.FAIL_AUTHENTICATION_CODE);

			data = new PostSmsCheckResponseDto(true);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
	}
    
    
}
