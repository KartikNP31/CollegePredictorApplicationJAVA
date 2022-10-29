// import java.sql.*;
// import java.util.*;

public class Admin extends Person{

    private final String adminRole = "admin";

    public void register(String userName, String email, String password) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        super.setRole(adminRole);
    }

    public boolean login(String userName, String password) {
        if(userName.compareTo("admin_group_15_iiits")==0 && password.compareTo("@IIIts_#oop_sec1")==0)
        {
            return true;
        }
        else{
            return false;
        }
    }

}
