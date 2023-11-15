package com.project.gamepediamobile.Activities;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.project.gamepediamobile.Adapter.ScreenshotAdapter;
import com.project.gamepediamobile.Constants;
import com.project.gamepediamobile.GameFiles.GameInfo;
import com.project.gamepediamobile.GameFiles.GameResponse;
import com.project.gamepediamobile.GameFiles.ParentPlatform;
import com.project.gamepediamobile.R;
import com.project.gamepediamobile.Utils;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements ScreenshotAdapter.OnItemClickListener {

    RequestQueue requestQueue;
    ProgressBar progressBar;
    StringRequest stringRequest;
    private TextView gameNameTxt, ratingTxt, playTimeTxt, releaseDateTxt, gameDescription,
            platformsTxt, genresTxt, metacriticTxt, developersTxt, publishersTxt;
    private NestedScrollView scrollView;
    private int idGame;
    private ShapeableImageView gameImage;
    private ImageView backBtn, pic2;
    private ScreenshotAdapter screenshotAdapter;
    private RecyclerView screenshotRecyclerView;
    private FloatingActionButton homeFab;

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

            GameInfo item = gson.fromJson(response, GameInfo.class);

            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImage())
                    .into(gameImage);
            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImageAdditional())
                    .into(pic2);

            gameNameTxt.setText(item.getName());
            ratingTxt.setText(String.valueOf(item.getRating()));
            //playTimeTxt.setText(String.valueOf(item.getPlaytime()));
            releaseDateTxt.setText(item.getReleased());
            gameDescription.setText(Html.fromHtml(item.getDescription(), Html.FROM_HTML_MODE_COMPACT));
            metacriticTxt.setText(String.valueOf(item.getMetacritic()));
            platformsTxt.setText(Utils.joinListToString(item.getParentPlatforms()));
            genresTxt.setText(Utils.joinListToString(item.getGenres()));
            developersTxt.setText(Utils.joinListToString(item.getDevelopers()));
            publishersTxt.setText(Utils.joinListToString(item.getPublishers()));
            },
                error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("TAG", "onErrorResponse: " + error.toString());
        });
        requestQueue.add(stringRequest);
    }

    private void fetchScreenShots() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        stringRequest = new StringRequest(Request.Method.GET, Constants.API_BASE_URL + "/" + idGame + "/screenshots?key=" + Constants.API_KEY + "&page_size=5", response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            GameResponse.Screenshots item = gson.fromJson(response, GameResponse.Screenshots.class);
            List<String> screenshots = item.getScreenshots();

            if (!screenshots.isEmpty()) {
                screenshotAdapter = new ScreenshotAdapter(item.getScreenshots(), this);
                screenshotRecyclerView.setAdapter(screenshotAdapter);
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("TAG", "onErrorResponse: " + error.toString());
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClicked(String imageUrl) {
        showExpandedImage(imageUrl);
    }

    private void showExpandedImage(String imageUrl) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_expand_image);
        ImageView expandedImage = dialog.findViewById(R.id.expanded_image);

        Glide.with(this)
                .load(imageUrl)
                .into(expandedImage);

        Log.i("TAG", "showExpandedImage: " + imageUrl);

        expandedImage.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void initView() {
        progressBar = findViewById(R.id.detailLoading);
        scrollView = findViewById(R.id.scrollView3);
        gameNameTxt = findViewById(R.id.gameNameTxt);
        ratingTxt = findViewById(R.id.ratingTxt);
        // playTimeTxt = findViewById(R.id.playTimeTxt); - NOT CURRENTLY BEING USED - DATA FROM API NOT ACCURATE
        releaseDateTxt = findViewById(R.id.releaseDateTextView);
        gameDescription = findViewById(R.id.gameDescription);
        gameImage = findViewById(R.id.background_image);
        backBtn = findViewById(R.id.backBtn);
        pic2 = findViewById(R.id.background_image_additional);
        platformsTxt = findViewById(R.id.platformsListTextView);
        genresTxt = findViewById(R.id.genresListTextView);
        metacriticTxt = findViewById(R.id.metacriticScoreTextView);
        developersTxt = findViewById(R.id.developerListTextView);
        publishersTxt = findViewById(R.id.publisherListTextView);
        screenshotRecyclerView = findViewById(R.id.imagesRecyclerView);
        screenshotRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        homeFab = findViewById(R.id.homeFab);

        backBtn.setOnClickListener(v -> finish());

        homeFab.setOnClickListener(v -> {
            finish();
        });
    }
}