/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.robertstreetprojects;

import dao.CategoryDao;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kylecaaspers
 */
@Controller
public class CategoryController {
    
    CategoryDao categoryDao;
    
    @Inject
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }
    
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(HttpServletRequest request, Map<String, Object> model){
        String categoryName = request.getParameter("categoryName");
        Category newCategory = new Category();
        newCategory.setName(categoryName);
        newCategory = categoryDao.addCategory(newCategory);
        
        
        return "redirect:displayAddCategory";
    }
    
}
