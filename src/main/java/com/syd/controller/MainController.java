package com.syd.controller;

import com.syd.model.UserEntity;
import com.syd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Ykue on 2017/6/29.
 */
@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap){
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList",userList);
        System.out.println(userList);
        return "admin/users";
    }
    @RequestMapping(value = "/admin/users/add",method = RequestMethod.GET)
    public String addUser(){
        return "admin/addUser";
    }
    @RequestMapping(value = "/admin/users/addP",method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserEntity userEntity){
        userRepository.saveAndFlush(userEntity);
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/admin/users/show/{id}",method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap){
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/showUser";
    }
    @RequestMapping(value = "/admin/users/update/{id}",method = RequestMethod.GET)
    public String updateUser(@PathVariable("id")Integer userId,ModelMap modelMap){
        UserEntity userEntity = userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/updateUser";
    }
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserP(@ModelAttribute("userP") UserEntity userEntity){
        userRepository.updateUser(userEntity.getNickname(),userEntity.getUsername(),userEntity.getPassword(),userEntity.getId());
        userRepository.flush();
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/admin/users/delete/{id}")
    public String delete(@PathVariable("id") Integer userId){
        userRepository.delete(userId);
        return "redirect:/admin/users";
    }


}
