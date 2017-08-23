package com.codepath.jennifergodinez.simpletodo;

/**
 * Created by jennifergodinez on 8/15/17.
 */

class ToDo {
    public Long _id; // for cupboard
    public String name;
    public String priority ;
    public String date;

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
    }

}