package assn07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        passwordManager.put("abc", "1");
        passwordManager.put("abc", "2");
        passwordManager.put("abc","3");
        passwordManager.put("a","6");
        System.out.println(passwordManager.get("a"));
        System.out.println(passwordManager.keySet());
        System.out.println(passwordManager.size());
        System.out.println(passwordManager.remove("aaa"));
        System.out.println(passwordManager.size());
        String _pass = passwordManager.generatesafeRandomPassword(4);
        int count = 0;
        while (_pass.length() == 4 && count <= 100){
            _pass = passwordManager.generatesafeRandomPassword(4);
            count ++;
        }
        System.out.println(_pass);


        // infite loop to go back to "Enter master password"



        // loop to read and execute commands until "Exit" is entered




    }
}
