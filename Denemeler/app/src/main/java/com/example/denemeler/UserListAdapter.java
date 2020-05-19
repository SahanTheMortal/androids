package com.example.denemeler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder>{
    ArrayList<Person> users;
    LayoutInflater inflater;
    public UserListAdapter(Context context, ArrayList<Person> users) {
        inflater = LayoutInflater.from(context);
        this.users = users;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_person_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person selectedPerson = users.get(position);
        holder.setData(selectedPerson, position);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView personName;

        public MyViewHolder(View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.personName);

        }

        public void setData(Person selectedProduct, int position) {

            this.personName.setText(selectedProduct.getUsername());
        }


        @Override
        public void onClick(View v) {


        }


    }
}
