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

		text = text.replace(at, "").replace(hash, "").replace("\n", "").replaceAll("[^\\x00-\\x7f]", "");
	
		return text;
	}
}