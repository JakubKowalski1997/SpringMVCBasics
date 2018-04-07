package com.example.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.directory.SearchResult;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TweetController {

    private Twitter twitter = new TwitterTemplate("jJ1KSqAu0i5Ke27E4WSE6pXiT","sAQkzKrRji7pmcYAdvmCCR7L3IstOVzaffllTEcLQ1gWqTIRiQ");

    @RequestMapping("/")
    public String hello(@RequestParam(defaultValue = "Tajniki SpringMVC4") String search, Model model){
        SearchResults results = twitter.searchOperations().search(search);
        List<String> tweets = results.getTweets()
                .stream()
                .map(Tweet::getText)
                .collect(Collectors.toList());
        model.addAttribute("tweets", tweets);
        return "resultPage";
    }

}
