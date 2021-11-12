package com.example.privatecliniclb1.web;

import com.example.privatecliniclb1.ds.Folder;
import com.example.privatecliniclb1.hibernateControllers.FolderHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class FolderWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    FolderHibController folderHibController = new FolderHibController(entityManagerFactory);


    @RequestMapping(value = "/user/getFolder/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@PathVariable(name = "id") int id){
        return folderHibController.getFolderById(id).toString();
    }


    @RequestMapping(value = "/user/allFolders", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders() {
        Gson gson = new Gson();
        return gson.toJson(folderHibController.getAllFolder().toString());
    }

    @RequestMapping(value = "/user/updateFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Folder folder = folderHibController.getFolderById(id);

        folder.setFolderName(properties.getProperty("name"));
        folder.setDateCreated(LocalDate.parse((properties.getProperty("date"))));
        //folder.setDocuments(properties.getProperty("documents"));



        folderHibController.editFolder(folder);
        return "Success";
    }

    @RequestMapping(value = "/user/addFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFolder(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Folder folder = new Folder(properties.getProperty("name"));
        folderHibController.createFolder(folder);
        return "Success";
    }

    @RequestMapping(value = "/user/deleteFolder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@PathVariable(name = "id") int id) {
        folderHibController.removeFolder(id);
        return "Success";
    }
}
