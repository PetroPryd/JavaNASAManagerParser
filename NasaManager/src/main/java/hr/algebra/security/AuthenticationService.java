/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.security;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Role;
import hr.algebra.model.User;
import hr.algebra.utilities.PasswordUtils;
import java.util.Optional;

/**
 *
 * @author Petro Prydatkevych
 */
public class AuthenticationService {
    private final Repository repository = RepositoryFactory.getRepository();
 
    public Optional<User> authenticate(String username, String password) throws Exception {
        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            return Optional.empty();
        }
        Optional<User> match = repository.selectUserByUsername(username.trim());
        if (match.isPresent() && PasswordUtils.matches(password, match.get().getPasswordHash())) {
            return match;
        }
        return Optional.empty();
    }
 
    /**
     * Registers a new account with the USER role. Fails if the username is taken
     * or the inputs are blank.
     */
    public User register(String username, String password) throws Exception {
        String trimmed = (username == null) ? "" : username.trim();
        if (trimmed.isBlank() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password are required.");
        }
        if (repository.selectUserByUsername(trimmed).isPresent()) {
            throw new IllegalArgumentException("Username '" + trimmed + "' is already taken.");
        }
        Role userRole = repository.selectRoleByName(Role.USER)
                .orElseThrow(() -> new IllegalStateException(
                        "USER role not found - did you run the database init script?"));
 
        User user = new User(trimmed, PasswordUtils.hash(password), userRole);
        user.setId(repository.createUser(user));
        return user;
    }
}
