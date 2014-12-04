package pl.mkorwel.app.manager.application.usecase;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationHistory;
import pl.mkorwel.app.manager.application.domain.ApplicationState;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;
import pl.mkorwel.app.manager.application.repository.ApplicationHistoryRepository;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationUpdateModel;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationReadModel;
import pl.mkorwel.app.manager.application.usecase.model.request.DeleteApplicationModel;
import pl.mkorwel.app.manager.base.usecase.model.response.ErrorCode;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

@Component
public class CRUDApplication {

	private Logger logger = LoggerFactory.getLogger(CRUDApplication.class);

	private ApplicationRepository repository;

	private ApplicationHistoryRepository historyRepository;

	@Autowired
	public CRUDApplication(ApplicationRepository repository,
			ApplicationHistoryRepository historyRepository) {
		super();
		this.repository = repository;
		this.historyRepository = historyRepository;
	}

	public Result<Long> create(ApplicationUpdateModel param) {

		List<ErrorCode> errors = validateBeforeSave(param);
		if (!errors.isEmpty()) {
			return new Result<Long>(errors);
		}

		Application application = repository.save(new Application(
				param.getName(), param.getContent()));

		return new Result<Long>(application.getId());
	}

	public Result<Void> update(ApplicationUpdateModel param) {

		Application application;
		try {
			application = repository.findOne(param.getId());
		} catch (NullPointerException e){
			return new Result<Void>(ApplicationErrorCode.APPLICATION_NOT_FOUND);
		}
		List<ErrorCode> errors = validateBeforeUpdate(param, application);
		if (!errors.isEmpty()) {
			return new Result<Void>(errors);
		}
		
		ApplicationHistory history = new ApplicationHistory(application);
		application.changeName(param.getName());
		application.changeContent(param.getContent());
		application = repository.save(application);
		historyRepository.save(history);
		
		return Result.EMPTY_RESULT;
	}

	private List<ErrorCode> validateBeforeUpdate(ApplicationUpdateModel param,
			Application application) {
		List<ErrorCode> errors = validateBeforeSave(param);
		if (!(application.getState() == ApplicationState.CREATED || application
				.getState() == ApplicationState.VERIFIED)) {
			errors.add(ApplicationErrorCode.WRONG_STATE);
		}
		return errors;
	}

	private List<ErrorCode> validateBeforeSave(ApplicationUpdateModel param) {
		List<ErrorCode> errors = new LinkedList<>();
		if (StringUtils.isEmpty(param.getName())) {
			errors.add(ApplicationErrorCode.NAME_IS_REQUIRED);
		}
		if (StringUtils.isEmpty(param.getContent())) {
			errors.add(ApplicationErrorCode.CONTENT_IS_REQUIRED);
		}

		return errors;
	}

	public Result<ApplicationReadModel> read(Long id) {
		try {
			return new Result<ApplicationReadModel>(new ApplicationReadModel(repository.findOne(id)));
		} catch (NullPointerException e){
			return new Result<ApplicationReadModel>(ApplicationErrorCode.APPLICATION_NOT_FOUND);
		}
	}

	public Result<Void> delete(DeleteApplicationModel params) {

		Application application;
		try {
			application = repository.findOne(params.getId());
		} catch (NullPointerException e){
			return new Result<Void>(ApplicationErrorCode.APPLICATION_NOT_FOUND);
		}
		ApplicationHistory history = new ApplicationHistory(application);
		try {
			application.delete(params.getReason());
		} catch (WrongStateApplicationException e) {
			logger.error(e.getMessage(), e);
			return new Result<Void>(ApplicationErrorCode.WRONG_STATE);
		} catch (NullPointerException e) {
			logger.error(e.getMessage(), e);
			return new Result<Void>(ApplicationErrorCode.REASON_IS_REQUIRED);
		}
		application = repository.save(application);
		historyRepository.save(history);

		return Result.EMPTY_RESULT;
	}

}
