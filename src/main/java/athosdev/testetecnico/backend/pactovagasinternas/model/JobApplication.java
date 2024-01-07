package athosdev.testetecnico.backend.pactovagasinternas.model;

import athosdev.testetecnico.backend.pactovagasinternas.enums.JobApplicationStage;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "stage", nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private JobApplicationStage applicationStage;

    @Column(name = "application_date", nullable = false, updatable = false)
    private LocalDateTime applicationDate;

    // CONSTRUCTORS

    public JobApplication() {
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

    public JobApplicationStage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationStage(JobApplicationStage applicationStage) {
        this.applicationStage = applicationStage;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
