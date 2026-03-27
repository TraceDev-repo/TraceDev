package com.example;

/**
 * Implementation of authentication service.
 * For simplicity, uses a thread-local user context.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private ThreadLocal<User> currentUser = new ThreadLocal<>();

    public void login(User user) {
        currentUser.set(user);
    }

    public void logout() {
        currentUser.remove();
    }

    @Override
    public boolean isUserLoggedIn() {
        return currentUser.get() != null;
    }

    @Override
    public User getCurrentUser() {
        User user = currentUser.get();
        if (user == null) {
            throw new SecurityException("No user logged in.");
        }
        return user;
    }
}