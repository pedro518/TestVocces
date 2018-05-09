package com.example.pedro.testvocces;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pedro.testvocces.model.User;

import org.json.JSONObject;

import java.util.List;

public class RequestHelper {

    private static final String TAG = "RequestHelper.java";

    public static String URL = "https://randomuser.me/api/?results=15&exc=location,login,id,email,registered,cell,nat";

    private RequestQueue requestQueue;

    public RequestHelper(Context context) {
        // Crear nueva cola de peticiones
        requestQueue = Volley.newRequestQueue(context);
    }

    public void requestUsers(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        // Nueva petición JSONObject
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                listener,
                errorListener
        );

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }
}
