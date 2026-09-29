package e1;

public class StringCount {

    public static int countWords(String text) {

        if (text == null) {return 0;}

        int contador = 0;
        String[] spl = text.split(" ");

        for (String palabra : spl) {
            if (!palabra.isEmpty()) {
                contador++;
            }
        }
        return contador;
    }

    public static int countChar(String text, char c) {

        if (text == null) {return 0;}

        int contador = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == c) {
                contador++;
            }
        }

        return contador;
    }

    public static int countCharIgnoringCase(String text, char c) {
        if (text == null) {
            return 0;
        }

        int contador = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.toLowerCase(text.charAt(i)) == Character.toLowerCase(c)) {
                contador++;
            }
        }
        return contador;

    }

    public static boolean isPasswordSafe(String password) {
        
        if (password.length()<8) {return false;}

        boolean lowerCase =false, upperCase =false, digit =false, special =false;

        for (int i=0; i<password.length(); i++){

            if (Character.isLowerCase(password.charAt(i))) {
                lowerCase =true;
            }

            if (Character.isUpperCase(password.charAt(i))) {
                upperCase =true;
            }

            if (Character.isDigit(password.charAt(i))) {
                digit =true;
            }

            if (password.charAt(i)=='?' || password.charAt(i)=='@' || password.charAt(i)=='#' || password.charAt(i)=='.' || password.charAt(i)==',' || password.charAt(i)=='$')  {
                special =true;
            }

            if (lowerCase && upperCase && digit && special) {return true;}

        }

        return false;
    }
}