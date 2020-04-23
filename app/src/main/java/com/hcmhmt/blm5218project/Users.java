package com.hcmhmt.blm5218project;

public class Users {
    String username;
    String password;
    int image;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Users(String username, String password, int image) {
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public Users() {
    }
}
