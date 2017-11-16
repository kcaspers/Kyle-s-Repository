/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.robertstreetprojects;

import dao.BlogPostDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class SearchController {

    BlogPostDao blogPostDao;

    @Inject
    public SearchController(BlogPostDao blogPostDao) {
        this.blogPostDao = blogPostDao;
    }

    @RequestMapping(value = "/search/{searchTerm}", method = RequestMethod.GET)
    public String searchBlogPosts(Map<String, Object> model, @PathVariable("searchTerm") String searchTerm) {
        //we search according to a single term, this can be a tag, category or username (author)
        List<BlogPost> postsBySearch = null;
        
        postsBySearch = blogPostDao.searchPosts(searchTerm);
        postsBySearch = postsBySearch.stream()
                .filter(p -> p.getStatus().getStatusId() == 1)
                .collect(Collectors.toList());
        
        model.put("searchTerm", searchTerm);
       
        if (postsBySearch.isEmpty()) {
            model.put("noResults", "No matches found!!!");
        }
        
        for(BlogPost bp : postsBySearch){
            String content = bp.getContent();
            content = content.replace("~break~", "");
            bp.setContent(content);   
        }
        model.put("postsBySearch", postsBySearch);
        
        return "searchHome";
    }

    @RequestMapping(value = "/searchBar", method = RequestMethod.GET)
    public String searchBarBlogPosts(Map<String, Object> model, HttpServletRequest request) {
        //we search according to a single term, this can be a tag, category or username (author)
        String searchTerm = request.getParameter("srch-term");
        if(searchTerm.trim().equals("")){
            model.put("noResults", "No matches found");
            return "searchHome";
        }
        
        List<BlogPost> postsBySearch = null;
        postsBySearch = blogPostDao.searchPosts(searchTerm);
        postsBySearch = postsBySearch.stream()
                .filter(p -> p.getStatus().getStatusId() == 1)
                .collect(Collectors.toList());
        
        model.put("postsBySearch", postsBySearch);
        if (postsBySearch.isEmpty()) {
            model.put("noResults", "No matches found");
        }
        for(BlogPost bp : postsBySearch){
            String content = bp.getContent();
            content = content.replace("~break~", "");
            bp.setContent(content);   
        }
        model.put("searchTerm", searchTerm);
        return "searchHome";
    }

}
