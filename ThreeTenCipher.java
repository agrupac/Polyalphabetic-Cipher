/**
 * A class that drives the encryption, decryption, and storage of text.
 *
 * @author Aidan Grupac
 */
public class ThreeTenCipher {

    /**
     * stores the 5 cipher alphabets each of 26 characters length.
     */
    public static char[][] keys;

    /**
    * This is the cipher text to be decrypted.
     */
    public static String cipherText;

    /**
    * This is the plain text to be encrypted.
     */
    public static String plainText;

    /**
    * a character array with initial capacity of 100 that contains all decoded text blocks.
     */
    private char textArchive[];

    /**
    * the size of the stored text in textArchive.
     */
    private int sizeStored = 0;

    /**
    * An exception for cleanArchivedText().
    */
    public static class NoTextException extends Exception {
        /**
        * A constructor.
        *
        * @param errorMessage a description of the error.
        */
        public NoTextException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
    * constructor that initializes the keys array, the textArchive, the cipherText, the plaintext, and sets the sizeStored to 0.
    */
    public ThreeTenCipher() {
		keys = new char[5][26];
		textArchive = new char[100];
		cipherText = "";
		plainText = "";
	}

    /**
    * Adds a new text to the textArchive.
     * If textArchive is full, it will increase the size to 1.5 the original size to accommodate the new text inserted.
     * This method should be O(n).
     * @param newText  the new text to be added to textArchive.
     */
    public void insertText(char[] newText) {

        int k = 0;
        int sizeCopy = sizeStored;
        for(int i = sizeCopy; i < sizeCopy + newText.length; i++){
            if(isFull()){
                //create temporary array 1.5 larger than archive
                char[] copy = new char[(int)((float)textArchive.length * 1.5 + 0.5)];
                //transfer contents of archive to temporary array
                for(int j = 0; j < textArchive.length; j++){
                    copy[j] = textArchive[j];
                }
                //reassign archive and delete temporary array
                textArchive = copy;
                copy = null;
            }
            //once archive has available capacity
            textArchive[i] = newText[k++];
            sizeStored++;
        }

    }

    /**removes all the character data from start to start + size-1.
     * It throws NoTextException, if there is no data to remove.
     * It should shrink the size after deleting the corresponding character data by setting it to a new capacity of capacity-size.
     * You need to check that the indices are valid or else throw an ArrayIndexOutOfBounds exception.
     * Returns true if successful.
     * This method should be O(n).
     *
     * @param start the index in textArchive from which to start removing
     * @param size the number of elements to remove from textArchive
     * @return returns true if successful
     * @throws NoTextException when there is no text to remove
     */
    public boolean cleanArchivedText(int start, int size) throws NoTextException {
        //edge case checks
        if(isEmpty()) throw new NoTextException("No data to remove.");
        if(start < 0 || start >= textArchive.length || size < 1 || size > textArchive.length) throw new ArrayIndexOutOfBoundsException("Invalid indicies.");
        //keep track of empty data and mark data to be removed as null
        int nullElements = 0;
        for(int i = start; i < start + size; i++){
            if(textArchive[i] == '\u0000') nullElements++;
            textArchive[i] = '\u0000';
        }
        //if all elements to be removed were null, throw exception
        if(nullElements == size) throw new NoTextException("No data to remove.");
        //create shrunken array and copy non-null elements
        char[] copy = new char[textArchive.length - size];
        int i = 0;
        for(char c : textArchive){
            if(c != '\u0000'){
                copy[i] = c;
                i++;
            }
        }
        textArchive = copy;
        copy = null;

        sizeStored -= size;

        return true;
    }

    /**
     * Maximum storage capacity of textArchive.
     * @return returns the maximum storage capacity of textArchive.
     */
    public int getStorageCapacity() {
        return textArchive.length;
    }

    /**
     * Getter for size.
     * @return returns the size of character data inserted in the textArchive.
     */
    public int getSize() {
        return sizeStored;
    }

    /**
     * Checks if the textArchive is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return sizeStored == 0;
    }

    /**checks if the textArchive is full.
     *
     *  @return true if textArchive is full
     */
    public boolean isFull() {
        return sizeStored == textArchive.length;
    }

    /** cipherText setter.
     *
     * @param cipher to set cipherText
     */
    public void setCipherText(String cipher) {
        cipherText = cipher;
    }

    /** cipherText getter.
     *
     * @return cipherText
     */
    public String getCipherText() {
        return cipherText;
    }

    /** plainText setter.
     * @param plain to set plainText
     */
    public void setPlainText(String plain) {
        plainText = plain;
    }

    /** plainText getter.
     *
     * @return plainText
     */
    public String getPlainText() {
        return plainText;
    }

    /** sets the keys.
     * set the size of keys to be 5 of 26 length cipher alphabets.
     *
     * @param keys the cipher alphabets of 26 length each
     * @param size 5 cipher alphabet keys
     */
    public void setKeys(char[][] keys, int size) {
        this.keys = keys;
    }

    /**
     * returns the characters of textArchive from start to size-1.
     *
     * @param start which index to begin at
     * @param size how many characters to get
     * @return selectedChars the desired portion of textArchive
     */
    public char[] getTextArchive(int start, int size) {

        char[] selectedChars = new char[size-start];
        int j = 0;
        for(int i = start; i < start + size; i++){
            selectedChars[j++] = textArchive[i];
        }
        return selectedChars;
    }

}
