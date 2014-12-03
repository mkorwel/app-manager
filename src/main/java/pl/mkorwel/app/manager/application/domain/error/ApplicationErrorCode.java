package pl.mkorwel.app.manager.application.domain.error;

import pl.mkorwel.app.manager.base.usecase.model.response.ErrorCode;

public enum ApplicationErrorCode implements ErrorCode {
	
	WRONG_STATE, NAME_IS_REQUIRED, REASON_IS_REQUIRED, CONTENT_IS_REQUIRED;
}
