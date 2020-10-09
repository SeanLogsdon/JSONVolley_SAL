package com.example.jsonvolley_sal;

import com.google.gson.annotations.SerializedName;


public class BestCharacters {
    @SerializedName("name")
    public String character;

    @SerializedName("GSP")
    public int gsp;

    @SerializedName("bestPlace")
    public String bestplace;

    public BestCharacters(String name, int GSP, String achievement) {
        character = name;
        gsp = GSP;
        bestplace = achievement;
    }
}