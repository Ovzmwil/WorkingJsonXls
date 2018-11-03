package com.working.project.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonClass {
    
    @SerializedName("Example")
    @Expose
    private String example;

    public String getExample() {
	return example;
    }

    public void setExample(String example) {
	this.example = example;
    }
}
