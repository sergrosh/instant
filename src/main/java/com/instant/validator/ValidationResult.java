package com.instant.validator;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sroshchupkin
 */

@ToString
public class ValidationResult {

    public enum ResultCode{SUCCESS, ERROR}

    @Getter
    private ResultCode resultCode;

    @Getter
    private List<String> messages;

    private ValidationResult(){}

    public boolean isSuccessfull(){return resultCode==ResultCode.SUCCESS;}

    public boolean isFailed() {
        return resultCode == ResultCode.ERROR;
    }

    public static class Builder {

        protected ValidationResult result;

        public Builder(List<String> messages) {
            result = new ValidationResult();
            result.messages = messages;
        }

        public ValidationResult build() {

            if (CollectionUtils.isEmpty(result.messages)) {
                result.resultCode = ResultCode.SUCCESS;
            } else {
                result.resultCode = ResultCode.ERROR;
            }
            ;
            return result;
        }
    }
}
