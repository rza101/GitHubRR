package com.rhezarijaya.githubrr.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rhezarijaya.githubrr.databinding.ListItemUserBinding;
import com.rhezarijaya.githubrr.models.User;
import com.rhezarijaya.githubrr.models.UserDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> userList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public UserListAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUserList(List<User> userList) {
        UserDiffCallback userDiffCallback = new UserDiffCallback(this.userList, userList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(userDiffCallback);

        this.userList.clear();
        this.userList.addAll(userList);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemUserBinding listItemUserBinding = ListItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(listItemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        String name = userList.get(pos).getName() + " (@" + userList.get(pos).getUsername() + ")";
        String location = userList.get(pos).getLocation();
        String company = userList.get(pos).getCompany();
        String avatar = userList.get(pos).getAvatar();

        Glide.with(holder.itemView.getContext())
                .load(holder.itemView.getResources().getIdentifier(avatar, "drawable", holder.itemView.getContext().getPackageName()))
                .into(holder.listItemUserBinding.itemUserCivAvatar);

        holder.listItemUserBinding.itemUserTvName.setText(name);
        holder.listItemUserBinding.itemUserTvLocation.setText(location);
        holder.listItemUserBinding.itemUserTvCompany.setText(company);

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClidk(userList.get(pos)));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemUserBinding listItemUserBinding;

        public ViewHolder(ListItemUserBinding listItemUserBinding) {
            super(listItemUserBinding.getRoot());
            this.listItemUserBinding = listItemUserBinding;
        }
    }

    public interface OnItemClickListener{
        void onItemClidk(User user);
    }
}
