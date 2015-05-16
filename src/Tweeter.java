import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.IOException;

public class Tweeter {

	public static void main(String[] args) {
		String topic = args[0];
		ArrayList<Status> tweets = readTweets(topic);
		HashMap<Integer, Integer> sentiments = new HashMap<Integer, Integer>();

		sentiments.put(0, 0);
		sentiments.put(1, 0);
		sentiments.put(2, 0);
		sentiments.put(3, 0);
		sentiments.put(4, 0);

		NLP.init();
		for(Status tweet : tweets) {
			int sentiment = NLP.findSentiment(CleanTweet.clean(tweet.getText()));
			System.out.println(CleanTweet.clean(tweet.getText()) + " : " + sentiment);
			sentiments.put(sentiment, sentiments.get(sentiment)+1);
			if(tweet.getPlace() != null)
				System.out.println("Place: " + tweet.getPlace().getFullName());
		}

		for (Integer i: sentiments.keySet()) {
			System.out.println("Sentiment " + i + " --> Count " + sentiments.get(i));
		}
	}

	static ArrayList<Status> readTweets(String topic)
	{
		ArrayList<Status> tweets = null;
		if(doesFileExists(topic)) {
			tweets = readTweetsFromFile(topic);
		} else {
			tweets = TweetManager.getTweets(topic);
			writeTweetsToFile(topic, tweets);			
		}
		return tweets;
	}

	static void writeTweetsToFile(String filename, ArrayList<Status> list)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream("serialized/" + filename + ".data");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(list);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in " + filename);
		} 
		catch(IOException i) {
			System.out.println("Error saving Serialized data of tweets");
			i.printStackTrace();
		}
	}

	static ArrayList<Status> readTweetsFromFile(String filename)
	{
		ArrayList<Status> e = null;
		try
		{
			FileInputStream fileIn = new FileInputStream("serialized/" + filename + ".data");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (ArrayList<Status>) in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i) {
			i.printStackTrace();
		} catch(ClassNotFoundException c) {
			System.out.println("ArrayList<Status> class not found");
			c.printStackTrace();
		}
		return e;
	}

	static boolean doesFileExists(String filename)
	{
		File f = new File("serialized/" + filename + ".data");
		if(f.exists() && !f.isDirectory())
			return true;
		else 
			return false;
	}
}