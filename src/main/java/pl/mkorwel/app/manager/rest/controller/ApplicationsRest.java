package pl.mkorwel.app.manager.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import pl.mkorwel.app.manager.application.usecase.CRUDApplication;
import pl.mkorwel.app.manager.application.usecase.FindAllApplication;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationFindRequest;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationUpdateModel;
import pl.mkorwel.app.manager.application.usecase.model.request.ApplicationReadModel;
import pl.mkorwel.app.manager.application.usecase.model.request.DeleteApplicationModel;
import pl.mkorwel.app.manager.application.usecase.model.response.ApplicationHeaderModel;
import pl.mkorwel.app.manager.base.exception.ApplicationException;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;
import pl.mkorwel.app.manager.rest.model.ReasonModelRequest;

@Controller
@RequestMapping(value = "/applications")
public class ApplicationsRest {

	private FindAllApplication findAllApplication;

	private CRUDApplication createApplication;

	@Autowired
	public ApplicationsRest(FindAllApplication findAllApplication,
			CRUDApplication createApplication) {
		super();
		this.findAllApplication = findAllApplication;
		this.createApplication = createApplication;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ApplicationHeaderModel> getAll(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) Direction sortDirection) {

		ApplicationFindRequest request = new ApplicationFindRequest(page, size,
				sort, sortDirection);
		return findAllApplication.execute(request);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> create(UriComponentsBuilder uriBuilder,
			@RequestBody ApplicationUpdateModel request) {

		Result<Long> applicationId = createApplication.create(request);
		if (applicationId.isError()) {
			throw new ApplicationException(applicationId.getErrors());
		}

		UriComponents uriComponents = uriBuilder.path("/applications/{id}")
				.buildAndExpand(applicationId.getResult());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponents.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseBody
	public ResponseEntity<Void> update(@PathVariable Long id,
			@RequestBody ApplicationUpdateModel request) {

		request.setId(id);
		Result<Void> result = createApplication.update(request);
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public ApplicationReadModel get(@PathVariable Long id) {

		return createApplication.read(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public ResponseEntity<Void> delete(@PathVariable Long id,
			@RequestBody ReasonModelRequest reason) {

		Result<Void> result = createApplication
				.delete(new DeleteApplicationModel(id, reason.getReason()));
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
