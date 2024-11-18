package com.example.vchat.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vchat.Models.messageModel;
import com.example.vchat.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter{
    ArrayList<messageModel> messageModels;
    Context context;
    String racId;
    int SENDER_VIEW_TYPE=1;
    int RECIEVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<messageModel> messageModels,Context context){
        this.messageModels=messageModels;
        this.context=context;

    }

    public ChatAdapter(ArrayList<messageModel> messageModels, Context context, String racId) {
        this.messageModels = messageModels;
        this.context = context;
        this.racId = racId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==SENDER_VIEW_TYPE){
           View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
           return new SenderViewHolder(view);
       }
       else{
           View view = LayoutInflater.from(context).inflate(R.layout.samle_reciver,parent,false);
           return new ReciverViewHolder(view);
       }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECIEVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messageModel messageModel=messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are You sure want to Delete this Message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database =FirebaseDatabase.getInstance();
                                String senderRoom=FirebaseAuth.getInstance().getUid() + racId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messageModel.getMessageid())
                                        .setValue(null);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return false;
            }
        });

        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder) holder).senderMsg.setText(messageModel.getMessage());
            Date date =new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
            String strdate=simpleDateFormat.format(date);
            ((SenderViewHolder)holder).senderTime.setText(strdate);
        }
        else{
            ((ReciverViewHolder)holder).recieverMsg.setText(messageModel.getMessage());

            Date date =new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
            String strdate=simpleDateFormat.format(date);
            ((ReciverViewHolder)holder).recieverTime.setText(strdate);
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReciverViewHolder extends RecyclerView.ViewHolder{

        TextView recieverMsg,recieverTime;
        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);

            recieverMsg=itemView.findViewById(R.id.recivertext);
            recieverTime=itemView.findViewById(R.id.reciverTime);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg,senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg=itemView.findViewById(R.id.sendertext);
            senderTime=itemView.findViewById(R.id.senderTime);

        }
    }

}
