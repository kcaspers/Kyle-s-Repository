/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.BlogPost;
import model.Tag;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class TagDaoTest {
    
    TagDao tagDao;
    BlogPostDao blogPostDao;
    
    public TagDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        tagDao = ctx.getBean("tagDao", TagDao.class);
        blogPostDao = ctx.getBean("blogPostDao", BlogPostDao.class);

        List<BlogPost> posts = blogPostDao.getAllBlogPosts();
        for (BlogPost currentPost : posts) {
            blogPostDao.deleteBlogPost(currentPost.getBlogPostId());
        }

        List<Tag> tags = tagDao.getAllTags();
        for (Tag currentTag : tags) {
            tagDao.deleteTag(currentTag.getTagId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTag method, of class TagDao.
     */
    @Test
    public void testAddGetTag() {
        
        Tag tag = new Tag();
        tag.setTagName("art");
        
        tagDao.addTag(tag);
        
        Tag fromDao = tagDao.getTagById(tag.getTagId());
        
        assertEquals(fromDao, tag);
    }

    @Test
    public void testDeleteTag() {
        
        Tag tag = new Tag();
        tag.setTagName("art");
        
        tagDao.addTag(tag);
        
        tagDao.deleteTag(tag.getTagId());
        assertNull(tagDao.getTagById(tag.getTagId()));
    }

    @Test
    public void testUpdateTag() {
        
        Tag tag = new Tag();
        tag.setTagName("art");
        
        tagDao.addTag(tag);
        
        tag.setTagName("contemporary");
        tagDao.updateTag(tag);
        
        assertEquals("contemporary", tagDao.getTagById(tag.getTagId()).getTagName());
    }

    /**
     * Test of getAllTags method, of class TagDao.
     */
    @Test
    public void testGetAllTags() {
        
        Tag tag = new Tag();
        tag.setTagName("art");
        
        tagDao.addTag(tag);
        
        Tag tag2 = new Tag();
        tag2.setTagName("more");
        
        tagDao.addTag(tag2);
        
        assertEquals(2, tagDao.getAllTags().size());
    }

}
