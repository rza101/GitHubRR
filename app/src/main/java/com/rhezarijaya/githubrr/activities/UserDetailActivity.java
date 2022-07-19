package com.rhezarijaya.githubrr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rhezarijaya.githubrr.R;
import com.rhezarijaya.githubrr.databinding.ActivityUserDetailBinding;
import com.rhezarijaya.githubrr.models.User;
import com.rhezarijaya.githubrr.utils.Constants;

public class UserDetailActivity extends AppCompatActivity {

    private ActivityUserDetailBinding activityUserDetailBinding;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserDetailBinding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(activityUserDetailBinding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (getIntent().getParcelableExtra(Constants.INTENT_MAIN_DETAIL) != null) {
            user = getIntent().getParcelableExtra(Constants.INTENT_MAIN_DETAIL);
        } else {
            Toast.makeText(this, "Illegal Access", Toast.LENGTH_SHORT).show();
            finish();
        }

        Glide.with(this)
                .load(getResources().getIdentifier(user.getAvatar(), "drawable", getPackageName()))
                .into(activityUserDetailBinding.detailCivAvatar);

        activityUserDetailBinding.detailTvName.setText(user.getName());
        activityUserDetailBinding.detailTvUsername.setText("@" + user.getUsername());
        activityUserDetailBinding.detailTvRepositories.setText(String.valueOf(user.getRepository()));
        activityUserDetailBinding.detailTvFollowers.setText(String.valueOf(user.getFollowers()));
        activityUserDetailBinding.detailTvFollowings.setText(String.valueOf(user.getFollowing()));
        activityUserDetailBinding.detailTvLocation.setText(user.getLocation());
        activityUserDetailBinding.detailTvCompany.setText(user.getCompany());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.detail_menu_share) {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, user.getName());
            sendIntent.setType("text/plain");

            startActivity(Intent.createChooser(sendIntent, "Share this profile"));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}