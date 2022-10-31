public abstract class Person{
    private String username;
    private String email;
    private String password;

    Person(){
        this(null,null,null);
    }
    Person(String username,String email,String password)
    {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public abstract boolean Login();
}