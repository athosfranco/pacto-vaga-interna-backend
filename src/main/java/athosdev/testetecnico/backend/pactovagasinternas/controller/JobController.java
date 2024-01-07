package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.dto.JobResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.exception.UserNotFoundException;
import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import athosdev.testetecnico.backend.pactovagasinternas.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    private JobResponseDTO convertToJobResponseDTO(Job job, User publishedBy) {
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setJobId(job.getJobId());
        jobResponseDTO.setTitle(job.getTitle());
        jobResponseDTO.setDescription(job.getDescription());
        jobResponseDTO.setPublishedBy(publishedBy);
        return jobResponseDTO;
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Integer jobId) {
        Optional<Job> job = jobService.getJobById(jobId);

        return job.map(value -> {
            Integer publishedByUserId = value.getPublishedByUserId();
            User publishedBy = userRepository.findById(publishedByUserId)
                    .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + publishedByUserId + " não encontrado"));

            JobResponseDTO jobResponseDTO = convertToJobResponseDTO(value, publishedBy);
            return new ResponseEntity<>(jobResponseDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job savedJob = jobService.saveJob(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer jobId, @RequestBody Job job) {
        if (!jobService.getJobById(jobId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        job.setJobId(jobId);
        Job updatedJob = jobService.saveJob(job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer jobId) {
        if (!jobService.getJobById(jobId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        jobService.deleteJob(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
