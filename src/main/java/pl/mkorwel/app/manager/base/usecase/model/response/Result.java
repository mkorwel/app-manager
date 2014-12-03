package pl.mkorwel.app.manager.base.usecase.model.response;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Result<T> {

	private final T result;
	
	private final List<ErrorCode> errors;
	
	public static final Result<Void> EMPTY_RESULT = new Result<>();
	
	private Result(){
		result = null;
		errors = null;
	}
	
	public Result(T result) {
		this.result = result;
		errors = null;
	}
	
	public Result(List<? extends ErrorCode> errors) {
		result = null;
		this.errors = Collections.unmodifiableList(errors);
	}
	
	public Result(ErrorCode error) {
		result = null;
		List<ErrorCode> errors = new LinkedList<>();
		errors.add(error);
		this.errors = Collections.unmodifiableList(errors);
	}

	public T getResult() {
		return result;
	}

	public List<ErrorCode> getErrors() {
		return errors;
	}

	public boolean isError(){
		return errors != null;
	}
	
}
