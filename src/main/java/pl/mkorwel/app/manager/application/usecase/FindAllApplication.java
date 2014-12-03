package pl.mkorwel.app.manager.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import pl.mkorwel.app.manager.application.domain.Application;
import pl.mkorwel.app.manager.application.repository.ApplicationRepository;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationFindRequest;
import pl.mkorwel.app.manager.application.usecase.model.response.ApplicationHeaderModel;
import pl.mkorwel.app.manager.base.usecase.BaseUserCase;

@Component
public class FindAllApplication implements
		BaseUserCase<List<ApplicationHeaderModel>, ApplicationFindRequest> {

	private ApplicationRepository applicationRepository;

	@Autowired
	public FindAllApplication(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	public List<ApplicationHeaderModel> execute(ApplicationFindRequest request) {

		Page<Application> all = applicationRepository.findAll(new PageRequest(
				request.getPage(), request.getSize(), request.getSort()));

		List<ApplicationHeaderModel> resultList = all.getContent().stream()
				.map(application -> new ApplicationHeaderModel(application))
				.collect(Collectors.toList());

		return resultList;
	}

}
