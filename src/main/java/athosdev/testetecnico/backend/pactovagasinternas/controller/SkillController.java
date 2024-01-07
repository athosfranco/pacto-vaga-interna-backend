package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.model.Skill;
import athosdev.testetecnico.backend.pactovagasinternas.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin("*")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> allSkills = skillService.getAllSkills();
        return ResponseEntity.ok(allSkills);
    }

    @PostMapping
    public ResponseEntity<Void> createSkill(@RequestBody Skill skill) {
        skillService.createSkill(skill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add-skill-to-user")
    public ResponseEntity<Void> addSkillToUser(@RequestParam Integer userId, @RequestParam Integer skillId, @RequestParam Integer expYears) {
        skillService.addSkillToUser(userId, skillId, expYears);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-skill-by-user-id/{userId}")
    public ResponseEntity<Set<Skill>> getUserSkills(@PathVariable Integer userId) {
        Set<Skill> userSkills = skillService.getUserSkills(userId);
        return ResponseEntity.ok(userSkills);
    }

    @PostMapping("/add-skill-to-job")
    public ResponseEntity<Void> addSkillToJob(@RequestParam Integer jobId, @RequestParam Integer skillId, @RequestParam Integer expYears) {
        skillService.addSkillToJob(jobId, skillId, expYears);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-skill-by-job-id/{jobId}")
    public ResponseEntity<Set<Skill>> getJobSkills(@PathVariable Integer jobId) {
        Set<Skill> jobSkills = skillService.getJobSkills(jobId);
        return ResponseEntity.ok(jobSkills);
    }

}
