package com.rhezarijaya.githubrr.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String username;
    private String name;
    private String location;
    private String company;
    private String avatar;
    private int repository;
    private int followers;
    private int following;

    public User(){}

    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        location = in.readString();
        company = in.readString();
        avatar = in.readString();
        repository = in.readInt();
        followers = in.readInt();
        following = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRepository() {
        return repository;
    }

    public void setRepository(int repository) {
        this.repository = repository;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", avatar='" + avatar + '\'' +
                ", repository=" + repository +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(company);
        dest.writeString(avatar);
        dest.writeInt(repository);
        dest.writeInt(followers);
        dest.writeInt(following);
    }
}
