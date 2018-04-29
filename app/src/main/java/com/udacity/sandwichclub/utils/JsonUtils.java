package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Log.i(TAG, "GGGGGG::  parseSandwichJson: [" + json + "]");
        //Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients)
        String mainName;
        List<String> alsoKnownAs;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients;

        if (json == null) return null; //if data string is empty, return null
        try {
            JSONObject sandwichJson = new JSONObject(json);
            mainName = (sandwichJson.getJSONObject("name").getString("mainName"));
            alsoKnownAs = jsonArrayToStringArray(sandwichJson.getJSONObject("name").getJSONArray("alsoKnownAs"));
            placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            description = sandwichJson.getString("description");
            image =  sandwichJson.getString("image");
            ingredients = jsonArrayToStringArray(sandwichJson.getJSONArray("ingredients"));
            Log.i(TAG, "2222222@: parseSandwichJson: [" + json + "]");
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }



    }
    private static List<String> jsonArrayToStringArray(JSONArray jarray) {
       List<String> returnArray = new ArrayList< >();
        if (jarray == null) return returnArray;
        int len = jarray.length();

        for (int i=0; i<len; i++){
            try {
                returnArray.add( jarray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return returnArray;

    }
}
