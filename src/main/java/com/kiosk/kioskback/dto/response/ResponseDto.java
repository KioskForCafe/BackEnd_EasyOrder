package com.kiosk.kioskback.dto.response;

import com.kiosk.kioskback.common.constants.ResponseMessage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Response Format")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    @Schema(description = "작업 결과 상태", example = "true", required = true)
    private boolean result;

    @Schema(description = "작업 결과 메세지", example = ResponseMessage.SUCCESS, required = true)
    private String message;
    
    @Schema(description = "작업 결과 데이터", required = true)
    private D data;

    public static <D> ResponseDto<D> setSuccess(String message, D data){
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message){
        return ResponseDto.set(false,message,null);
    }
}