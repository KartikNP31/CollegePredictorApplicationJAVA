public abstract class Registration {
    private String UserName;
    private String Password;
    private String email;

    public void SignUp(String userName, String password, String email) {
        setUserName(userName);
        setPassword(password);
        setEmail(email);
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}