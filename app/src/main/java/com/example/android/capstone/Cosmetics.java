package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Cosmetics {
    private String CosmeticId;
    private String CosmeticName;
    private String CosmeticAddress;
    private List<Ingredients> CosmeticIngredients;
    public Cosmetics(){
        //this constructor is required
    }
    public Cosmetics(String CosmeticId, String CosmeticName, String CosmeticAddress, List<Ingredients> CosmeticIngredients) {
        this.CosmeticId = CosmeticId;
        this.CosmeticName = CosmeticName;
        this.CosmeticAddress = CosmeticAddress;
        this.CosmeticIngredients = CosmeticIngredients;
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
    public List<Ingredients> getCosmeticIngredients() {
        return CosmeticIngredients;
    }
}