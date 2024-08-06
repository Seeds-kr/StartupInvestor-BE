package seeds.StartupInvestor.global.response.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import seeds.StartupInvestor.global.response.code.BaseErrorCode;
import seeds.StartupInvestor.global.response.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReasonDTO getErrorReason() {
        return this.errorCode.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.errorCode.getReasonHttpStatus();
    }
}
