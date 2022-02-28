package utility;

import pojos.Registrant;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToTxt {

    public static void generateData(Registrant registrant,String fileName){

        try{

            FileWriter fileWriter = new FileWriter(fileName,true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.append(registrant.getFirstName()+" , "+registrant.getLastName()+" , "+
                          registrant.getLogin()+" , "+registrant.getSsn()+" , "+
                          registrant.getEmail()+" , "+registrant.getPassword()+"\n");

            writer.close();


        }catch (Exception e){

        }

    }

}
