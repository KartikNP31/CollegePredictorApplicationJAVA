package CSV;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class CSVFileHandle {
    static public void WriteLineIntoCSVForDeletionAndUpdation(String filepath, String[] data) {
        
        File file = new File(filepath);
        
        try {
            
            FileWriter outPutFile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outPutFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(data);
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Application error : File handling problem.");
        }
    }
    
    static public void WritelineIntoCSV(String filepath, String[] data) {
        
        File file = new File(filepath);
        
        try {
            
            FileWriter outPutFile = new FileWriter(file, false);
            CSVWriter writer = new CSVWriter(outPutFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(data);
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Application error : File handling problem.");
        }
    }
    
    static public void addCSVtoDatabase(String filepath, Connection connection) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details values (?,?,?,?,?,?,?)");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String name = data[0];
                String email = data[1];
                String pass = data[2];
                String gender = data[3];
                String category = data[4];
                String generalRank = data[5];
                String categoryRank = data[6];
                
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, pass);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, category);
                preparedStatement.setInt(6, Integer.parseInt(generalRank));
                preparedStatement.setInt(7, Integer.parseInt(categoryRank));
                preparedStatement.addBatch();
            }
            
            lineReader.close();
            preparedStatement.executeBatch();
            System.out.println("User data uploaded");
            
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    static public void addUser_deletedCsvToDatabasesUser_deleted(String filepath, Connection connection) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_deleted values (?,?,?,?,?,?,?)");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String name = data[0];
                String email = data[1];
                String pass = data[2];
                String gender = data[3];
                String category = data[4];
                String generalRank = data[5];
                String categoryRank = data[6];
                
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, pass);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, category);
                preparedStatement.setInt(6, Integer.parseInt(generalRank));
                preparedStatement.setInt(7, Integer.parseInt(categoryRank));
                preparedStatement.addBatch();
            }
            
            lineReader.close();
            preparedStatement.executeBatch();
            System.out.println("Deleted User data uploaded to User_deleted table");
            
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    
    static public void UpdateUserData_CSVtoDatabase(String filepath, Connection connection) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            Statement statement = connection.createStatement();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String name = data[0];
                String email = data[1];
                String value = data[2];
                String attribute = data[3];
                statement.addBatch("update user_details set "+attribute+" = '"+value+"' where username= '"+name+"' and email= '"+email+"'");
            }
            lineReader.close();
            statement.executeBatch();
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    static public void UploadJosaaRoundCutoffToDatabase(String filepath, Connection connection, int round) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            String roundNo = "round".concat(Integer.toString(round));
            lineReader.readLine();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into " + roundNo + " values (?,?,?,?,?,?,?)");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Institute = data[0];
                String Program = data[1];
                String quota = data[2];
                String category = data[3];
                String gender = data[4];
                String Opening_Rank = data[5];
                String Closing_Rank = data[6];
                
                preparedStatement.setString(1, Institute);
                preparedStatement.setString(2, Program);
                preparedStatement.setString(3, quota);
                preparedStatement.setString(4, category);
                preparedStatement.setString(5, gender);
                preparedStatement.setString(6, Opening_Rank);
                preparedStatement.setString(7, Closing_Rank);
                
                preparedStatement.addBatch();
            }
            
            lineReader.close();
            preparedStatement.executeBatch();
            System.out.println("User data uploaded");
            
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity problem.");
        }
    }
    
    
    static public void DeleteCSVFIle(String filepath) {
        try {
            File f = new File(filepath);
            if (f.delete())
            {
                System.out.println(f.getName() + " deleted after execution.");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("Application error : File handling problem.");
        }
    }
    
    
}
