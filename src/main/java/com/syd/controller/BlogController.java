package com.syd.controller;

import com.syd.model.BlogEntity;
import com.syd.model.UserEntity;
import com.syd.repository.BlogRepository;
import com.syd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ykue on 2017/6/29.
 */
@Controller
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "admin/blog", method = RequestMethod.GET)
    public String showBlog(ModelMap modelMap){
        List<BlogEntity> blogEntityList = blogRepository.findAll();
        modelMap.addAttribute("blogList",blogEntityList);
        return "/admin/showBlog";
    }
    @RequestMapping(value = "admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap){
        List<UserEntity> userEntityrList = userRepository.findAll();
        modelMap.addAttribute("userList",userEntityrList);
        return "/admin/addBlog";
    }

    @RequestMapping(value = "admin/blogs/addP",method = RequestMethod.POST)
    public String addBlogB(@ModelAttribute("blog") BlogEntity blogEntity){
        System.out.println(blogEntity.getTitle());
        System.out.println(blogEntity.getBlogByUser());
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blog";
    }
    @RequestMapping(value = "admin/blogs/show/{id}",method = RequestMethod.GET)
    public String blogDetail(@PathVariable("id") int id,ModelMap modelMap){
        BlogEntity blog = blogRepository.findOne(id);
        System.out.println(blog);
        modelMap.addAttribute("blog",blog);
        return "/admin/blogDetail";
    }
    @RequestMapping(value = "admin/blogs/update/{id}")
    public String updateBlog(@PathVariable("id") int id,ModelMap modelMap){
        BlogEntity blogEntity = blogRepository.findOne(id);
        List<UserEntity> userEntity = userRepository.findAll();
        modelMap.addAttribute("blog",blogEntity);
        modelMap.addAttribute("userList",userEntity);
        return "/admin/updateBlog";
    }

    @RequestMapping(value = "admin/blogs/updateP",method = RequestMethod.POST)
    public String updateBlogP(@ModelAttribute("blogP") BlogEntity blogEntity){
        blogRepository.updateBlog(blogEntity.getTitle(), blogEntity.getBlogByUser().getId(), blogEntity.getContent(),  blogEntity.getPubDate(), blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blog";
    }
    @RequestMapping(value ="admin/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id")int id){
        blogRepository.delete(id);
        blogRepository.flush();
        return "redirect:/admin/blog";
    }
}
