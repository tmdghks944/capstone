package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cosmetic {

    private String CosmeticId;
    private String CosmeticName;
    private String CosmeticAddress;

    public Cosmetic(){
        //this constructor is required
    }
    public Cosmetic(String CosmeticId, String CosmeticName, String CosmeticAddress) {
        this.CosmeticId = CosmeticId;
        this.CosmeticName = CosmeticName;
        this.CosmeticAddress = CosmeticAddress;
    }

    public String getCosmeticId() {
        return CosmeticId;
    }

    public String getCosmeticName() { return CosmeticName; }

    public String getCosmeticAddress() {
        return CosmeticAddress;
    }
}
