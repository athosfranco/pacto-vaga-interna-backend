package athosdev.testetecnico.backend.pactovagasinternas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    private String username;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<UserRole> authorities;

    @JsonBackReference
    @OneToMany(mappedBy = "publishedBy", cascade = CascadeType.ALL)
    private Set<Job> publishedJobs;

    @JsonBackReference
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private Set<JobApplication> jobApplications = new HashSet<>();

    ///////////// DATE TIME

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    ///////////// NO ARGS CONSTRUCTOR

    public User() {
        super();
        this.authorities = new HashSet<UserRole>();
        this.publishedJobs = new HashSet<>();
        this.jobApplications = new HashSet<>();
    }

    //////////////////////////////////


    ///////////// ALL ARGS CONSTRUCTOR
    public User(Integer userId, String username, String password, Set<UserRole> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.publishedJobs = new HashSet<>();
        this.jobApplications = new HashSet<>();
    }

    //////////// GETTER SETTER
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(Set<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Job> getPublishedJobs() {
        return publishedJobs;
    }

    public void setPublishedJobs(Set<Job> publishedJobs) {
        this.publishedJobs = publishedJobs;
    }


}
