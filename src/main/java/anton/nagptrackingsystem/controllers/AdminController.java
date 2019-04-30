package anton.nagptrackingsystem.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import anton.nagptrackingsystem.constant.Message;
import anton.nagptrackingsystem.dto.LoginDetail;
import anton.nagptrackingsystem.dto.Response;
import anton.nagptrackingsystem.entity.Activity;
import anton.nagptrackingsystem.entity.Applicant;
import anton.nagptrackingsystem.entity.ApplicantActivity;
import anton.nagptrackingsystem.entity.Batch;
import anton.nagptrackingsystem.entity.Comment;
import anton.nagptrackingsystem.entity.Level;
import anton.nagptrackingsystem.entity.User;
import anton.nagptrackingsystem.repositories.ApplicantActivityRepository;
import anton.nagptrackingsystem.repositories.CommentRepository;
import anton.nagptrackingsystem.services.ActivityService;
import anton.nagptrackingsystem.services.ApplicantService;
import anton.nagptrackingsystem.services.BatchService;
import anton.nagptrackingsystem.services.LevelService;
import anton.nagptrackingsystem.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService adminService;

	@Autowired
	BatchService batchService;

	@Autowired
	LevelService levelService;

	@Autowired
	ActivityService activityService;

	@Autowired
	ApplicantService applicantService;

	@Autowired
	ApplicantActivityRepository applicantActivityRepository;

	@Autowired
	CommentRepository commentRepository;

	// ADMIN
	@GetMapping
	public ResponseEntity<Response> getAdmins()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.findAdmins();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> findAdminbyId(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.adminById(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PostMapping
	public ResponseEntity<Response> addAdmin(@RequestBody User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.addAdmin(user);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Response> adminLogin(@RequestBody LoginDetail loginDetails)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.login(loginDetails.getEmail(), loginDetails.getPassword());
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.deleteUser(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> editAdmin(@PathVariable("id") int id, @RequestBody User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = adminService.updateAdmin(id, user.getPassword(), user.getName(), user.getContactNo(),
				user.getUserType());
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	// LEVELS
	@GetMapping("/level")
	public ResponseEntity<Response> getLevels()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = levelService.getLevels();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(400).body(response);
		}
	}

	@PostMapping("/level")
	public ResponseEntity<Response> addLevel(@RequestBody Level level)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = levelService.addLevel(level);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/level/{id}")
	public ResponseEntity<Response> getLevelById(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = levelService.getLevelById(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@DeleteMapping("/level/{id}")
	public ResponseEntity<Response> deleteLevel(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = levelService.deleteLevel(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PutMapping("/level/{id}")
	public ResponseEntity<Response> editLevel(@PathVariable("id") int id, @RequestBody Level level) {
		return null;
	}

	// BATCH
	@GetMapping("/batch")
	public ResponseEntity<Response> getBatches()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.getBatches();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(400).body(response);
		}
	}

	@PostMapping("/batch")
	public ResponseEntity<Response> addBatch(@RequestBody Batch batch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.addBatch(batch);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/batch/{id}")
	public ResponseEntity<Response> getBatchById(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.getBatchById(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@DeleteMapping("/batch/{id}")
	public ResponseEntity<Response> deleteBatch(@PathVariable("id") int id)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.deleteBatch(id);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PutMapping("/batch/{id}")
	public ResponseEntity<Response> editBatch(@PathVariable("id") int id, @RequestBody Batch level) {
		return null;
	}

	@GetMapping("/batch/technologies")
	public ResponseEntity<Response> getBatchTechnologies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = batchService.getBatchTechnologies();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}

	}

	// ACTIVITY
	@PostMapping("/activity")
	public ResponseEntity<Response> addActivity(@RequestBody Activity activity)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {
			Response response = activityService.addActivity(activity);
			if (response.status == "true") {
				return ResponseEntity.status(200).body(response);
			} else {
				return ResponseEntity.status(409).body(response);
			}
		} catch (Exception ex) {
			return ResponseEntity.status(409).body(new Response(Message.ERROR_EXCEPTION + ex.toString(), "exception"));
		}
	}

	@GetMapping("/activity")
	public ResponseEntity<Response> getActivities()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = activityService.getActivities();
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/activity/{id}")
	public ResponseEntity<Response> getActivityById(@PathVariable("id") int id) {
		return null;
	}

	@DeleteMapping("/activity/{id}")
	public ResponseEntity<Response> deleteActivity(@PathVariable("id") int id) {
		return null;
	}

	@PutMapping("/activity/{id}")
	public ResponseEntity<Response> editActivity(@PathVariable("id") int id, @RequestBody Activity activity) {
		return null;
	}

	// APPLICANT
	@PostMapping("/applicant")
	public ResponseEntity<Response> addApplicant(@RequestBody Applicant applicant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = applicantService.addApplicant(applicant);
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@GetMapping("/applicant")
	public ResponseEntity<Response> getAllApplicants(@RequestParam(required = false) Optional<Integer> pageNo,
			@RequestParam(required = false) Optional<Integer> pageSize)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response;
		if (pageNo.isPresent() && pageSize.isPresent()) {
			response = applicantService.getApplicantsPaginated(pageNo.get(), pageSize.get());
		} else {
			response = applicantService.getAllApplicants();
		}
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@PutMapping("/applicant/{id}")
	public ResponseEntity<Response> editApplicantByAdmin(@PathVariable("id") int id, @RequestParam Applicant applicant)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Response response = applicantService.editApplicantByAdmin(id, applicant.getApplicant().getUserType(),
				applicant.getNagpStatus(), applicant.getLevel(), applicant.getBatch());
		if (response.status == "true") {
			return ResponseEntity.status(200).body(response);
		} else {
			return ResponseEntity.status(409).body(response);
		}
	}

	@DeleteMapping("/applicant/{id}")
	public ResponseEntity<Response> deleteApplicant(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/applicant/{id}/applicant_activity")
	public ResponseEntity<Response> getApplicantActivitiesByApplicantId(@PathVariable("id") int id) {
		return null;
	}

	// APPLICANT ACTIVITY
	@PostMapping("/applicant_activity")
	public ResponseEntity<Response> addApplicantActivity(@RequestBody ApplicantActivity applicantActivity) {
		return null;
	}

	@PutMapping("/applicant_activity/{id}")
	public ResponseEntity<Response> editApplicantActivity(@PathVariable("id") int id,
			@RequestBody ApplicantActivity applicantActivity) {
		return null;
	}

	@DeleteMapping("/applicant_activity/{id}")
	public ResponseEntity<Response> deleteApplicantActivity(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/applicant_activity/{id}")
	public ResponseEntity<Response> getApplicantActivityByApplicantActivityId(@PathVariable("id") int id) {
		return null;
	}

	@GetMapping("/applicant_activity/{id}/comments")
	public ResponseEntity<Response> getApplicantActivityComments(@PathVariable("id") int id) {
		return null;
	}

	// COMMENTS
	@GetMapping("/comments/{id}")
	public ResponseEntity<Response> getCommentByCommentId(@PathVariable("id") int id) {
		return null;
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Response> deleteComment(@PathVariable("id") int id) {
		return null;
	}

	@PutMapping("/comments/{id}")
	public Response editComment(@PathVariable("id") int id, @RequestBody Comment comment) {
		return null;
	}

	@PostMapping("/comments")
	public Response addComment(@RequestBody Comment comment) {
		return null;
	}

}
