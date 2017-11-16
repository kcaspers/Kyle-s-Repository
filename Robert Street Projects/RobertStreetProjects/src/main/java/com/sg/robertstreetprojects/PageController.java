/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.robertstreetprojects;

import dao.StaticPageDao;
import dao.UserDao;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.StaticPage;
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
public class PageController {

    StaticPageDao staticPageDao;
    UserDao userDao;

    @Inject
    public PageController(StaticPageDao staticPageDao, UserDao userDao) {
        this.staticPageDao = staticPageDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.POST)
    public String addPage(HttpServletRequest request, @RequestParam("newPage") String content) {
        //how do we know which user is posting? For now just have a dummy userID
        StaticPage staticPage = new StaticPage();
        staticPage.setName(request.getParameter("newPageTitle"));
        staticPage.setContent(content);

        //TEMPORARY SOLUTION!
        User user = userDao.getUserById(1);
        staticPage.setUser(user);
        staticPageDao.addStaticPage(staticPage);

        return "redirect:/";
    }

    //navigate to static page and supply page with the staticPageObject
    @RequestMapping(value = "displayStaticPage/{pageID}", method = RequestMethod.GET)
    public String viewStaticPage(@PathVariable("pageID") int id, Map<String, Object> model) {
        StaticPage pageToDisplay = staticPageDao.getStaticPageById(id);
        model.put("pageToDisplay", pageToDisplay);

        return "staticPageTemplate";
    }

    @RequestMapping(value = "/staticPages", method = RequestMethod.GET)
    @ResponseBody
    public List<StaticPage> getAllStaticPages() {
        return staticPageDao.getAllStaticPages();
    }

    @RequestMapping(value = "/displayStaticPage/deletePage", method = RequestMethod.GET)
    public String deletePage(HttpServletRequest request) {
        int pageId = Integer.parseInt(request.getParameter("staticPageId"));
        staticPageDao.deleteStaticPage(pageId);
        return "redirect:/";
    }
}
