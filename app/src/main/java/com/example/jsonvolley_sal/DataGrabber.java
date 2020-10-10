package com.example.jsonvolley_sal;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jsonvolley_sal.BestCharacters;
import com.example.jsonvolley_sal.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGrabber {
    private RequestQueue mQueue;

    public static Map<String, BestCharacters> ITEM_MAP = null;

    public static List<BestCharacters> Chrac = null;

    public void getThatDataFromThatURL(Context context) {
        if (Chrac != null) {
            return;
        }

        mQueue = Volley.newRequestQueue(context);
        parseJason(context);

        Chrac = new ArrayList<>();
        ITEM_MAP = new HashMap<>();
    }

    private void parseJason(final Context context) {
        // Must read the JSON file from the strings.xml
        String url = context.getString(R.string.URL);

        final Gson gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("bestCharacters");

                    // Checks to make sure the list isn't empty
                    if (jsonArray.length() > 0) {
                        List<BestCharacters> charac = Arrays.asList(gson.fromJson(jsonArray.toString(), BestCharacters[].class));

                        for (BestCharacters bestCharacters : charac) {
                            addObjectToList(bestCharacters);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    // Method for adding object to list
    private void addObjectToList(BestCharacters CurrentCharac) {
        Chrac.add(CurrentCharac);
        ITEM_MAP.put(CurrentCharac.character, CurrentCharac);
    }

}