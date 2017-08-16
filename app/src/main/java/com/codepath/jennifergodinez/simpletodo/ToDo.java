package com.codepath.jennifergodinez.simpletodo;

import java.util.Date;

/**
 * Created by jennifergodinez on 8/15/17.
 */

public class ToDo {
    public Long _id; // for cupboard
    public String name; // bunny name
    public Integer priority ;
    public Date dueDate;


    public ToDo() {
        this.name = "";
    }

    public ToDo(String n) {
        this.name = n;
    }

    public ToDo(String n, Date d) {
        this.name = n;
        this.dueDate = d;
    }

    public ToDo(String n, Date d, Integer p) {
        this.name = n;
        this.dueDate = d;
        this.priority = p;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long get_id() {
        return this._id;
    }
}