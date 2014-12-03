package pl.mkorwel.app.manager.application.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.domain.error.ApplicationErrorCode;
import pl.mkorwel.app.manager.application.exception.WrongStateApplicationException;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.base.usecase.BaseUserCase;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;

@Component
public class PublishApplication implements BaseUserCase<Result<Void>, Long> {

	private Logger logger = LoggerFactory.getLogger(PublishApplication.class);

	private ApplicationRepository applicationRepository;

	@Autowired
	public PublishApplication(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public Result<Void> execute(Long id) {
		Application application = applicationRepository.findOne(id);
		try {
			application.publish();
		} catch (WrongStateApplicationException e) {
			logger.error(e.getMessage(), e);
			return new Result<Void>(ApplicationErrorCode.WRONG_STATE);
		}
		applicationRepository.save(application);
		return Result.EMPTY_RESULT;
	}

}