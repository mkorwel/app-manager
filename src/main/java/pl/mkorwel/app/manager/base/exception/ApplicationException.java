package pl.mkorwel.app.manager.base.exception;

import java.util.List;

import pl.mkorwel.app.manager.base.usecase.model.response.ErrorCode;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<ErrorCode> errors;

	public ApplicationException(List<ErrorCode> errors){
		this.errors = errors;
	}
 	
	public List<ErrorCode> getErrors() {
		return errors;
	}
}
