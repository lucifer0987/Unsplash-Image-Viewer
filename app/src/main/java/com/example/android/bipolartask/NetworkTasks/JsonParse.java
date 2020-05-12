package com.example.android.bipolartask.NetworkTasks;

import com.example.android.bipolartask.Models.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParse {

    public static List<Image> parse(String jsonString) throws JSONException {

        JSONArray root = new JSONArray(jsonString);
        List<Image> ImageList = new ArrayList<>();

        for(int i = 0; i < root.length(); i++){
            JSONObject currentImage = root.getJSONObject(i);
            String title = "", url = "";

            String titleFetched = currentImage.getString("description");
            if(titleFetched.equals("null")){
                title = "N/A";
            }else if(titleFetched.length() <= 17){
                title = titleFetched;
            }else{
                title = titleFetched.substring(0, 17);
            }


            JSONObject urls = currentImage.getJSONObject("urls");
            url = urls.getString("raw") + "&w=200&dpr=3";

            ImageList.add(new Image(title, url));
        }

        return ImageList;

    }

}
