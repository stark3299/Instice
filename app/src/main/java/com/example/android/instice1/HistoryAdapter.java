package com.example.android.instice1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder1> {
    private ArrayList<List1> list2;
    private Context mContext1 ;

    public HistoryAdapter(Context ctx1 , ArrayList<List1> list2) {
        this.list2 = list2;
        this.mContext1 = ctx1;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext1).inflate(R.layout.activity_history,parent,false );
        return new MyViewHolder1(view);

    }

    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder1 holder, int position) {
        holder.nameH.setText(list2.get(position).getName());
        holder.emailH.setText(list2.get(position).getEmail());
        holder.statusH1.setChecked(list2.get(position).getCheck());
        Picasso.get().load(list2.get(position).getImgView()).into(holder.imageView);
        if(holder.statusH1.isChecked()){
            holder.statusH.setText(R.string.status_h1);
        }
        else{
            holder.statusH.setText(R.string.status_h);
        }
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameH , emailH , statusH  ;
        CheckBox statusH1;
        LinearLayout linearLayout ;
        private MyViewHolder1(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            nameH= itemView.findViewById(R.id.sh_name1);
            emailH = itemView.findViewById(R.id.sh_email2);
            statusH = itemView.findViewById(R.id.statusH);
            linearLayout = itemView.findViewById(R.id.history1);
        }
    }
}
