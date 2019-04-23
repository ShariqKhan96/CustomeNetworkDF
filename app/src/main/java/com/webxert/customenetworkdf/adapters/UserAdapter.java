package com.webxert.customenetworkdf.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webxert.customenetworkdf.R;
import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 4/22/2019.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyVH> {
    List<Employees> users = new ArrayList<>();
    Context context;

    public UserAdapter(List<Employees> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserAdapter.MyVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_view, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyVH myVH, int i) {
        Employees user = users.get(i);
        myVH.email.setText(user.getEmployee_age());
        myVH.name.setText(user.getEmployee_name());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView name, email;

        public MyVH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }
    }
}
