/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.Objects;

/**
 *
 * @author Petro Prydatkevych
 */
public class User {
    private int id;
    private String username;
    private String passwordHash;
    private Role role;
 
    public User() {
    }
 
    public User (String username, String passwordHash, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }
 
    public User (int id, String username, String passwordHash, Role role) {
        this(username, passwordHash, role);
        this.id = id;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPasswordHash() {
        return passwordHash;
    }
 
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
 
    public Role getRole() {
        return role;
    }
 
    public void setRole(Role role) {
        this.role = role;
    }
 
    public boolean isAdministrator() {
        return role != null && Role.ADMINISTRATOR.equalsIgnoreCase(role.getName());
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User other)) {
            return false;
        }
        return Objects.equals(username, other.username);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
 
    @Override
    public String toString() {
        return username;
    }
}
