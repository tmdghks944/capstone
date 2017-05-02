package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Cosmetic {

    private String cosmeticId;
    private String cosmeticName;
    private String cosmeticAddress;

    public Cosmetic(){
        //this constructor is required
    }
    public Cosmetic(String CosmeticId, String CosmeticName, String CosmeticAddress) {
        this.cosmeticId = CosmeticId;
        this.cosmeticName = CosmeticName;
        this.cosmeticAddress = CosmeticAddress;
    }

    public String getCosmeticId() { return cosmeticId; }

    public String getCosmeticName() { return cosmeticName; }

    public String getCosmeticAddress() {
        return cosmeticAddress;
    }
}
