package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.model.Job;
import athosdev.testetecnico.backend.pactovagasinternas.model.Skill;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.JobRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.SkillRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class SkillService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();

    }

    public void createSkill(Skill skill) {
        Skill skillFound = skillRepository.findSkillBySkillName(skill.getSkillName());

        if (skillFound != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma habilidade com esse nome.");
        }
        skillRepository.save(skill);
    }

    public void addSkillToUser(Integer userId, Integer skillId, Integer expYears) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habilidade não encontrada"));


        if (user.getSkills().contains(skill)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já cadastrou essa habilidade.");
        }

        skill.setExperienceYears(expYears);
        user.getSkills().add(skill);
        userRepository.save(user);
    }

    public Set<Skill> getUserSkills(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return user.getSkills();
    }

    public void addSkillToJob(Integer jobId, Integer skillId, Integer expYears) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habilidade não encontrada"));

        skill.setExperienceYears(expYears);
        job.getRequiredSkills().add(skill);
        jobRepository.save(job);
    }

    public Skill getOrCreateSkill(Skill skill) {
        Skill existingSkill = skillRepository.findSkillBySkillName(skill.getSkillName());

        if (existingSkill == null) {
            ////// Se a Skill não existir, cria a skill //////
            existingSkill = new Skill();
            existingSkill.setSkillName(skill.getSkillName());
            existingSkill.setExperienceYears(skill.getExperienceYears());
            skillRepository.save(existingSkill);
        }

        return existingSkill;
    }


    public Set<Skill> getJobSkills(Integer jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada"));

        return job.getRequiredSkills();
    }

    public void deleteSkill(Integer skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habilidade não encontrada"));


        List<User> usersWithSkill = userRepository.findBySkillsContaining(skill);
        for (User user : usersWithSkill) {
            user.getSkills().remove(skill);
            userRepository.save(user);
        }


        List<Job> jobsWithSkill = jobRepository.findByRequiredSkillsContaining(skill);
        for (Job job : jobsWithSkill) {
            job.getRequiredSkills().remove(skill);
            jobRepository.save(job);
        }

        // Delete the skill
        skillRepository.delete(skill);
    }
}