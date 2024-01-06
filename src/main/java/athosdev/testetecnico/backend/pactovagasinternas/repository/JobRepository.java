package athosdev.testetecnico.backend.pactovagasinternas.repository;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
