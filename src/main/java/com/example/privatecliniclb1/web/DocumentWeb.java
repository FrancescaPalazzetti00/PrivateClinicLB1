package com.example.privatecliniclb1.web;

import com.example.privatecliniclb1.ds.Document;
import com.example.privatecliniclb1.hibernateControllers.DocumentHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Properties;

@Controller
public class DocumentWeb {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");
    DocumentHibController documentHibController = new DocumentHibController(entityManagerFactory);


    @RequestMapping(value = "/user/getDocument/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getDocument(@PathVariable(name = "id") int id){
        return documentHibController.getDocumentById(id).toString();
    }


    @RequestMapping(value = "/user/allDocuments", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllDocuments() {
        Gson gson = new Gson();
        return gson.toJson(documentHibController.getAllDocument().toString());
    }

    @RequestMapping(value = "/user/updateDocument/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Document document = documentHibController.getDocumentById(id);

        document.setDocName(properties.getProperty("docName"));
        document.setDocDesc(properties.getProperty("docDesc"));
        document.setDateCreated(LocalDate.parse((properties.getProperty("dateCreated"))));
        document.setDateCompleted(LocalDate.parse((properties.getProperty("dateCompleted"))));



        documentHibController.editDocument(document);
        return "Success";
    }

    @RequestMapping(value = "/user/addDocument", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewDocument(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Document document = new Document(properties.getProperty("docName"), properties.getProperty("docDesc"));
        documentHibController.createDocument(document);
        return "Success";
    }

    @RequestMapping(value = "/user/deleteDocument/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@PathVariable(name = "id") int id) {
        documentHibController.removeDocument(id);
        return "Success";
    }
}
