package athosdev.testetecnico.backend.pactovagasinternas.repository;

import athosdev.testetecnico.backend.pactovagasinternas.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    List<JobApplication> findByApplicantUserId(Integer userId);


    boolean existsByApplicantUserIdAndAppliedJob_JobId(Integer userId, Integer jobId);
}
