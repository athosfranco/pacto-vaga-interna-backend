package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.model.Notification;
import athosdev.testetecnico.backend.pactovagasinternas.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byDestinationUserId/{userId}")
    public ResponseEntity<List<Notification>> getNotificationByDestinationUserId(@PathVariable Integer userId) {
        List<Notification> notifications = notificationService.getNotificationDestinationUserById(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/unreadNotificationsByDestinationUserId/{userId}")
    public ResponseEntity<List<Notification>> getUnreadNotificationsByDestinationUserId(@PathVariable Integer userId) {
        List<Notification> notifications = notificationService.getUnreadNotificationDestinationUserById(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PostMapping("/sendToAdmins")
    public ResponseEntity<Notification> sendNotificationToAdmins(@RequestBody Notification notification) {
    Notification createdNotification = notificationService.sendNotificationToAdmins(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/markAsRead/{id}")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Integer id) {
        Notification updatedNotification = notificationService.markNotificationAsRead(id);
        if (updatedNotification != null) {
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Integer id,
                                                           @RequestBody Notification updatedNotification) {
        Notification notification = notificationService.updateNotification(id, updatedNotification);

        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
