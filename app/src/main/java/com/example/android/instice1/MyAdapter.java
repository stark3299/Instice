package com.example.android.instice1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] title;
    int[] img;

    public MyAdapter(Context context, String[] title, int[] img) {
        this.context = context;
        this.title = title;
        this.img = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.activity_my_adapter,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titleText.setText(title[i]);
        myViewHolder.imageView.setImageResource(img[i]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleText;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.textview1);
            imageView = itemView.findViewById(R.id.imageview);

        }
    }
}
