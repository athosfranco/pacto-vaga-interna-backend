package athosdev.testetecnico.backend.pactovagasinternas.dto;

import athosdev.testetecnico.backend.pactovagasinternas.model.User;

import java.util.Optional;

public class LoginResponseDTO {
    private Optional<User> user;
    private String jwt;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(Optional<User> user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public Optional<User> getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = Optional.ofNullable(user);
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}