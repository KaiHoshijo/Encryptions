import java.util.Scanner;

public class CaesarCipher {
  private static final Scanner console = new Scanner(System.in);
  private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
  private static String message;
  public static void main(String[] args) {
    System.out.println("Welcome to Caesar Cipher! Do you want to encrypt or decrypt [E, d]: ");

    while (true) {
      String mode = console.nextLine().toLowerCase();
      if (mode.equals("E") || mode.equals("")) {
        // the encryption of the key
        System.out.print("What key do you want to use: ");
        // verifies that the user input an integer for the key
        int printOnce = 0;
        while (!console.hasNextInt()) {
          if (printOnce == 0) {
            System.out.print("Invalid. Try again: "); 
            printOnce++;
          }
          console.next();
        }
        int key = console.nextInt();
        console.nextLine();
        // establishes the message to be encrypted
        setMessage();
        // prints the encrypted message
        System.out.println(encryptMessage(message, key));
        break;
      } else if (mode.equals("d")) {
        // establishes the message to be decrypted
        setMessage();
        // prints all shifts of that message
        decryptMessage(message);
        break;
      } else {
        System.out.print("Invalid. Try again [E, d]: ");
      }
    }
    console.close();
  }
  
  public static void setMessage() {
    System.out.print("What is your message: ");
    message = console.nextLine().toLowerCase();
  }

  public static String encryptMessage(String message, int key) {
    String output = "";
    for (int i = 0; i < message.length(); i++) {
      // checks if the character is a part of the English lexicon
      int index = alpha.indexOf(message.charAt(i));
      if (index != -1) {
        // does the caesar shift of the message 
        index = (index + key) % 26;
        output += alpha.charAt(index);
      } else {
        output += message.charAt(i);
      }
    }
    return output;
  }

  public static void decryptMessage(String message) {
    for (int key = 1; key < 26; key++) {
      String decrypted = "";
      for (int i = 0; i < message.length(); i++) {
        // verifies that it's not a special character
        int index = alpha.indexOf(message.charAt(i));
        if (index != -1) {
          // reverses caesar key
          index = (index - key);
          // if it's negative then 26 must be added before it can reverse shift
          index = index > 0 ? index % 26 : (index + 26) % 26;
          decrypted += alpha.charAt(index);
        } else {
          decrypted += message.charAt(i);
        }
      }
      // prints out the decrypted shift of key
      System.out.println(decrypted + " key is: " + key + "\n");
    }
  }
}