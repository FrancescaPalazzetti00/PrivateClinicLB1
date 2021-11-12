package com.example.privatecliniclb1.web;

import com.example.privatecliniclb1.ds.Archive;
import com.example.privatecliniclb1.hibernateControllers.ArchiveHibController;
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
public class ArchiveWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    ArchiveHibController archiveHibController = new ArchiveHibController(entityManagerFactory);


    @RequestMapping(value = "/user/getArchive/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getArchive(@PathVariable(name = "id") int id){
        return archiveHibController.getArchiveById(id).toString();
    }


    @RequestMapping(value = "/user/allArchives", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders() {
        Gson gson = new Gson();
        return gson.toJson(archiveHibController.getAllArchive().toString());
    }

    @RequestMapping(value = "/user/updateArchive/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateArchive(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Archive archive = archiveHibController.getArchiveById(id);

        archive.setArchiveName(properties.getProperty("archiveName"));
        archive.setArchiveDescription(properties.getProperty("archiveDesc"));
        archive.setDateCreated(LocalDate.parse((properties.getProperty("dateCreated"))));
        archive.setDateModified(LocalDate.parse((properties.getProperty("dateModified"))));

        archiveHibController.editArchive(archive);
        return "Success";
    }

    @RequestMapping(value = "/user/addArchive", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewArchive(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Archive archive = new Archive(properties.getProperty("archiveName"),properties.getProperty("archiveDesc"));
        archiveHibController.createArchive(archive);
        return "Success";
    }

    @RequestMapping(value = "/user/deleteArchive/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateArchive(@PathVariable(name = "id") int id) {
        archiveHibController.removeArchive(id);
        return "Success";
    }
}
