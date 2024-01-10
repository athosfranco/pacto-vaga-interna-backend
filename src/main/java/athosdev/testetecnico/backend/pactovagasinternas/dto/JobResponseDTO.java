package athosdev.testetecnico.backend.pactovagasinternas.dto;

import athosdev.testetecnico.backend.pactovagasinternas.model.Skill;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public class JobResponseDTO {

    private Integer jobId;

    private String title;

    private String description;

    private User publishedBy;

    private LocalDateTime createdAt;

    private Set<Skill> requiredSkills;

    public JobResponseDTO() {
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

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }


}
