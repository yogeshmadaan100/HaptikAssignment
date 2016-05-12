package com.haptik.haptikassignment.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.models.Profile;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.txt_message_count)
    TextView txtMessageCount;
    @Bind(R.id.txt_username)
            TextView txtUsername;
    int actualBackdropViewWidth;
    Profile profile = null;
    public static final String KEY_PROFILE = "profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        if(getIntent()!=null)
        {
            profile = getIntent().getParcelableExtra(KEY_PROFILE);
        }
        if(profile==null){
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            finish();
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        collapsingToolbarLayout.setTitle(profile.getName());
        txtUsername.setText(profile.getName());
        txtMessageCount.setText(String.valueOf(profile.getMessageCount()));
        Glide.with(this).load(profile.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(backdrop);

//        loadBackdrop();
    }
    public void loadBackdrop()
    {
        final ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int gridWidth = viewGroup.getWidth();
                actualBackdropViewWidth = gridWidth ;
                Log.e("actualbackgrop width",""+gridWidth+profile.getImageUrl());
                Glide.with(getApplicationContext()).load(profile.getImageUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).
                        into(new SimpleTarget<Bitmap>(actualBackdropViewWidth,actualBackdropViewWidth) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                backdrop.setImageBitmap(resource); // Possibly runOnUiThread()
                            }
                        });
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(KEY_PROFILE,profile);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        profile = savedInstanceState.getParcelable(KEY_PROFILE);
    }
}
