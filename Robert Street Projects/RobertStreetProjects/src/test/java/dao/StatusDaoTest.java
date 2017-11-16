/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.BlogPost;
import model.Status;
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
public class StatusDaoTest {

    StatusDao statusDao;
    BlogPostDao blogPostDao;

    public StatusDaoTest() {
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
        statusDao = ctx.getBean("statusDao", StatusDao.class);
        blogPostDao = ctx.getBean("blogPostDao", BlogPostDao.class);

        List<BlogPost> posts = blogPostDao.getAllBlogPosts();
        for (BlogPost currentPost : posts) {
            blogPostDao.deleteBlogPost(currentPost.getBlogPostId());
        }

        List<Status> statuses = statusDao.getAllStatuses();
        for (Status currentStatus : statuses) {
            statusDao.deleteStatus(currentStatus.getStatusId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addStatus method, of class StatusDao.
     */
    @Test
    public void testAddGetStatus() {
        Status status = new Status();
        status.setName("Approved");

        statusDao.addStatus(status);

        Status fromDao = statusDao.getStatusById(status.getStatusId());

        assertEquals(fromDao, status);
    }

    /**
     * Test of deleteStatus method, of class StatusDao.
     */
    @Test
    public void testDeleteStatus() {

        Status status = new Status();
        status.setName("Approved");

        statusDao.addStatus(status);

        statusDao.deleteStatus(status.getStatusId());
        assertNull(statusDao.getStatusById(status.getStatusId()));
    }

    /**
     * Test of updateStatus method, of class StatusDao.
     */
    @Test
    public void testUpdateStatus() {

        Status status = new Status();
        status.setName("Approved");

        statusDao.addStatus(status);

        status.setName("Rejected");
        statusDao.updateStatus(status);

        assertEquals("Rejected", statusDao.getStatusById(status.getStatusId()).getName());
    }

    @Test
    public void testGetAllStatuses() {

        Status status = new Status();
        status.setName("Approved");

        statusDao.addStatus(status);

        Status status2 = new Status();
        status2.setName("Pending");

        statusDao.addStatus(status2);

        Status status3 = new Status();
        status3.setName("Rejected");

        statusDao.addStatus(status3);

        assertEquals(3, statusDao.getAllStatuses().size());
    }
}
