package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.enums.JobApplicationStage;
import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.JobApplication;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobApplicationRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public Optional<JobApplication> getJobApplicationById(Integer jobApplicationId) {

        if (jobApplicationRepository.findById(jobApplicationId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada");
        }

        return jobApplicationRepository.findById(jobApplicationId);
    }

    public List<JobApplication> getJobApplicationsByUserId(Integer userId) {
        return jobApplicationRepository.findByApplicantUserId(userId);
    }

    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    public void deleteJobApplication(Integer applicationId) {
        jobApplicationRepository.deleteById(applicationId);
    }

    public JobApplication applyToJob(User applicant, Job appliedJob) {

        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(applicant);
        jobApplication.setAppliedJob(appliedJob);
        jobApplication.setApplicationDate(LocalDateTime.now());
        jobApplication.setApplicationStage(JobApplicationStage.UNDER_REVIEW);

        return jobApplicationRepository.save(jobApplication);
    }

}
