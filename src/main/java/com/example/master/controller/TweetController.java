package com.example.master.controller;

import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TweetController {

    private Twitter twitter = new TwitterTemplate("jJ1KSqAu0i5Ke27E4WSE6pXiT","sAQkzKrRji7pmcYAdvmCCR7L3IstOVzaffllTEcLQ1gWqTIRiQ");

    @RequestMapping("/")
    public String home(){
        return "searchPage";
    }

    @RequestMapping("/result")
    public String hello(@RequestParam(defaultValue = "masterSpringMVC4") String search, Model model){
        SearchResults results = twitter.searchOperations().search(search);
        List<Tweet> tweets = results.getTweets();
        model.addAttribute("search", search);
        model.addAttribute("tweets", tweets);
        return "resultPage";
    }

    @RequestMapping(value = "postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String search = request.getParameter("search");
       redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }

}
