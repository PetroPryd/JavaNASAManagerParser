/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.security;

import hr.algebra.model.User;

/**
 *
 * @author Petro Prydatkevych
 */
public final class Session {
    private static final Session INSTANCE = new Session();
 
    private User currentUser;
 
    private Session() {
    }
 
    public static Session getInstance() {
        return INSTANCE;
    }
 
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
 
    public User getCurrentUser() {
        return currentUser;
    }
 
    public boolean isAuthenticated() {
        return currentUser != null;
    }
 
    public boolean isAdministrator() {
        return currentUser != null && currentUser.isAdministrator();
    }
 
    public void logout() {
        this.currentUser = null;
    }
}
