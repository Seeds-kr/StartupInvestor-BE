package seeds.StartupInvestor.global.response.exception.handler;

import seeds.StartupInvestor.global.response.exception.GeneralException;
import seeds.StartupInvestor.global.response.code.BaseErrorCode;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
