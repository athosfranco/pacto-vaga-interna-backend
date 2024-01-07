package athosdev.testetecnico.backend.pactovagasinternas.enums;

public enum JobApplicationStage {
    UNDER_REVIEW(1, "Em Análise"),
    USER_CLOSED(2, "Encerrada pelo Candidato"),
    CREATOR_CLOSED(3, "Encerrada pela Empresa"),
    REJECTED(4, "Reprovado"),
    APPROVED(5, "Aprovado");


    private final int stageId;
    private final String stageName;

    JobApplicationStage(int stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    public int getStageId() {
        return stageId;
    }

    public String getStageName() {
        return stageName;
    }
}
