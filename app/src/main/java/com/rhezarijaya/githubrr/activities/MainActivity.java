package com.rhezarijaya.githubrr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.rhezarijaya.githubrr.R;
import com.rhezarijaya.githubrr.adapters.UserListAdapter;
import com.rhezarijaya.githubrr.databinding.ActivityMainBinding;
import com.rhezarijaya.githubrr.models.User;
import com.rhezarijaya.githubrr.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        userListAdapter = new UserListAdapter(user -> {
            Intent intent = new Intent(this, UserDetailActivity.class);
            intent.putExtra(Constants.INTENT_MAIN_DETAIL, user);
            startActivity(intent);
        });

        activityMainBinding.mainRvUsers.setAdapter(userListAdapter);
        activityMainBinding.mainRvUsers.setHasFixedSize(true);

        showUserList();
    }

    private List<User> getDummyDataJSON() {
        List<User> users = new ArrayList<>();

        String jsonString = getResources().getString(R.string.json_dummy_data);

        try {
            JSONArray jsonUsers = new JSONObject(jsonString).getJSONArray("users");

            for (int i = 0; i < jsonUsers.length(); i++) {
                User user = new User();
                JSONObject userObject = jsonUsers.getJSONObject(i);

                user.setUsername(userObject.getString("username"));
                user.setName(userObject.getString("name"));
                user.setLocation(userObject.getString("location"));
                user.setRepository(userObject.getInt("repository"));
                user.setCompany(userObject.getString("company"));
                user.setFollowers(userObject.getInt("follower"));
                user.setFollowing(userObject.getInt("following"));
                user.setAvatar(userObject.getString("avatar").substring(10)); // @drawable/user1

                users.add(user);
            }

        } catch (JSONException | NumberFormatException ignored) {
        }

        return users;
    }

    private List<User> getDummyDataXML() {
        List<User> users = new ArrayList<>();

        String[] usernames = getResources().getStringArray(R.array.username);
        String[] names = getResources().getStringArray(R.array.name);
        String[] locations = getResources().getStringArray(R.array.location);
        String[] repositories = getResources().getStringArray(R.array.repository);
        String[] companies = getResources().getStringArray(R.array.company);
        String[] followers = getResources().getStringArray(R.array.followers);
        String[] followings = getResources().getStringArray(R.array.following);
        String[] avatars = getResources().getStringArray(R.array.avatar);

        for (int i = 0; i < usernames.length; i++) {
            User user = new User();

            try {
                user.setUsername(usernames[i]);
                user.setName(names[i]);
                user.setLocation(locations[i]);
                user.setRepository(Integer.parseInt(repositories[i]));
                user.setCompany(companies[i]);
                user.setFollowers(Integer.parseInt(followers[i]));
                user.setFollowing(Integer.parseInt(followings[i]));
                user.setAvatar(avatars[i].substring(13).replace(".png", "")); // res/drawable/user1.png
            } catch (NumberFormatException ignored) {
            }

            users.add(user);
        }

        return users;
    }

    private void showUserList() {
        if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            userListAdapter.setUserList(getDummyDataJSON());
            activityMainBinding.mainRvUsers.setLayoutManager(new LinearLayoutManager(this));
        } else {
            userListAdapter.setUserList(getDummyDataXML());
            activityMainBinding.mainRvUsers.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }
}