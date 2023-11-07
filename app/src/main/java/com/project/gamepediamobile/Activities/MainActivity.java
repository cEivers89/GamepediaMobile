package com.project.gamepediamobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.project.gamepediamobile.Constants;
import com.project.gamepediamobile.Game;
import com.project.gamepediamobile.GameListAdapter;
import com.project.gamepediamobile.GameResponse;
import com.project.gamepediamobile.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewGames, adapterUpcomingGames;
    private RecyclerView recyclerViewNewGames, recyclerViewUpcomingGames;
    private RequestQueue requestQueue;
    private ProgressBar progressBar, progressBar2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fetchRecentGames();
        fetchUpcomingGames();
    }

    private void fetchRecentGames() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.API_BASE_URL + "?key=" + Constants.API_KEY + "&dates=" + Constants.getFourMonthsAgo() + "," + Constants.getTodayDate() + "&ordering=-added",
                response -> {
                    Gson gson = new Gson();
                    progressBar.setVisibility(View.GONE);
                    GameResponse gameResponse = gson.fromJson(response, GameResponse.class);
                    List<Game> items = gameResponse.getGames();
                    //Log.i("TAG", "fetchRecentGames: " + response);
                    adapterNewGames = new GameListAdapter(items);
                    recyclerViewNewGames.setAdapter(adapterNewGames);
                }, error -> {
            Log.i("TAG", "fetchRecentGames: " + error.toString());
            progressBar.setVisibility(View.GONE);
        });
        requestQueue.add(stringRequest);
    }

    private void fetchUpcomingGames() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar2.setVisibility(View.VISIBLE);
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, Constants.API_BASE_URL + "?key=" + Constants.API_KEY + "&dates=" + Constants.getTodayDate() + "," + Constants.getOneYearFromToday() + "&ordering=-added",
                response -> {
                    Gson gson = new Gson();
                    progressBar2.setVisibility(View.GONE);
                    GameResponse gameResponse = gson.fromJson(response, GameResponse.class);
                    List<Game> items = gameResponse.getGames();
                    //Log.i("TAG", "fetchUpcomingGames: " + response);
                    adapterUpcomingGames = new GameListAdapter(items);
                    recyclerViewUpcomingGames.setAdapter(adapterUpcomingGames);
                }, error -> {
            Log.i("TAG", "fetchUpcomingGames: " + error.toString());
            progressBar2.setVisibility(View.GONE);
        });
        requestQueue.add(stringRequest2);
    }

    private void initView() {
        recyclerViewNewGames = findViewById(R.id.recyclerViewNewReleases);
        recyclerViewNewGames.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewUpcomingGames = findViewById(R.id.recyclerViewUpcomingReleases);
        recyclerViewUpcomingGames.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        progressBar = findViewById(R.id.progressBarNewReleases);
        progressBar2 = findViewById(R.id.progressBarUpcomingReleases);
    }
}