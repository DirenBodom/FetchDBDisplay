package com.example.fetchdbdisplay;

public class TableRow {
    private int id, listid;
    private String name;

    public TableRow(int i, int li, String n) {
        id = i;
        listid = li;
        name = n;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Table Row: " + id + ", listId: " + listid + ", name: " + name;
    }
}
