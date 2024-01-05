package athosdev.testetecnico.backend.pactovagasinternas.dto;

public class RegisterUserDTO {
    private String username;
    private String password;

    public RegisterUserDTO() {
    }

    public RegisterUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterUserDTO{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
