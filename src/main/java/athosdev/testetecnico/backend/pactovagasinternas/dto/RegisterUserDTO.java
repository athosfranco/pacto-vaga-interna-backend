package athosdev.testetecnico.backend.pactovagasinternas.dto;

import athosdev.testetecnico.backend.pactovagasinternas.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class RegisterUserDTO {
    private String username;
    private String password;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String currentJob;

    private LocalDateTime hireDate;

    public RegisterUserDTO() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentRole) {
        this.currentJob = currentRole;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "RegisterUserDTO{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
