package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.dto.JobApplicationRequestDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.JobApplicationUpdateDTO;
import athosdev.testetecnico.backend.pactovagasinternas.enums.JobApplicationStage;
import athosdev.testetecnico.backend.pactovagasinternas.exception.JobNotFoundException;
import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.JobApplication;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobApplicationRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;


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

    public JobApplication applyToJob(Integer userId, Integer jobId, JobApplicationRequestDTO body) {
        var userFound = userRepository.findById(userId).orElseThrow(() -> new JobNotFoundException("Usuário de ID " + userId  + " não encontrado"));
        var jobFound = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Vaga de ID " + jobId + " não encontrada"));

        if (jobApplicationRepository.existsByApplicantUserIdAndAppliedJob_JobId(userId, jobId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já se candidatou a esta vaga. Acompanhe seu processo seletivo no Painel do Candidato.");
        }

        JobApplication jobApplication = new JobApplication();
        jobApplication.setApplicant(userFound);
        jobApplication.setAppliedJob(jobFound);
        jobApplication.setUserMessage(body.getUserMessage());
        jobApplication.setApplicationDate(LocalDateTime.now());
        jobApplication.setApplicationStage(JobApplicationStage.UNDER_REVIEW);

        return jobApplicationRepository.save(jobApplication);
    }

    public JobApplication updateJobApplicationStage(Integer applicationId, JobApplicationUpdateDTO requestDTO) {
        JobApplication jobApplication = getJobApplicationById(applicationId)
                .orElseThrow(() -> new JobNotFoundException("Job Application with ID " + applicationId + " not found"));

        jobApplication.setApplicationStage(requestDTO.getNewStage());
        jobApplication.setFeedback(requestDTO.getFeedback());

        return jobApplicationRepository.save(jobApplication);
    }

}
