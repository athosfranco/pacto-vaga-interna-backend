package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
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
    private JobService jobService;

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }

    @GetMapping("/")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer jobId) {
        Optional<Job> job = jobService.getJobById(jobId);
        return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
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
