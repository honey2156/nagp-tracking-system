package anton.nagptrackingsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Applicant;
import anton.nagptrackingsystem.services.ApplicantService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	ApplicantService applicantService;

	@PutMapping("/{id}")
	public ResponseEntity<Response> editApplicantByApplicant(@PathVariable("id") int id,
			@RequestBody Applicant applicant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = applicantService.editApplicantByApplicant(id, applicant.getApplicant().getPassword(),
				applicant.getApplicant().getName(), applicant.getApplicant().getContactNo());
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getApplicantById(@PathVariable("id") int id) {
		return null;
	}
}
