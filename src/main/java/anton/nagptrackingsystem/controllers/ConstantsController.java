package anton.nagptrackingsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.services.ActivityService;
import anton.nagptrackingsystem.services.ApplicantService;
import anton.nagptrackingsystem.services.BatchService;
import anton.nagptrackingsystem.services.UserService;

@RestController
@RequestMapping("/constants")
public class ConstantsController {

	@Autowired
	BatchService batchService;

	@Autowired
	ActivityService activityService;

	@Autowired
	ApplicantService applicantService;

	@Autowired
	UserService userService;

	@GetMapping("/batch_technologies")
	public ResponseEntity<Response> getTechnologies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.getBatchTechnologies();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}

	}

	@GetMapping("/user_types")
	public ResponseEntity<Response> getUserTypes()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = userService.getUserTypes();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/activity_status")
	public ResponseEntity<Response> getActivityStatuses()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = activityService.getActivityStatus();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/nagp_status")
	public ResponseEntity<Response> getNAGPStatus()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = applicantService.getNagpStatus();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

}