package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Cosmetics {
    private String CosmeticId;
    private String CosmeticName;
    private String CosmeticAddress;
    public Cosmetics(){
        //this constructor is required
    }
    public Cosmetics(String CosmeticId, String CosmeticName, String CosmeticAddress) {
        this.CosmeticId = CosmeticId;
        this.CosmeticName = CosmeticName;
        this.CosmeticAddress = CosmeticAddress;
    }

    public String getCosmeticId() {
        return CosmeticId;
    }

    public String getCosmeticName() {
        return CosmeticName;
    }

    public String getCosmeticAddress() {
        return CosmeticAddress;
    }
}