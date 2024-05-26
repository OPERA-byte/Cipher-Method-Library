import java.util.ArrayList;
import java.util.Arrays;

// Key: GLYPH, KeyStream: SATOR
public class Vigenere{
    /** This class implements the Vigenère cipher, a method of encrypting alphabetic text.
    *    It provides functionality to construct a Vigenère table, prepend a keyword to the base alphabet,
    *    create a new Vigenère table, print the Vigenère table, encrypt a plaintext message using a key stream,
    *    and format the key stream to match the length of the plaintext.
    */
    private static String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private static ArrayList <String> cipheredDictionary = new ArrayList<>(); 
    private static String keyStream;  
    
    /**
    * Constructs a Vigenère table (also known as a Vigenère square) using the provided base alphabet.
    * The Vigenère table is stored in the cipheredDictionary, where each row is a shifted version of
    * the base alphabet. The first row is the base alphabet itself, and each subsequent row is a
    * left-rotated version of the previous row.
    *
    * @param baseAlphabet The base alphabet used to construct the Vigenère table. This should be an
    *                     ArrayList of strings where each string is a single character.
    */
    public void constructVigenereTable(ArrayList<String> baseAlphabet){
        cipheredDictionary.clear(); 
        String tempCharacter; 
        ArrayList<String> subSet = new ArrayList<>(); 
        cipheredDictionary.add(String.join("", baseAlphabet));  
        for(int i = 0; i < baseAlphabet.size() - 1; i++){
           tempCharacter = baseAlphabet.get(0); 
           
           // Add all elements from the base alphabet, except the first one, to the subset
           subSet.addAll(baseAlphabet.subList(1,baseAlphabet.size())); 
           cipheredDictionary.add(String.join("", subSet) + tempCharacter); //Construct String 
           baseAlphabet.remove(tempCharacter); 
           baseAlphabet.add(tempCharacter);
           subSet.clear(); 
               
        }
    }
    
    /**
    * Prepends the characters of the given dictionary key to the front of the base alphabet.
    * The dictionary key is processed in reverse order so that the first character of the key
    * ends up at the front of the alphabet, followed by the subsequent characters.
    *
    * @param dictionaryKey The keyword to prepend to the base alphabet.
    * @return The modified base alphabet with the keyword characters prepended.
    */
    public ArrayList<String> prependKeywordToBaseAlphabet(String dictionaryKey){
    
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F",
                                                                   "G", "H", "I", "J", "K", "L", "M",
                                                                   "N", "O", "P", "Q", "R", "S", "T",
                                                                   "U", "V", "W", "X", "Y", "Z")); 
        char tempCharacter; 
        for(int i = 0; i < dictionaryKey.length(); i++){
            tempCharacter = dictionaryKey.charAt((dictionaryKey.length()-1) - i); // Starts from the last letter of keyword
            alphabet.remove(Character.toString(tempCharacter)); // Remove tempCharacter from alphabet 
            alphabet.add(0, Character.toString(tempCharacter)); // Add Temp Character to the alphabet 
        }
        //baseAlphabet.addAll(alphabet);
        return alphabet; 
    }
    
    
    /**
    * Creates a new Vigenère table by prepending the dictionary key to the base alphabet,
    * constructing the Vigenère table, and then printing the table.
    *
    * @param dictionaryKey The keyword to prepend to the base alphabet for the Vigenère table.
    */
    public void newVigenereTable(String dictionaryKey){
        ArrayList<String> baseAlphabet = new ArrayList<>(); 
        baseAlphabet.addAll(prependKeywordToBaseAlphabet(dictionaryKey)); 
        constructVigenereTable(baseAlphabet); 
        //printVigenereTable(); 
    }
    
    public void newVigenereTable(){
        ArrayList<String> baseAlphabet = new ArrayList<>(); 
        baseAlphabet.addAll(prependKeywordToBaseAlphabet("GLYPH")); 
        constructVigenereTable(baseAlphabet); 
        //printVigenereTable();
    }
    
    //Print VigenereTable 
    public void printVigenereTable(){
        System.out.println("       ABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
        System.out.println(); 
        
        for (int i = 0; i < cipheredDictionary.size(); i++){
            System.out.println("    "+ englishAlphabet.charAt(i)+ "  " + cipheredDictionary.get(i) + "     "); 
        }
    }
    
    /**
    * Encrypts the plaintext message using the provided key stream.
    * 
    * @param keyStream The key stream used for encryption.
    * @param plainText The plaintext message to be encrypted.
    * @return The ciphertext obtained by encrypting the plaintext message.
    */
    public String encrypt(String tempKeyStream, String plainText){
        keyStream = formatKeyStream(tempKeyStream, plainText); 
        ArrayList<String> cipherTextArr = new ArrayList<>(); 
        char tempPlainTextChar;
        int plainTextInt;  
        int keyStreamInt; 
        String tempCipherText; 
        
        for(int i = 0; i < plainText.length(); i++){
            if(!Character.isLetter(plainText.charAt(i))){
                cipherTextArr.add(Character.toString(plainText.charAt(i))); 
            }else{
                plainTextInt = cipheredDictionary.get(0).indexOf(plainText.charAt(i)); 
                keyStreamInt = cipheredDictionary.get(0).indexOf(keyStream.charAt(i)); 
                    
                tempCipherText = Character.toString(cipheredDictionary.get(plainTextInt).charAt(keyStreamInt)); 
        
                cipherTextArr.add(tempCipherText);
            }  
        }return String.join("", cipherTextArr); 
    }
    
    /**
     * Encrypts the given plaintext message using the Vigenère cipher.
    *
    * This method first constructs a new Vigenère table, formats the key stream to match the length
    * of the plaintext using the keyword "SATOR", and then encrypts each character of the plaintext.
    * Non-letter characters in the plaintext are added directly to the ciphertext without encryption.
    *
    * @param plainText The plaintext message to be encrypted.
    * @return The resulting ciphertext after encryption.
    */
    public String encrypt(String plainText){
        newVigenereTable(); 
        String keyStream = formatKeyStream("SATOR", plainText); 
        ArrayList<String> cipherTextArr = new ArrayList<>(); 
        char tempPlainTextChar;
        int plainTextInt;  
        int keyStreamInt; 
        String tempCipherText; 
        
        for(int i = 0; i < plainText.length(); i++){
            if(!Character.isLetter(plainText.charAt(i))){
                cipherTextArr.add(Character.toString(plainText.charAt(i))); 
            }else{
                plainTextInt = cipheredDictionary.get(0).indexOf(plainText.charAt(i)); 
                keyStreamInt = cipheredDictionary.get(0).indexOf(keyStream.charAt(i)); 
                    
                tempCipherText = Character.toString(cipheredDictionary.get(plainTextInt).charAt(keyStreamInt)); 
        
                cipherTextArr.add(tempCipherText);
            }  
        }return String.join("", cipherTextArr);
    }
    
    /**
    * Formats the key stream to match the length of the plaintext by repeating the key stream if necessary.
    * 
    * @param keyStream The key stream to be formatted.
    * @param plainText The plaintext whose length will be used for formatting the key stream.
    * @return The formatted key stream with the same length as the plaintext.
    */
    public String formatKeyStream(String keyStream, String plainText){
    
        int plainTextLength = plainText.length(); 
        int keyStreamLength = keyStream.length(); 
        char tempCharacter; 
        
        for(int i = 0; i <(plainTextLength - keyStreamLength); i++){
            tempCharacter = keyStream.charAt(i); 
            keyStream += tempCharacter; 
        
        }return keyStream; 
    }


    

}

