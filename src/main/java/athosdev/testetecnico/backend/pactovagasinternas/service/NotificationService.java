package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.dto.ErrorResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.Notification;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.model.UserRole;
import athosdev.testetecnico.backend.pactovagasinternas.repository.NotificationRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.RoleRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> getNotificationDestinationUserById(Integer id) {
        return notificationRepository.findAllByDestinationUserId(id);
    }

    public List<Notification> getUnreadNotificationDestinationUserById(Integer id) {
        return notificationRepository.findAllByReadIsFalseAndDestinationUserId(id);
    }


    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification sendNotificationToAdmins(Notification notification) {

        Optional<UserRole> admin = roleRepository.findByAuthority("ADMIN");

        if (admin.isPresent()) {

            List<User> adminUsers = userRepository.findByAuthoritiesContaining(admin);

            for (User adminUser : adminUsers) {
                Notification adminNotification = new Notification();
                adminNotification.setTitle(notification.getTitle());
                adminNotification.setContent(notification.getContent());
                adminNotification.setDestinationUserId(adminUser.getUserId());
                createNotification(adminNotification);

                return notificationRepository.save(notification);
            }
        }

        return notification;
    }

    public Notification markNotificationAsRead(Integer id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);

        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        } else {
            return null;
        }
    }

    public Notification updateNotification(Integer id, Notification updatedNotification) {
        Optional<Notification> existingNotification = notificationRepository.findById(id);

        if (existingNotification.isPresent()) {
            Notification notification = existingNotification.get();
            notification.setTitle(updatedNotification.getTitle());
            notification.setContent(updatedNotification.getContent());
            notification.setRead(updatedNotification.isRead());

            return notificationRepository.save(notification);
        } else {
            return null;
        }
    }

    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }
}
