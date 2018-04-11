package project.view.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidatorService {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    private ValidatorService(){
    }

    public static int readInt() throws IOException{
        int age;
        try {
                String inputInt = input.readLine();
                age = new Integer(inputInt);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct age => ");
                return readInt();
            }
        return age;
    }
    
    public static String readPhone() throws IOException{
        String[] regex = {"^[+]\\d{12}", "^[0]\\d{9}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[-]\\d{3}[-]\\d{3}[-]\\d{2}[-]\\d{2}", "^[0]\\d{2}[-]\\d{3}[-]\\d{2}[-]\\d{2}"};
        String inputPhone = input.readLine();
        
        for(int i = 0; i < regex.length; ++i){
            if(inputPhone.matches(regex[i])){
                return inputPhone;
            }
        }
        System.out.println("Incorrect format of input phoneNumber!");
        System.out.print("Please enter correct phone number => ");
        return readPhone();
    }

    public static String readDate() throws IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String inputDate = input.readLine();
        try{
           if(!(dateFormat.format(dateFormat.parse(inputDate)).equals(inputDate))){
               System.out.println("Incorrect format of input value!");
               System.out.print("Please enter correct date of birthday => ");
               return readDate();
           }               
        } catch (ParseException e){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct date of birthday => ");
            return readDate();
        }
        return inputDate;
    }
}
