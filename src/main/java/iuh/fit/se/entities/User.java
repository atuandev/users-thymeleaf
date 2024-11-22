package iuh.fit.se.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "First Name is not empty")
    private String firstName;

    @Column(nullable = false)
    @NotEmpty(message = "Last Name is not empty")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email is not empty")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Password is not empty")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Gender is not empty")
    private String gender;

    @Column(nullable = false)
    @NotEmpty(message = "Favorite Language is not empty")
    private String favoriteLanguage;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    private boolean married;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String gender, String favoriteLanguage, String avatar, boolean married, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.favoriteLanguage = favoriteLanguage;
        this.avatar = avatar;
        this.married = married;
        this.roles = roles;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", favoriteLanguage='" + favoriteLanguage + '\'' +
                ", avatar='" + avatar + '\'' +
                ", married=" + married +
                ", roles=" + roles +
                '}';
    }

    public String getRolesAsStrings() {
        return String.join(", ", roles.stream().map(Role::getName).toArray(String[]::new));
    }
}