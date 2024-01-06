package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserService userService;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Integer jobId) {
        return jobRepository.findById(jobId);
    }

    public Job saveJob(Job job) {

        if (job.getPublishedByUserId() != null) {

            User publishedByUser = userService.getUserById(job.getPublishedByUserId())
                    .orElseThrow(() -> new RuntimeException("O usuário informado não foi encontrado"));
            job.setPublishedBy(publishedByUser);
        }

        return jobRepository.save(job);
    }

    public void deleteJob(Integer jobId) {
        jobRepository.deleteById(jobId);
    }
}
