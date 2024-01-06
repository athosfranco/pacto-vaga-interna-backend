package athosdev.testetecnico.backend.pactovagasinternas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Integer jobId;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "published_by_user_id")
    @JsonManagedReference
    private User publishedBy;

    @Column(name = "published_by_user_id", insertable = false, updatable = false)
    private Integer publishedByUserId;

    ///////////// DATE TIME

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    //// NO ARGS CONSTRUCTOR
    public Job() {
    }

    /// ALL ARGS CONSTRUCTOR

    public Job(Integer jobId, String title, String description, Integer publishedByUserId) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.publishedBy = null;
        this.publishedByUserId = publishedByUserId;
    }


    //// GETTERS SETTERS


    public Integer getPublishedByUserId() {
        return publishedByUserId;
    }

    public void setPublishedByUserId(Integer publishedByUserId) {
        this.publishedByUserId = publishedByUserId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedBy=" + publishedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
