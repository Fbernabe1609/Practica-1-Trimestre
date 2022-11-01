package org.example;

import org.example.models.DbHelper;

public class Main {
    public static void main(String[] args) {
        DbHelper db = new DbHelper();
        db.viewDocument();
    }
}