package com.andrewfisher.discountapp.service;

import android.content.Context;
import android.util.Log;

import com.andrewfisher.discountapp.model.DiscountItem;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by andrewfisher on 1/12/17.
 */

public class DiscountService {
    private Context mContext;

    //Retrieves the Json file from the assets folder, and sets it to a string
    public String getJSONFile(){
        String jsonFileString;
        try{
            InputStream asset = mContext.getAssets().open("discounts.json");
            int size = asset.available();
            byte[] buffer = new byte[size];
            asset.read(buffer);
            asset.close();
            jsonFileString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Log.d("test",jsonFileString);
        return jsonFileString;
    }


    public ArrayList<DiscountItem> allDiscounts(){
        String jsonString = getJSONFile();






        try{
            JSONObject json = new JSONObject(jsonString);
        }
    }



}
