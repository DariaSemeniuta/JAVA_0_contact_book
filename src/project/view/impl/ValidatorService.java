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

    public static String readDate() throws IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String inputDate = input.readLine();
        try{
           boolean flag = dateFormat.parse(inputDate).toString().equals(inputDate);
        } catch (ParseException e){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct date of birthday => ");
            return readDate();
        }
        return inputDate;
    }
}
