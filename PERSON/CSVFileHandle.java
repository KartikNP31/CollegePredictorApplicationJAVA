package PERSON;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVFileHandle {
    static public void WritelineIntoCSV(String filepath, String[] data){

        File file =new File(filepath);

        try{

            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer =new CSVWriter(outputfile , ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(data);

            writer.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    static public void  addCSVtoDatabase(String filepath, Connection connection){
        try {
            BufferedReader lineReader =new BufferedReader(new FileReader(filepath)) ;
            String lineText ;
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details values (?,?,?,?,?,?,?)");
            while ((lineText = lineReader.readLine())!=null)
            {
                String[] data = lineText.split(",");
                String name = data[0];
                String email = data[1];
                String pass = data[2];
                String gender = data[3];
                String category = data[4];
                String generalRank = data[5];
                String categoryRank = data[6];

                preparedStatement.setString(1,name);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,pass);
                preparedStatement.setString(4,gender);
                preparedStatement.setString(5,category);
                preparedStatement.setInt(6, Integer.parseInt(generalRank));
                preparedStatement.setInt(7,Integer.parseInt(categoryRank));
                preparedStatement.addBatch();
            }

            lineReader.close();
            preparedStatement.executeBatch();


        } catch (Exception e) {
           // System.out.println(e);
        }
    }

}
