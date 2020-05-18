package com.timesoft.hr.employeedata.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("ErrorMessage")
public class ErrorMessageResponse {
    @JsonIgnore
    private int statusCode;

    @ApiModelProperty("Timestamp")
    private Date timestamp;

    @ApiModelProperty("Exception")
    private String exception;

    @ApiModelProperty("message")
    private String message;

    @ApiModelProperty("errors")
    private List<FieldError> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldError {
        private String fieldPath;
        private String message;
    }
}
