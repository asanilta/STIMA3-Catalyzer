/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterstima;

import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Tifani
 */
public class TwitterAPI {
    private static TwitterFactory tf;
    
    public static void initializeTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("tZqfO2sBgnOkrV6TS5vf2d9YI")
          .setOAuthConsumerSecret("4x4K4kM6aKiC3Q9gJBjxZFCny1Hk6JlySq6LoEMgNqfIq6G3Os")
          .setOAuthAccessToken("69652133-MRIXAqi7n1fjKgof4yDxGttvKeK47z0vtR7zmNNsB")
          .setOAuthAccessTokenSecret("ibUkFFGqfzKBFjwnR8dtQoJ0m4qV0JOTXbKupKoyTmEaK");
        tf = new TwitterFactory(cb.build());
    }
    
    public static void TwitterSearch(String key) {
        Twitter twitter = tf.getInstance();
        try {
            Query query = new Query(key);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
