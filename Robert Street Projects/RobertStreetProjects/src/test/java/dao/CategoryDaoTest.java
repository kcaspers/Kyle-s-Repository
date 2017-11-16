/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.BlogPost;
import model.Category;
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
public class CategoryDaoTest {

    CategoryDao categoryDao;
    BlogPostDao blogPostDao;

    public CategoryDaoTest() {
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
        categoryDao = ctx.getBean("categoryDao", CategoryDao.class);
        blogPostDao = ctx.getBean("blogPostDao", BlogPostDao.class);

        List<BlogPost> posts = blogPostDao.getAllBlogPosts();
        for (BlogPost currentPost : posts) {
            blogPostDao.deleteBlogPost(currentPost.getBlogPostId());
        }

        List<Category> categories = categoryDao.getAllCategories();
        for (Category currentCategory : categories) {
            categoryDao.deleteCategory(currentCategory.getCategoryId());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetCategory() {
        Category category = new Category();
        category.setName("Upcomming Events");

        categoryDao.addCategory(category);

        Category fromDao = categoryDao.getCategoryById(category.getCategoryId());

        assertEquals(fromDao, category);
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Upcomming Events");

        categoryDao.addCategory(category);

        assertEquals(1, categoryDao.getAllCategories().size());

        categoryDao.deleteCategory(category.getCategoryId());

        assertNull(categoryDao.getCategoryById(category.getCategoryId()));
    }

    /**
     * Test of updateCategory method, of class CategoryDao.
     */
    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Upcomming Events");

        categoryDao.addCategory(category);

        category.setName("Past events");
        categoryDao.updateCategory(category);

        assertEquals("Past events", categoryDao.getCategoryById(category.getCategoryId()).getName());
    }

    @Test
    public void testGetAllCategories() {

        Category category = new Category();
        category.setName("Upcomming Events");

        categoryDao.addCategory(category);

        Category category2 = new Category();
        category2.setName("News");

        categoryDao.addCategory(category2);

        assertEquals(2, categoryDao.getAllCategories().size());
    }

}
