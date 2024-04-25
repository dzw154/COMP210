package assn07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

//        passwordManager.put("Aa", "1");
//        passwordManager.put("Aa", "2");
//        passwordManager.put("BB", "2");
//        passwordManager.put("BBBBBB","3");
//        passwordManager.put("BBAaBB","6");
//        passwordManager.put("AaAaAa","6");
//        passwordManager.put("AaBBBB","6");
//        System.out.println(passwordManager.get("Aa"));
//        System.out.println(passwordManager.keySet());
//        System.out.println(passwordManager.size());
//        System.out.println(passwordManager.remove("aaa"));
//        System.out.println(passwordManager.size());
//        String _pass = passwordManager.generatesafeRandomPassword(4);
//        System.out.println(_pass);

        // your code below
        System.out.println("Enter Master Password");
        String phrase = scanner.nextLine();
        while (!passwordManager.checkMasterPassword(phrase)){
            System.out.println("Enter Master Password");
            phrase = scanner.nextLine();
        }
        while (!phrase.equals("Exit")){
            phrase = scanner.nextLine();

            // Command not found
            if (!phrase.equals("New password") && !phrase.equals("Get password")
                    && !phrase.equals("Delete account") && !phrase.equals("Check duplicate account")
                    && !phrase.equals("Get accounts") && !phrase.equals("Generate random password")
                    && !phrase.equals("Exit")){
                System.out.println("Command not found");
            }
            else{

                // New password
                if (phrase.equals("New password")){
                    String website = scanner.nextLine();
                    String password = scanner.nextLine();
                    passwordManager.put(website, password);
                    System.out.println("New password added");
                }

                // Get password
                if (phrase.equals("Get password")){
                    String website = scanner.nextLine();
                    if (passwordManager.get(website) == null){
                        System.out.println("Account does not exist");
                    }
                    else{
                        System.out.println(passwordManager.get(website));
                    }
                }

                // Delete account
                if (phrase.equals("Delete account")){
                    String website = scanner.nextLine();
                    if (passwordManager.remove(website) == null){
                        System.out.println("Account does not exist");
                    }
                    else{
                        passwordManager.remove(website);
                        System.out.println("Account deleted");
                    }
                }

                // Check duplicate account
                if (phrase.equals("Check duplicate account")){
                    String pass = scanner.nextLine();
                    if (passwordManager.checkDuplicate(pass) == null){
                        System.out.println("No accounts using that password");
                    }
                    else{
                        System.out.println("Accounts using that password:");
                        Object[] shared = passwordManager.checkDuplicate(pass).toArray();
                        for (int i = 0; i < shared.length; i++){
                            System.out.println(shared[i]);
                        }
                    }
                }

                // Get accounts
                if (phrase.equals("Get accounts")){
                    System.out.println("Your accounts:");
                    Object[] accounts = passwordManager.keySet().toArray();
                    for (int i = 0; i < accounts.length; i++){
                        System.out.println(accounts[i]);
                    }
                }

                // Generate random password
                if (phrase.equals("Generate random password")){
                    int Size = scanner.nextInt();
                    System.out.println(passwordManager.generatesafeRandomPassword(Size));
                }

                // Exit
                if (phrase.equals("Exit")){
                    break;
                }
            }
        }






        // infite loop to go back to "Enter master password"



        // loop to read and execute commands until "Exit" is entered




    }
}
