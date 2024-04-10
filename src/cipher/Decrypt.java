package cipher;

public class Decrypt {
	public String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char i : text.toCharArray()) { // Convert String into char array
            if (Character.isLetter(i)) { // If the current char is a letter
                if (Character.isUpperCase(i)) { // Uppercase
                	decrypted.append((char) ('A' + (i - 'A' - shift + 26) % 26 )); 
                } else { // Lowercase											Compared to Encrypt, the + and - are reversed
                	decrypted.append((char) ('a' + (i - 'a' - shift + 26) % 26 ));
                }
            }
            else if (i == ' ' + shift) { // Space
            	decrypted.append(' ');
            }
            else { // If not a letter
            	decrypted.append(i);
            }
        }
        return decrypted.toString(); // Convert StringBuilder to String
    }
}
