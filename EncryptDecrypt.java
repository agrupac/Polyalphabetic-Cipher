import java.util.Random;
/**
 * A class that handles the encryption and decryption of text.
 * @author maha
 * @author Aidan Grupac
 */
public class EncryptDecrypt {

    /**this instance variable contains the size of the plainText in the ThreeTenCipher class.
     *
     */
    private int plainTextSize;

    /** plainTextSize setter.
     *
     * @param s set equal to the length of plainText from ThreeTenCipher class
     */
    public void setPlainTextSize(int s) {
        plainTextSize = s;
    }

    /** plainTextSize getter.
     *
     * @return the plainTextSize
     */
    public int getPlainTextSize() {
        return plainTextSize;
    };

    /**this integer array of size equal to plainTextSize.
     * contains the random numbers to be used by the encrypt/decrypt methods.
     *
     */
    private int[] selectAlpha;

    /**
    * This alphabet array is used in ecryption/decryption.
    */
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
    * Returns the index of a given character in the alphabet array.
    *
    * @param c a character to search for
    * @return the index of the character
    */
    private int alphabetIndexOf(char c){
        for(int i = 0; i < 26; i++){
            if(alphabet[i] == c) return i;
        }
        return -1;
    }

    /**
    * Returns the index of a given character in the given key.
    *
    * @param key the index of the key in keys matrix
    * @param c the character to search for
    * @return the index of the character
    */
    private int keysIndexOf(int key, char c){
        for(int i = 0; i < 26; i++){
            if(ThreeTenCipher.keys[key][i] == c) return i;
        }
        return -1;
    }

    /**first randomly sets the selectAlpha array to random numbers between 0 and 4 this number indicates which alphabet cipher is used for each letter in plainText.
     * accesses the plainText in the ThreeTenCipher then encrypts it and sets the cipherText in the ThreeTenCipher.
     */
    public void encrypt() {

        ThreeTenCipher.cipherText = "";
        Random rand = new Random();

        selectAlpha = new int[plainTextSize];
        for(int i = 0; i < selectAlpha.length; i++){
            selectAlpha[i] = rand.nextInt(5);
        }

        char[] textArray = ThreeTenCipher.plainText.toLowerCase().toCharArray();

        for(int i = 0; i < textArray.length; i++){
            if(textArray[i] >= 'a' && textArray[i] <= 'z'){
                textArray[i] = ThreeTenCipher.keys[selectAlpha[i]][alphabetIndexOf(textArray[i])];
            }
            ThreeTenCipher.cipherText += textArray[i];
        }

    }

    /** uses the cipherText from the ThreeTenCipher and the instance variable selectAlpha.
     * decrypts the cipher text then calls the archiveDecrypted to store the resulting string in the ThreeTenCipher testArchive instance variable.
     * @return the resulting text
     */
    public String decrypt() {

        char[] textArray = ThreeTenCipher.cipherText.toCharArray();
        String result = "";

        for(int i = 0; i < selectAlpha.length; i++){
            if(textArray[i] >= 'A' && textArray[i] <= 'Z'){
                textArray[i] = alphabet[keysIndexOf(selectAlpha[i],textArray[i])];
            }
            result += textArray[i];
        }

        return result;
    }

}
