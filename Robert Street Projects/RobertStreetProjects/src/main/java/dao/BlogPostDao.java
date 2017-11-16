/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import model.BlogPost;
import model.Status;
import model.Tag;

/**
 *
 * @author kylecaaspers
 */
public interface BlogPostDao {
    
    public void addBlogPost(BlogPost blogPost);
    
    public void addNewBlogPost(BlogPost blogPost);
    
    public void deleteBlogPost(int blogPostID);
    
    public void updateBlogPost(BlogPost blogPost);
    
    public BlogPost getBlogPostById(int blogPostID);
    
    public List<BlogPost> getAllBlogPosts();
    
    public List<BlogPost> getBlogPostsByUser(int userID);
    
    public List<BlogPost> getBlogPostByStatus(int statusId);

    //make a search method like searchContacts
    public List<BlogPost> searchPosts(String searchTerm);
    
    //any other blog methods
}
