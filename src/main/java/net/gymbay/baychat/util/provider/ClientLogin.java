package net.gymbay.baychat.util.provider;

public record ClientLogin(String email, String password) {

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
