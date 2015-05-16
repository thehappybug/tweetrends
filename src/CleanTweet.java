import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import com.twitter.Extractor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CleanTweet {

	public static String clean (String text) {


		String hash = "#";
		String at = "@";

		Extractor extractor = new Extractor();

		List <String> urls = extractor.extractURLs(text);
		for(String url: urls) {
		    text = text.replace(url, "");
		}

		text = text.replace(at, "").replace(hash, "");
	
		/*
		int i=0;
		File file = new File("SlangLookupTable.txt");

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;

			String[][] slangs = {{"", ""}};

			while ((line = bufferedReader.readLine()) != null) {
				String[] word = line.split("::");
			    //slangs[i][0] = word[0];
			    //slangs[i++][1] = word[1]; 
			    System.out.println(word[0] + "\t" + word[1]);
			    //System.out.println(line);
			}
			fileReader.close();
		}
		catch (Exception e) {

		}
		finally {
			
		}
		System.out.println(i);
		*/
		return text;

	}

	public static void main(String[] args) {

		System.out.println(clean("HELLO! #happy hey"));
		System.out.println(clean("@umer: HELLO! #happy"));
		System.out.println(clean("HELLO! @umer #happy"));
		System.out.println(clean("HELLO! @noor www.google.com #happy"));
		System.out.println(clean("HELLO! http://www.twitter.com #happy"));
		System.out.println(clean("HELLO! bitly.com #happy"));
		System.out.println(clean("HELLO! #happy"));
		System.out.println(clean("HELLO! #happy"));

	}
}