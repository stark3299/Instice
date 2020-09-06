package com.example.android.instice1;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    public Context mContext;
    public ArrayList<Userr> users;
    String phone , message , message1;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

    public RecycleAdapter(Context ctx, ArrayList<Userr> users)
    {
        this.mContext = ctx;
        this.users = users;
    }
    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview1,parent,false );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {

        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.mcheck.setChecked(users.get(position).getCheck());
        Picasso.get().load(users.get(position).getImgView()).into(holder.imgView);
        
        holder.mains.setOnClickListener(v -> {
            String userId1 = users.get(position).getId();
            Boolean check = users.get(position).getCheck();
            if(check.equals(true)){
                Intent intent = new Intent(v.getContext() , Details.class);
                intent.putExtra("UserId" , userId1);
                v.getContext().startActivity(intent);
            }
            else {
                Intent intent = new Intent(v.getContext() , Detail1.class);
                intent.putExtra("UserId" , userId1);
                v.getContext().startActivity(intent);
            }

        });
        holder.mcheck.setOnClickListener(v -> {
           MaterialAlertDialogBuilder materialAlertDialogBuilder =  new MaterialAlertDialogBuilder(v.getContext());
            materialAlertDialogBuilder.setTitle("Case Solved ?")
                    .setMessage("Are you want to update the status of the case ?")
                    .setNegativeButton("No", (dialog, which) -> materialAlertDialogBuilder.setOnDismissListener(dialog1 -> {
                        Toast.makeText(mContext, "Nothing Changed", Toast.LENGTH_SHORT).show();
                    }))
                    .setPositiveButton("Yes", (dialog, which) -> {
                        String userId2 = users.get(position).getId();
                        Boolean check1 = users.get(position).getCheck();
                        message = v.getContext().getString(R.string.message);
                        message1 = v.getContext().getString(R.string.message1);
                        if(check1.equals(true)){
                            reference.child(userId2).child("check").setValue(false);
                            reference.keepSynced(true);
                            holder.mcheck.setChecked(users.get(position).getCheck());
                            sendSMS(userId2 , message1);
                        }
                        else {
                            reference.child(userId2).child("check").setValue(true);
                            reference.keepSynced(true);
                            holder.mcheck.setChecked(users.get(position).getCheck());
                            sendSMS(userId2 , message);
                        }
                    }).show();
            notifyDataSetChanged();
        });
    }
    public void sendSMS(String id1 , String mssg) {
        reference.orderByChild("id").equalTo(id1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    phone = datas.child("mobile_no").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        SmsManager mysms = SmsManager.getDefault();
        mysms.sendTextMessage(phone , null , mssg, null , null);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
        LinearLayout mains;
        ImageView imgView;
        CheckBox mcheck;

        private MyViewHolder(android.view.View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.s_name1);
            email = itemView.findViewById(R.id.s_email2);
            imgView = itemView.findViewById(R.id.imgview1);
            mcheck = itemView.findViewById(R.id.checkBox2);
            mains = itemView.findViewById(R.id.mains);
        }
    }
}
