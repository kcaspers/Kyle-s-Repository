/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.robertstreetprojects;

import dao.BlogPostDao;
import dao.CategoryDao;
import dao.StatusDao;
import dao.TagDao;
import dao.UserDao;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.BlogPost;
import model.Category;
import model.Status;
import model.Tag;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class PostController {

    BlogPostDao blogPostDao;
    UserDao userDao;
    CategoryDao categoryDao;
    TagDao tagDao;
    StatusDao statusDao;

    @Inject
    public PostController(BlogPostDao blogPostDao, UserDao userDao, 
            CategoryDao categoryDao, TagDao tagDao, StatusDao statusDao) {
        this.blogPostDao = blogPostDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
        this.statusDao = statusDao;
    }

    //Once we have a dao we will instantiate it here
    //and inject it here
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(HttpServletRequest request, @RequestParam("newPost") String content, Principal principal) {

        BlogPost newPost = new BlogPost();
        newPost.setTitle(request.getParameter("newPostTitle"));
        newPost.setContent(content);

        Status draft = new Status();
        draft.setStatusId(4);
        newPost.setStatus(draft);

        //need to add tags.
        // List<String> databaseTagName = new ArrayList<>();
        ArrayList<Tag> postTags = new ArrayList<>();
        List<Tag> databaseTags = tagDao.getAllTags();
        List<String> tagList = new ArrayList<>();

        String tag = request.getParameter("tags");

        String[] allTags = tag.split(" ");

        String[] newTags = new String[allTags.length];

        for (int i = 0; i < allTags.length; i++) {
            newTags[i] = allTags[i];
            if (!newTags[i].equals("")) {
                tagList.add(newTags[i]);
            }
        }

        //add the new ones to the DB
        for (String newTagName : tagList) {
            boolean isPresent = false;
            for (Tag t : databaseTags) {
                if (t.getTagName().equals(newTagName)) {
                    isPresent = true;
                    postTags.add(t);
                }
            }
            if (!isPresent) {
                Tag newTag = new Tag();
                newTag.setTagName(newTagName);
                postTags.add(tagDao.addTag(newTag));
            }
        }

        //finally, add the tags to out new post
        newPost.setTags(postTags);

//        for (Tag t : databaseTags) {
//            databaseTagName.add(t.getTagName());
//        }
//        if (!databaseTagName.equals(tagList)) {
//            for (String newTagName : tagList) {
//                Tag newTag = new Tag();
//                newTag.setTagName(newTagName);
//                tagDao.addTag(newTag);
//            }
//            //databaseTags.add(newTag);   
//        }
        int categoryId = Integer.parseInt(request.getParameter("newPostCategory"));
        Category category = categoryDao.getCategoryById(categoryId);
        newPost.setCategory(category);

        //get photo path
        String imagePath = request.getParameter("photo");
        newPost.setImagePath(imagePath);

        //String userName = (request.getParameter("username"));
        //TEMPORARY SOLUTION!
        User user = userDao.getUserByUsername(principal.getName());
        newPost.setUser(user);

        blogPostDao.addNewBlogPost(newPost);

        return "redirect:/";  //I am hoping this redirects us to our updated home page
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public String editPost(HttpServletRequest request, @RequestParam("newPost") String content, Principal principal) {
        //System.out.println("welcome to the edit endpoint");
        int postId = Integer.parseInt(request.getParameter("postId"));
        BlogPost postToEdit = blogPostDao.getBlogPostById(postId);
        if(!postToEdit.getUser().getUserName().equals(principal.getName())){
            return "redirect:/";
        } else {
            
            postToEdit.setTitle(request.getParameter("editPostTitle"));
        postToEdit.setContent(content);

        ArrayList<Tag> postTags = new ArrayList<>();
        List<Tag> databaseTags = tagDao.getAllTags();
        List<String> tagList = new ArrayList<>();

        String tag = request.getParameter("tags");
        String[] allTags = tag.split(" ");
        String[] newTags = new String[allTags.length];
        for (int i = 0; i < allTags.length; i++) {
            newTags[i] = allTags[i];
            if (!newTags[i].equals("")) {
                tagList.add(newTags[i]);
            }
        }
        //add the new ones to the DB
        for (String newTagName : tagList) {
            boolean isPresent = false;
            for (Tag t : databaseTags) {
                if (t.getTagName().equals(newTagName)) {
                    isPresent = true;
                    postTags.add(t);
                }
            }
            if (!isPresent) {
                Tag newTag = new Tag();
                newTag.setTagName(newTagName);
                postTags.add(tagDao.addTag(newTag));
            }
        }
        //finally, add the tags to out new post
        postToEdit.setTags(postTags);

        int categoryId = Integer.parseInt(request.getParameter("newPostCategory"));
        if (categoryId > 0) {
            Category category = categoryDao.getCategoryById(categoryId);
            postToEdit.setCategory(category);
        }

        String imagePath = request.getParameter("photo");
        if (!imagePath.equals("")) {
            postToEdit.setImagePath(imagePath);
        }

        blogPostDao.updateBlogPost(postToEdit);

        return "redirect:/";
        }
    }

    @RequestMapping(value = "/publishPost", method = RequestMethod.GET)
    public String publishPost(HttpServletRequest request, Map<String, Object> model) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        BlogPost postToPublish = blogPostDao.getBlogPostById(postId);

        Status status = new Status();
        status.setStatusId(1);
        postToPublish.setStatus(status);

        //I should set the publish date to be right now
        postToPublish.setPublishDate(LocalDate.now());
        String requestDate = request.getParameter("expDate");
        LocalDate expDate = LocalDate.parse(requestDate);
        postToPublish.setExpDate(expDate);

        blogPostDao.updateBlogPost(postToPublish);

        return "redirect:/";
    }

    @RequestMapping(value = "/submitDraft", method = RequestMethod.GET)
    public String submitDraft(HttpServletRequest request, Map<String, Object> model) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        BlogPost draftPost = blogPostDao.getBlogPostById(postId);
        
        Status pending = statusDao.getStatusById(2);
        draftPost.setStatus(pending);
        
        blogPostDao.updateBlogPost(draftPost);
        return "redirect:/";
    }

    @RequestMapping(value = "/rejectPost", method = RequestMethod.GET)
    public String rejectPost(HttpServletRequest request, Map<String, Object> model
    ) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        BlogPost postToReject = blogPostDao.getBlogPostById(postId);
        Status status = new Status();
        status.setStatusId(3);
        postToReject.setStatus(status);

        blogPostDao.updateBlogPost(postToReject);
        List<BlogPost> pendingPosts = blogPostDao.getAllBlogPosts();

        model.put("pendingPosts", pendingPosts);
        return "redirect:/displayPending";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public String deletePost(HttpServletRequest request, Map<String, Object> model) {
        String postIdParameter = request.getParameter("postId");
        int postId = Integer.parseInt(postIdParameter);
        BlogPost postToReject = blogPostDao.getBlogPostById(postId);
        Status status = new Status();
        status.setStatusId(3);
        postToReject.setStatus(status);

        blogPostDao.updateBlogPost(postToReject);

        return "redirect:/displayDrafts";
    }

}
