package athosdev.testetecnico.backend.pactovagasinternas.repository;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);
}
