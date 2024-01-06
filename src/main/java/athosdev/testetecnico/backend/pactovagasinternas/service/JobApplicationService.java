package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.JobApplication;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public Optional<JobApplication> getJobApplicationById(Integer jobApplicationId) {

        if (jobApplicationRepository.findById(jobApplicationId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada");
        }


        return jobApplicationRepository.findById(jobApplicationId);
    }

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public void deleteJobApplication(Integer applicationId) {
        jobApplicationRepository.deleteById(applicationId);
    }

    public JobApplication applyToJob(User applicant, Job appliedJob) {
        JobApplication jobApplication = new JobApplication(applicant, appliedJob);
        return (JobApplication) jobApplicationRepository.save(jobApplication);
    }



}
