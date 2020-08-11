package com.example.testquickjava.models;

import java.util.ArrayList;

public class PostRespuesta {
    public ArrayList<Post> getResult() {
        return result;
    }

    public void setResult(ArrayList<Post> result) {
        this.result = result;
    }

    private ArrayList<Post> result;
}
