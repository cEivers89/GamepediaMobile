package com.project.gamepediamobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.project.gamepediamobile.Constants;
import com.project.gamepediamobile.GameItem;
import com.project.gamepediamobile.R;
import com.project.gamepediamobile.ScreenshotAdapter;

public class DetailActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ProgressBar progressBar;
    StringRequest stringRequest;
    private TextView gameNameTxt, metacriticScore, playTimeTxt, releaseDateTxt, gameDescription;
    private NestedScrollView scrollView;
    private int idGame;
    private ShapeableImageView gameImage;
    private ImageView backBtn, pic2;
    private RecyclerView.Adapter adapterScreenshots;
    private RecyclerView screenshotRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idGame = getIntent().getIntExtra("id", 0);
        initView();
        fetchGameDetails();
        fetchScreenShots();
    }

    private void fetchGameDetails() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        stringRequest = new StringRequest(Request.Method.GET, Constants.API_BASE_URL + "/" + idGame + "?key=" + Constants.API_KEY, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            GameItem item = gson.fromJson(response, GameItem.class);

            Log.i("TAG", "fetchGameDetails: " + response);
            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImage())
                    .into(gameImage);
            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImage())
                    .into(pic2);

            gameNameTxt.setText(item.getName());
            metacriticScore.setText(String.valueOf(item.getMetacritic()));
            playTimeTxt.setText(String.valueOf(item.getPlaytime()));
            releaseDateTxt.setText(item.getReleased());
            gameDescription.setText(Html.fromHtml(item.getDescription(), Html.FROM_HTML_MODE_COMPACT));
}, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("TAG", "onErrorResponse: " + error.toString());
        });
        requestQueue.add(stringRequest);
    }

    private void fetchScreenShots() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        stringRequest = new StringRequest(Request.Method.GET, Constants.API_BASE_URL + "/" + idGame + "/screenshots?key=" + Constants.API_KEY, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            GameItem item = gson.fromJson(response, GameItem.class);
            Log.i("TAG", "fetchScreenShots: " + response);

            if (item.getScreenshots() != null) {
                adapterScreenshots = new ScreenshotAdapter(item.getScreenshots());
                screenshotRecyclerView.setAdapter(adapterScreenshots);
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("TAG", "onErrorResponse: " + error.toString());
        });
        requestQueue.add(stringRequest);
    }

    private void initView() {
        progressBar = findViewById(R.id.detailLoading);
        scrollView = findViewById(R.id.scrollView3);
        gameNameTxt = findViewById(R.id.gameNameTxt);
        metacriticScore = findViewById(R.id.metacriticScore);
        playTimeTxt = findViewById(R.id.playTimeTxt);
        releaseDateTxt = findViewById(R.id.releaseDateTxt);
        gameDescription = findViewById(R.id.gameDescription);
        gameImage = findViewById(R.id.background_image);
        backBtn = findViewById(R.id.backBtn);
        pic2 = findViewById(R.id.background_image_additional);
        screenshotRecyclerView = findViewById(R.id.imagesRecyclerView);
        screenshotRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backBtn.setOnClickListener(v -> finish());
    }
}