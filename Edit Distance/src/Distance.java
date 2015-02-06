import java.io.File;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.Collections;



public class Distance {
    
	//initializes the lists and variables
	private static ArrayList<String> sameSizeWords = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();
	private static List<String> nextWords = new LinkedList<String>();
	private static List<String> temp = new LinkedList<String>();
	private static List<String> memory = new LinkedList<String>();
	private static Map<String, String> prevWords = new HashMap<String, String>();
	private static int countRounds = 0;
	private static String startWord = JOptionPane.showInputDialog(null,"Enter the starting word");
	private static String endWord = JOptionPane.showInputDialog(null,"Enter the ending word");
	private static String message = "";

	static {
		readDataSize(new File("DicText/dict.txt"));	
	}
	
	private static void readDataSize(File file){
		try{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				words.add(scanner.next());
			}
		}catch(java.io.FileNotFoundException e){
			System.out.println("Error in ReadDataSize: e="+e);
		}
	}
	//creates a new list from the dict.txt file with words of equal length to the input words
	public static void getSameSizeWords(){
		for (String s: words) {
			if (s.length() == startWord.length()) {
				sameSizeWords.add(s);
			}
		}
	}
	//loops through sameSizeWords and checks for all words that share all but one letter with the word on nextWords
	public static void playEditDistance() {
		int count;
		for (String str: sameSizeWords){
		   for (int i = 0; i < nextWords.size(); i++){
			  count = 0;
		      for (int j = 0; j < startWord.length(); j++) {
			     if (str.charAt(j) == nextWords.get(i).charAt(j)){
				    count++;	
				    //System.out.println("Next word = " + nextWords.get(j));
			     }   
				 if (count == startWord.length()-1 && !str.equals(startWord) && !(memory.contains(str))) {
				    //System.out.println(str);
					prevWords.put(str, nextWords.get(i));
					temp.add(str);
					memory.add(str);
					//System.out.println(prevWords.keySet());
					//System.out.println(prevWords.get(str));
				 }
				  
			  }
		   }
           
	    }
	}
	//prints out the chain of words from the start word to the end word
	public static void printChain(String str) {
		List <String> chain = new LinkedList<String>();
		if (str.equals(startWord)){
			chain.add(startWord);
		} else {
			chain.add(str);
			printChain(prevWords.get(str));
		}
		Collections.reverse(chain);
		for (String s: chain){
			if (s.equals(endWord)){
				message += endWord;
				JOptionPane.showMessageDialog(null, message);
				//System.out.println(endWord);
			} else {
				message += s + "-> ";
			   //System.out.print(s + "-> ");
			}   
		}
		
	}
	//clears nextWords, adds the words on temp to nextWord, then clears temp
	public static void renewList(){
		while (!(nextWords.contains(endWord)) && !(nextWords.isEmpty())) {
			   playEditDistance();
			   nextWords.clear();
			   for (String tempString: temp) {
				  nextWords.add(tempString);
			   }
			   temp.clear();
			   countRounds++;
			   //System.out.println("next words = " + nextWords);
			   /*for (String s: prevWords.keySet()) {
					System.out.println("previous word = " + prevWords.get(s) + "   new word = " + s);
				}*/
			   
			} 
	}
	public static void main(String[] args) {
		getSameSizeWords();
		nextWords.add(startWord);
		memory.add(startWord);
		renewList();
		if (nextWords.contains(endWord)) {
			message = "The edit distance is " + countRounds + " with the following chain: \n";
			printChain(endWord);
		} else {
			JOptionPane.showMessageDialog(null, "The edit distance between \"" + startWord + "\" and \"" + endWord + "\" is undefined");
		}
		
    }	   
}
	
