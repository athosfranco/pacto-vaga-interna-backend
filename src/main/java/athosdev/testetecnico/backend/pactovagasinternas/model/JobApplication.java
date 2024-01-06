package athosdev.testetecnico.backend.pactovagasinternas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Integer applicationId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User applicant;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job appliedJob;

    @Column(name = "application_date", nullable = false, updatable = false)
    private LocalDateTime applicationDate;

    // CONSTRUCTORS

    public JobApplication() {
        this.applicationDate = LocalDateTime.now();
    }

    public JobApplication(User applicant, Job appliedJob) {
        this.applicant = applicant;
        this.appliedJob = appliedJob;
        this.applicationDate = LocalDateTime.now();
    }

    // GETTER SETTER

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Job getAppliedJob() {
        return appliedJob;
    }

    public void setAppliedJob(Job appliedJob) {
        this.appliedJob = appliedJob;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
