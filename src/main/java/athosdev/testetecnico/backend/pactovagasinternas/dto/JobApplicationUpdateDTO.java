package athosdev.testetecnico.backend.pactovagasinternas.dto;

import athosdev.testetecnico.backend.pactovagasinternas.enums.JobApplicationStage;

public class JobApplicationUpdateDTO {
    private JobApplicationStage newStage;
    private String feedback;

    public JobApplicationStage getNewStage() {
        return newStage;
    }

    public void setNewStage(JobApplicationStage newStage) {
        this.newStage = newStage;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
