package com.example.privatecliniclb1.web;

import com.example.privatecliniclb1.ds.Patient;
import com.example.privatecliniclb1.ds.Secretary;
import com.example.privatecliniclb1.ds.User;
import com.example.privatecliniclb1.hibernateControllers.UserHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class UserWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    UserHibController userHibController = new UserHibController(entityManagerFactory);


    @RequestMapping(value = "/user/getUser/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@PathVariable(name = "id") int id){
        return userHibController.getUserById(id).toString();
    }


    @RequestMapping(value = "/user/allUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers() {
        Gson gson = new Gson();
        return gson.toJson(userHibController.getAllUsers().toString());
    }


    @RequestMapping(value = "/user/updatePatient/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePatient(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Patient patient = (Patient) userHibController.getUserById(id);

        patient.setLogin(properties.getProperty("login"));
        patient.setPsw(properties.getProperty("psw"));
        patient.setName(properties.getProperty("name"));
        patient.setSurname(properties.getProperty("surname"));
        patient.setTaxCode(properties.getProperty("taxCode"));
        patient.setEmail(properties.getProperty("email"));
        patient.setAddress(properties.getProperty("address"));


        userHibController.editUser(patient);
        return "Success";
    }

    @RequestMapping(value = "/user/addPatient", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewPatient(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Patient patient = new Patient(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("taxCode"), properties.getProperty("email"), properties.getProperty("address"));
        userHibController.createUser(patient);
        return "Success";
    }

    @RequestMapping(value = "/user/updateSecretary/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateSecretary(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Secretary secretary = (Secretary) userHibController.getUserById(id);

        secretary.setLogin(properties.getProperty("login"));
        secretary.setPsw(properties.getProperty("psw"));
        secretary.setSecretaryName(properties.getProperty("name"));
        secretary.setSecretarySurname(properties.getProperty("surname"));
        secretary.setSecretaryPhoneNum(properties.getProperty("phoneNum"));
        secretary.setSecretaryAddress(properties.getProperty("address"));


        userHibController.editUser(secretary);
        return "Success";
    }

    @RequestMapping(value = "/user/addSecretary", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewSecretary(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Secretary secretary = new Secretary(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("phoneNum"), properties.getProperty("address"));
        userHibController.createUser(secretary);
        return "Success";
    }

    @RequestMapping(value = "/user/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateUser(@PathVariable(name = "id") int id) {
        userHibController.removeUser(id);
        return "Success";
    }

}
