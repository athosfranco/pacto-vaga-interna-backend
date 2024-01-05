package athosdev.testetecnico.backend.pactovagasinternas.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admins")
@CrossOrigin("*")
public class AdminController {

    @GetMapping("/")
    public String ping() {
        return "Ping";
    }

}
