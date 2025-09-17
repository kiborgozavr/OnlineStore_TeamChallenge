package io.teamchallenge.dto.user;

public class UserProfilePOST extends UserProfile {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
