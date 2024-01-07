package athosdev.testetecnico.backend.pactovagasinternas.dto;

import athosdev.testetecnico.backend.pactovagasinternas.model.User;

public class JobResponseDTO {

    private Integer jobId;

    private String title;

    private String description;

    private User publishedBy;

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

    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }


}
