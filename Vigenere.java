import java.util.ArrayList;
import java.util.Arrays;


public class Vigenere{

    private static String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private static ArrayList <String> cipheredDictionary = new ArrayList<>(); 
    
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
        printVigenereTable(); 
    }
    
    //Print VigenereTable 
    public void printVigenereTable(){
        System.out.println("       ABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
        System.out.println(); 
        
        for (int i = 0; i < cipheredDictionary.size(); i++){
            System.out.println("    "+ englishAlphabet.charAt(i)+ "  " + cipheredDictionary.get(i) + "     "); 
        }
    
    }
    
    
    public static void main(String[]args){
    
        Vigenere program = new Vigenere(); 
        program.newVigenereTable("KRYPTOS");  
    
    }

    





}

