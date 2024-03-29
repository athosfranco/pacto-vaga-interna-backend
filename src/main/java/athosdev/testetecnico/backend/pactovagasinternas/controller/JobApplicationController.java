package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.dto.ErrorResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.JobApplicationRequestDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.JobApplicationUpdateDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.JobApplication;
import athosdev.testetecnico.backend.pactovagasinternas.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-applications")
@CrossOrigin("*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplication> createNewJobApplication(@RequestParam Integer userId, @RequestParam Integer jobId, @RequestBody JobApplicationRequestDTO body) {
        JobApplication jobApplication = jobApplicationService.applyToJob(userId, jobId, body);
        return new ResponseEntity<>(jobApplication, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllJobApplications() {
        List<JobApplication> jobApplications = jobApplicationService.getAllJobApplications();
        return new ResponseEntity<>(jobApplications, HttpStatus.OK);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<JobApplication>> getJobApplicationsByUserId(@PathVariable Integer userId) {
        List<JobApplication> jobApplications = jobApplicationService.getJobApplicationsByUserId(userId);
        return new ResponseEntity<>(jobApplications, HttpStatus.OK);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getJobApplicationById(@PathVariable Integer applicationId) {
        try {
            Optional<JobApplication> user = jobApplicationService.getJobApplicationById(applicationId);
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getReason(), (HttpStatus) e.getStatusCode());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/update-stage/{applicationId}")
    public ResponseEntity<JobApplication> updateJobApplicationStage(
            @PathVariable Integer applicationId,
            @RequestBody JobApplicationUpdateDTO requestDTO
    ) {
        try {
            JobApplication updatedApplication = jobApplicationService.updateJobApplicationStage(applicationId, requestDTO);
            return ResponseEntity.ok(updatedApplication);
        } catch (ResponseStatusException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getReason(), (HttpStatus) e.getStatusCode());
            return (ResponseEntity<JobApplication>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Integer applicationId) {
        jobApplicationService.deleteJobApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
