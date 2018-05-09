package com.example.pedro.testvocces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.pedro.testvocces.db.NucleoBD;
import com.example.pedro.testvocces.db.UsersContract;
import com.example.pedro.testvocces.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    GsonBuilder gBuilder = new GsonBuilder();
                    gBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
                    String results = response.getString("results");
                    Type listType = new TypeToken<List<User>>() {}.getType();
                    List<User> users = gBuilder.create().fromJson(results, listType);
                    /*Vacío la lista para que no se mantengan demasiados usuarios
                     * ya que al ejeecutar varias veces la listaa se haría relativamente extensa
                     */
                    UsersContract.clear(getApplicationContext());
                    UsersContract.insertUsers(getApplicationContext(), users);
                    ((TextView)findViewById(R.id.tvNumUsers)).setText(users.size() + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        new RequestHelper(this).requestUsers(listener, errorListener);


        List<User> users = UsersContract.getAll(this);
        updateRvUsers(users);
    }

    private void updateRvUsers(List<User> users){
        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        UsersAdapter adapter = new UsersAdapter(this, users);
        rvUsers.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
