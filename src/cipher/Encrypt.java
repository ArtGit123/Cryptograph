package cipher;

public class Encrypt {
	public String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char i : text.toCharArray()) { // Convert String into char array
            if (Character.isLetter(i)) { // If the current char is a letter
                if (Character.isUpperCase(i)) { // Uppercase
                    encrypted.append((char) ('A' + (i - 'A' + shift) % 26 ));
                } else { // Lowercase
                    encrypted.append((char) ('a' + (i - 'a' + shift) % 26 ));
                }
            }
            else if (Character.isSpaceChar(i)) { // Space
            	encrypted.append((char)(' ' + shift));
            }
            else { // If not a letter
                encrypted.append(i);
            }
        }
        return encrypted.toString(); // Convert StringBuilder to String
    }
}
