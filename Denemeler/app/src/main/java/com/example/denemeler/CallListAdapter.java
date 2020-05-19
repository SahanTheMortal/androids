package com.example.denemeler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.MyViewHolder> {
    ArrayList<Call> calls;
    LayoutInflater inflater;
    public CallListAdapter(Context context, ArrayList<Call> calls) {
        inflater = LayoutInflater.from(context);
        this.calls = calls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_call_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Call selectedCall= calls.get(position);
        holder.setData(selectedCall, position);

    }
    @Override
    public int getItemCount() {
        return calls.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView callNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            callNumber = (TextView) itemView.findViewById(R.id.callNumber);

        }

        public void setData(Call selectedCall, int position) {

            this.callNumber.setText(selectedCall.getNumber());
        }


        @Override
        public void onClick(View v) {


        }


    }
}
