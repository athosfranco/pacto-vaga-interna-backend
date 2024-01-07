package athosdev.testetecnico.backend.pactovagasinternas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "skill_name", unique = true, nullable = false)
    private String skillName;

    @Column(name="experience_years")
    private Integer experienceYears;

    // CONSTRUCTOR

    public Skill() {
    }

    //GETTER SETTER


    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
