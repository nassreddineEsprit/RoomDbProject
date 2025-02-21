package tn.esprit.roomdbproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.roomdbproject.database.AppDataBase;
import tn.esprit.roomdbproject.entity.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> users;
    private Context mContext;

    public UserAdapter(List<User> users, Context mContext) {
        this.users = users;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = users.get(position);
        holder.firstname.setText(user.getFristName());
        holder.lastname.setText(user.getLastName());

        holder.btnDelete.setOnClickListener(v -> {

            AppDataBase.getAppDatabase(mContext).userDao().delete(user);

            users.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, users.size());
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstname, lastname;
        ImageView btnDelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
