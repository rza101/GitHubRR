package com.rhezarijaya.githubrr.models;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class UserDiffCallback extends DiffUtil.Callback {
    private final List<User> oldList;
    private final List<User> newList;

    public UserDiffCallback(List<User> oldList, List<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getUsername().equals(newList.get(newItemPosition).getUsername());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getUsername().equals(newList.get(newItemPosition).getUsername());
    }
}
