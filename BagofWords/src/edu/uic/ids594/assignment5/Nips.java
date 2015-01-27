/**
 * 
 */
package edu.uic.ids594.assignment5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author himansubadhai
 *
 */
public class Nips {

	// define folder and file paths

	private static final String docWord = "/Users/himansubadhai/Documents/input/assignment5/docword.nips.txt";
	private static final String vocab ="/Users/himansubadhai/Documents/input/assignment5/vocab.nips.txt";
	private static final String outputpath ="/Users/himansubadhai/Documents/input/assignment5/output/";

	// define variables
	private HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
	private BufferedReader reader;
	private PrintWriter printWriter;

	//read vocab file and store words in hashmap
	public void readFile(){
		try{
			reader=new BufferedReader(new FileReader(vocab));
			String line=reader.readLine().trim();
			int pos=1;

			while(line!=null){		
				// add the value to hashmap
				this.hashMap.put(pos, line);
				line = reader.readLine();
				pos++;
			}
			System.out.println("Words added to hashmap");
			reader.close();

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}

	}

	public void generateFiles (){
		try {
			reader=new BufferedReader(new FileReader(docWord));

			// skip first 3 lines
			reader.readLine();
			reader.readLine();
			reader.readLine(); 		

			String line = null;

			while((line=reader.readLine())!=null){		

				// split the line to get the document ID, word ID and word Frequency
				String [] tokens= line.split(" ");
				int docID = Integer.parseInt(tokens[0]);
				int wordID = Integer.parseInt(tokens[1]);
				int wordFrequency = Integer.parseInt(tokens[2]);

				File file = new File ("");

				file=new File(outputpath+docID+".txt");

				// if file doesn't exist create new file
				if (!file.exists()){
					file.createNewFile();
				}
				printWriter=new PrintWriter(new BufferedWriter(new FileWriter(file,true)));

				if(hashMap.containsKey(wordID)){

					// generate file
					for(int count=0; count < wordFrequency; count++){
						printWriter.println(hashMap.get(wordID));
					}
				}
				printWriter.close();

			}
			reader.close();

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nips nips = new Nips();
		nips.readFile();
		nips.generateFiles();

	}

}
