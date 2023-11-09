package com.project.gamepediamobile.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.project.gamepediamobile.Constants;
import com.project.gamepediamobile.GameFiles.GameItem;
import com.project.gamepediamobile.R;
import com.project.gamepediamobile.Adapter.ScreenshotAdapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements ScreenshotAdapter.OnItemClickListener {

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

            //Log.i("TAG", "fetchGameDetails: " + response);
            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImage())
                    .into(gameImage);
            Glide.with(DetailActivity.this)
                    .load(item.getBackgroundImageAdditional())
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
            List<String> screenshots = item.getScreenshots();
            // DEBUGGING ONLY - Log.i("TAG", "fetchScreenShots: " + item.getScreenshots());

            if (item.getScreenshots() != null && !screenshots.isEmpty()) {
                adapterScreenshots = new ScreenshotAdapter(item.getScreenshots(), this);
                screenshotRecyclerView.setAdapter(adapterScreenshots);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_image_viewer, null);
        ImageView expandedImage = dialogView.findViewById(R.id.expanded_image);

        Glide.with(this)
                .load(imageUrl)
                .into(expandedImage);

        Log.i("TAG", "showExpandedImage: " + imageUrl);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        expandedImage.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
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