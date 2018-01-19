package com.sxm.framework.common;

import java.util.ArrayList;
import java.util.List;

import com.sxm.framework.dto.FaceBookPost;
import com.sxm.framework.dto.handler.EnvirnomentHandler;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class FaceBookUtil {
    private static FaceBookUtil fbInstance;

    private FaceBookUtil() {

    }

    public static FaceBookUtil getInstance() {
        if (fbInstance == null) {
            new FaceBookUtil();
        }

        return fbInstance;
    }

    public static List getFbPosts() throws FacebookException {
        Facebook facebook = new FacebookFactory().getInstance();
        String accessTokenString = EnvirnomentHandler.getInstance()
                .getEnvirnoment().getFboauthToken();
         facebook.setOAuthAppId("", "");
        /* String accessTokenString =
         "CAACEdEose0cBAFYs8kE26UioNkftBoUo1wdZCoXulZCQnvvVVw0HFA2GnPrl0PhIJpqiNsuD73BzCkmKajeAkZB4XbZAmdHQsZCGhzFysGs9p4W1HZAxtEeUhFST18UWg8e1B3RMvE2i9WIHGHBJ2Rbk4DiTKsGNSdnkhGxCt7QVGWPMZCduWas7AxsZB1BD8oujJR6cqOlPjAZDZD";*/
        AccessToken at = new AccessToken(accessTokenString);
        facebook.setOAuthAccessToken(at);
        List posts = new ArrayList();
        ResponseList<Post> feeds = facebook.getPosts();
        System.out.println(feeds.size());
        FaceBookPost fbpost = null;
        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            fbpost = new FaceBookPost();
            fbpost.setDescription(post.getDescription());
            fbpost.setCaption(post.getCaption());
            fbpost.setName(post.getName());
            posts.add(fbpost);
        }
        return posts;
    }
    
    public static void main(String args[]) throws FacebookException {        
        FaceBookUtil.getInstance().getFbPosts() ;
    }
}
