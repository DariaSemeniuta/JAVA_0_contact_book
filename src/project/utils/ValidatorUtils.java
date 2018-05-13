package project.utils;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidatorUtils {

    private ValidatorUtils(){
    }

    public static Boolean readInt(String inputInt){
        try {
              new Integer(inputInt);
            } catch (NumberFormatException e) {
                return false;
            }
        return true;
    }
    
    public static Boolean readPhone(String inputPhone){
        String[] regex = {"^[+]\\d{12}", "^[0]\\d{9}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}\\d{2}\\d{2}", "^[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[(]\\d{3}[)]\\d{3}[-]\\d{2}[-]\\d{2}", "^[+]\\d{1}[-]\\d{3}[-]\\d{3}[-]\\d{2}[-]\\d{2}", "^[0]\\d{2}[-]\\d{3}[-]\\d{2}[-]\\d{2}"};

        for(int i = 0; i < regex.length; ++i){
            if(inputPhone.matches(regex[i])){
                return true;
            }
        }
        return false;
    }

    public static Boolean readDate(String inputDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try{
           if(!(dateFormat.format(dateFormat.parse(inputDate)).equals(inputDate))){
               return false;
           }               
        } catch (ParseException e){
            return false;
        }
        return true;
    }
}
