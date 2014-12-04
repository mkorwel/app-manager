package pl.mkorwel.app.manager.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.ApplicationHistory;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;
import pl.mkorwel.app.manager.application.repository.ApplicationHistoryRepository;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.application.usecase.model.request.RejectApplicationModel;
import pl.mkorwel.app.manager.base.usecase.BaseUserCase;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

@Component
public class RejectApplication implements
		BaseUserCase<Result<Void>, RejectApplicationModel> {

	private Logger logger = LoggerFactory.getLogger(RejectApplication.class);
	
	private ApplicationRepository repository;

	private ApplicationHistoryRepository historyRepository;

	@Autowired
	public RejectApplication(ApplicationRepository repository,
			ApplicationHistoryRepository historyRepository) {
		super();
		this.repository = repository;
		this.historyRepository = historyRepository;
	}

	@Override
	public Result<Void> execute(RejectApplicationModel params) {
		Application application;
		try {
			application = repository.findOne(params.getId());
		} catch (NullPointerException e){
			return new Result<Void>(ApplicationErrorCode.APPLICATION_NOT_FOUND);
		}
		ApplicationHistory applicationHistory = new ApplicationHistory(application);
		try {
			application.reject(params.getReason());
		} catch (WrongStateApplicationException e) {
			logger.error(e.getMessage(), e);
			return new Result<Void>(ApplicationErrorCode.WRONG_STATE);
		} catch (NullPointerException e) {
			logger.error(e.getMessage(), e);
			return new Result<Void>(ApplicationErrorCode.REASON_IS_REQUIRED);
		}
		repository.save(application);
		historyRepository.save(applicationHistory);
		return Result.EMPTY_RESULT;
	}

}
