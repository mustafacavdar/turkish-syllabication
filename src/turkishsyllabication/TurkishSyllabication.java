/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turkishsyllabication;

import java.util.ArrayList;

/**
 *
 * @author Mustafa
 */
public class TurkishSyllabication {

 
    public static void main(String[] args) {
     
        String word = "";
        ArrayList<String> syllables = new ArrayList<>();
        System.out.println(divideWordIntoSyllables(word, syllables));
    }
    
    public static ArrayList<String> divideWordIntoSyllables(String word, ArrayList<String> syllables){
        
        String vowels = "aeıioöuü";
        word = word.toLowerCase();
        int firstVowelIndex = -1;
        boolean firstVowelFound = false;
        int vowelCount = 0;
        
        //find first vowel
        for(int i = 0; i < word.length(); i++){
            if(vowels.indexOf(word.charAt(i)) >= 0){
                if(!firstVowelFound){
                    firstVowelIndex = i;
                    firstVowelFound = true;
                }
                vowelCount++;
            }
        }
        
        //base case
        if(vowelCount == 1){
            syllables.add(word);
        }
        
        else{
        
            //if next letter is a vowel, then divide after first one. e.g. pro-aktif
            if(vowels.indexOf(word.charAt(firstVowelIndex + 1)) >= 0){
                syllables.add(word.substring(0, firstVowelIndex + 1));
                divideWordIntoSyllables(word.substring(firstVowelIndex + 1), syllables);
            }
            
            //if next letter is a consonant and the one after it is a vowel, divide after first vowel. e.g. pa-ra
            else if(vowels.indexOf(word.charAt(firstVowelIndex + 2)) >= 0){
                syllables.add(word.substring(0, firstVowelIndex + 1));
                divideWordIntoSyllables(word.substring(firstVowelIndex + 1), syllables);
            }
            
            //if next two letters are consonants and the one after them is a vowel, divide after first consonant e.g. par-ti
            else if(vowels.indexOf(word.charAt(firstVowelIndex + 3)) >= 0){
                syllables.add(word.substring(0, firstVowelIndex + 2));
                divideWordIntoSyllables(word.substring(firstVowelIndex + 2), syllables);
            }
            
            //if next three are all consonants
            else{
                
                String temp = word.substring(firstVowelIndex + 1, firstVowelIndex + 4);
                
                //if three consonants are "str", "ktr" or "ntr", then divide after first consonant. e.g. san-tral
                if(temp.equals("str") || temp.equals("ktr") || temp.equals("ntr")){
                    syllables.add(word.substring(0, firstVowelIndex + 2));
                    divideWordIntoSyllables(word.substring(firstVowelIndex + 2), syllables);
                }
                
                //otherwise, divide after second consonant. e.g. Türk-çe
                else{
                    syllables.add(word.substring(0, firstVowelIndex + 3));
                    divideWordIntoSyllables(word.substring(firstVowelIndex + 3), syllables);
                }
            }
        }
        return syllables;
    }
    
}
