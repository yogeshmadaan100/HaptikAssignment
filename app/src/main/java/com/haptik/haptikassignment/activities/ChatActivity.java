package com.haptik.haptikassignment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.haptik.haptikassignment.R;
import com.haptik.haptikassignment.fragments.ChatFragment;
import com.haptik.haptikassignment.interfaces.ToolbarInterface;
import com.haptik.haptikassignment.models.Message;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity implements ChatFragment.onChatClickedInterface,ToolbarInterface{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onChatClicked(View itemView, Message message, boolean isDefaultSelection) {

    }

    @Override
    public void toggleToolbarVisibility(boolean value) {

    }

    @Override
    public void setToolbarTitle(String toolbarTitle) {

    }

    @Override
    public void setToolbarTheme(int toolbarTheme) {

    }

    @Override
    public void setHomeUpEnabled(boolean value) {

    }

    @Override
    public void setToolbarTitleTextColor(int toolbarTitleTextColor) {

    }

    @Override
    public void setHomeUpIndicator(int homeUpIndicator) {

    }

    @Override
    public void setToolbarMenu(Menu menu) {

    }

    @Override
    public void setToolbarBackgroundColor(int color) {

    }

    @Override
    public void showMenuItems(boolean value) {

    }
}
