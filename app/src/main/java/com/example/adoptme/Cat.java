package com.example.adoptme;

public class Cat {
    private String mImageUrl;   //every cat has an image, age and name
    private String mName;
    private int mAge;


    public Cat(String imageUrl, String name, int age) {   //passing in values to cat item
        mImageUrl = imageUrl;
        mName = name;
        mAge = age;
    }
    //getting values out of the objects

    public String getImageUrl(){
        return mImageUrl;
    }


    public String getName(){
        return mName;
    }


    public int getAgeCount(){
        return mAge;
    }


}
