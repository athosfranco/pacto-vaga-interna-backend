package athosdev.testetecnico.backend.pactovagasinternas.repository;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByReadIsFalseAndDestinationUserId(Integer userId);
    List<Notification> findAllByDestinationUserId(Integer userId);
}
