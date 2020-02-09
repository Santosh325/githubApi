package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    GithubAdapter githubAdapter;
    List<Githubmodel> mGithubmodelList;
    RequestQueue queue;
    String url = "https://api.github.com/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mGithubmodelList = new ArrayList<>();
        loadDataFromUrl();

    }
    private void loadDataFromUrl() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        setDataInRecyclerview(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
    private void setDataInRecyclerview(JSONArray response) {
        JSONObject jsonObject = null;
        for(int i = 0; i < response.length(); i++) {
            Githubmodel user = new Githubmodel();
            try {
                jsonObject = response.getJSONObject(i);
                user.setLogin(jsonObject.getString("login"));
                user.setAvatarUrl(jsonObject.getString("avatar_url"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            mGithubmodelList.add(user);

        }
        githubAdapter = new GithubAdapter(this,mGithubmodelList);
        mRecyclerView.setAdapter(githubAdapter);
    }
}
