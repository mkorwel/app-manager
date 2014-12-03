package pl.mkorwel.app.manager.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mkorwel.app.manager.application.usecase.AcceptApplication;
import pl.mkorwel.app.manager.application.usecase.PublishApplication;
import pl.mkorwel.app.manager.application.usecase.RejectApplication;
import pl.mkorwel.app.manager.application.usecase.VerifyApplication;
import pl.mkorwel.app.manager.application.usecase.model.request.RejectApplicationModel;
import pl.mkorwel.app.manager.base.exception.ApplicationException;
import pl.mkorwel.app.manager.base.usecase.model.response.Result;
import pl.mkorwel.app.manager.rest.model.ReasonModelRequest;

@Controller
@RequestMapping(value = "/applications/{id}")
public class ApplicationOperations {

	private VerifyApplication verifyApplication;

	private AcceptApplication acceptApplication;

	private PublishApplication publishApplication;

	private RejectApplication rejectApplication;

	@Autowired
	public ApplicationOperations(VerifyApplication verifyApplication,
			AcceptApplication acceptApplication,
			PublishApplication publishApplication,
			RejectApplication rejectApplication) {
		super();
		this.verifyApplication = verifyApplication;
		this.acceptApplication = acceptApplication;
		this.publishApplication = publishApplication;
		this.rejectApplication = rejectApplication;
	}

	@RequestMapping(value = "/verify", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Void> verify(@PathVariable Long id) {
		Result<Void> result = verifyApplication.execute(id);
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/accept", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Void> accept(@PathVariable Long id) {
		Result<Void> result = acceptApplication.execute(id);
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/publish", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Void> publish(@PathVariable Long id) {
		Result<Void> result = publishApplication.execute(id);
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/reject", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<Void> reject(@PathVariable Long id,
			@RequestBody ReasonModelRequest reason) {
		Result<Void> result = rejectApplication
				.execute(new RejectApplicationModel(id, reason.getReason()));
		if (result.isError()) {
			throw new ApplicationException(result.getErrors());
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
