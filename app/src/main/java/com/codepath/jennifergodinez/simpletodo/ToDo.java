package com.codepath.jennifergodinez.simpletodo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jennifergodinez on 8/15/17.
 */

class ToDo {
    public Long _id; // for cupboard
    public String name;
    public String priority ;
    public String date;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

    private Date dueDate;


    public ToDo() {
        this.name = "";
    }

    public ToDo(String n) {
        this(n, "");
    }

    public ToDo(String n, String d) {
        this(n, d, "LOW");
    }

    public ToDo(String n, String d, String p) {
        this.name = n;
        this.date = d;
        this.priority = p;
        try {
            this.dueDate = formatter.parse(d);
        } catch (ParseException pe) {
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public Long get_id() {
        return this._id;
    }
}