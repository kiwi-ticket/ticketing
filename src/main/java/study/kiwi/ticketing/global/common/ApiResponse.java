package study.kiwi.ticketing.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import study.kiwi.ticketing.global.codes.SuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    /**
     * 요청 성공 시 응답 생성
     */
    public static<T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessCode.SUCCESS.getCode(), SuccessCode.SUCCESS.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(SuccessCode code, T result){
        return new ApiResponse<>(true, code.getCode() , code.getMessage(), result);
    }

    /**
     * 요청 실패 시 응답 생성
     */
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}