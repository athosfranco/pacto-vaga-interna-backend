package athosdev.testetecnico.backend.pactovagasinternas.enums;

public enum Gender {

    M("Masculino"),
    F("Feminino"),
    O("Outro"),
    NI("Prefere não informar");

    private String gender;


    Gender(String s) {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
