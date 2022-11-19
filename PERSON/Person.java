package PERSON;

import INSTITUTE.Institute;

import java.sql.Connection;

public abstract class Person {
    private String username;
    private String email;
    private String password;
    
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
    
    public void getAllInstitute(Connection connection)
    {
        Institute institute = new Institute();
        institute.getAllInstitute(connection);
    }
    public void getAllBranch(Connection connection)
    {
        Institute institute = new Institute();
        institute.getAllBranch(connection);
    }
    public abstract boolean Login(Connection connection);
}