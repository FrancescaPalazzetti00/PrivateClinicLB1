package com.example.privatecliniclb1.web;

import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Controller
public class UserWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);


    @RequestMapping(value = "/user/getUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable(name = "id") int id){
        return userHibController.getUserById(id).toString();
    }

}
