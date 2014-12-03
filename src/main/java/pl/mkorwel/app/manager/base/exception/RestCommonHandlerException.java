package pl.mkorwel.app.manager.base.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.mkorwel.app.manager.base.usecase.model.response.ErrorCode;

@ControllerAdvice
public class RestCommonHandlerException {

	@ExceptionHandler(value = ApplicationException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<ErrorCode> defaultErrorHandler(ApplicationException e)
			throws Exception {
		return e.getErrors();
	}
}
