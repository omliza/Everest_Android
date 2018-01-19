package com.sxm.framework.common;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.sxm.framework.dto.handler.EnvirnomentHandler;

public class TwitterUtil {
    private static TwitterUtil tweetInstance;

    private TwitterUtil() {

    }

    public static TwitterUtil getInstance() {
        if (tweetInstance == null) {
            new TwitterUtil();
        }

        return tweetInstance;
    }

    public static List getTwitterPosts(String searchKey) {
        List tweets = new ArrayList();
        Twitter twitter = new TwitterFactory().getInstance();

        twitter.setOAuthConsumer(EnvirnomentHandler.getEnvirnoment()
                .getTwitterConsumerkey(), EnvirnomentHandler.getEnvirnoment()
                .getTwitterConsumerSecretKey());

        AccessToken token = new AccessToken(EnvirnomentHandler.getEnvirnoment()
                .getTwittertoken(), EnvirnomentHandler.getEnvirnoment()
                .getTwitterSecretToken());
        twitter.setOAuthAccessToken(token);

        Query query = new Query(searchKey);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("result " + result);

        for (Status status : result.getTweets()) {
            Reporter.log("@" + status.getUser().getScreenName() + ":"
                    + status.getText());
            tweets.add(status.getText());
        }
        return tweets;
    }
}
