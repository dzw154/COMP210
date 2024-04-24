package assn07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        passwordManager.put("Aa", "1");
        passwordManager.put("Aa", "2");
        passwordManager.put("BB", "2");
        passwordManager.put("BBBBBB","3");
        passwordManager.put("BBAaBB","6");
        passwordManager.put("AaAaAa","6");
        passwordManager.put("AaBBBB","6");
        System.out.println(passwordManager.get("Aa"));
//        System.out.println(passwordManager.keySet());
//        System.out.println(passwordManager.size());
//        System.out.println(passwordManager.remove("aaa"));
//        System.out.println(passwordManager.size());
//        String _pass = passwordManager.generatesafeRandomPassword(4);

//        System.out.println(_pass);


        // infite loop to go back to "Enter master password"



        // loop to read and execute commands until "Exit" is entered




    }
}
