package com.example.privatecliniclb1.ds;

import java.io.Serializable;
import java.util.ArrayList;

public class ArchiveSys implements Serializable {

    private int id;
    private ArrayList<User> allUsers;
    private ArrayList<Archive> allArchives;

    public ArchiveSys(int id, ArrayList<User> allUsers, ArrayList<Archive> allArchives) {
        this.id = id;
        this.allUsers = allUsers;
        this.allArchives = allArchives;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<Archive> getAllArchives() {
        return allArchives;
    }

    public void setAllArchives(ArrayList<Archive> allArchives) {
        this.allArchives = allArchives;
    }
}
