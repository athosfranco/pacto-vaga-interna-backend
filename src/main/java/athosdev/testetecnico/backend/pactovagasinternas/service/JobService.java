package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private UserRepository userRepository;

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
       if(job.getPublishedByUserId() == null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário informar o ID do usuário responsável pela vaga");
       }

        return jobRepository.save(job);
    }

    public void deleteJob(Integer jobId) {
        jobRepository.deleteById(jobId);
    }

    public List<Job> searchJobs(String searchTerm) {
        return jobRepository.findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(searchTerm, searchTerm);
    }
}
