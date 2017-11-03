package com.example.theodhor.retrofit2.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.theodhor.retrofit2.Events.SendRequest;
import com.example.theodhor.retrofit2.Events.UserArray;
import com.example.theodhor.retrofit2.R;
import com.example.theodhor.retrofit2.interfaces.TokenCallback;
import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.model.Attachment;

import java.util.Calendar;
import java.util.List;

public class AttachmentAdapter extends RecyclerView.Adapter<AttachmentAdapter.MyViewHolder> {

    private List<Attachment> attachments;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productnametv, productpricetv, productdesctv;
        private Button showmebtn;
        private ImageView productiv;

        public MyViewHolder(View view) {
            super(view);
            productnametv = (TextView) view.findViewById(R.id.productnametv);
            productpricetv = (TextView) view.findViewById(R.id.productpricetv);
            productdesctv = (TextView) view.findViewById(R.id.productdesctv);
            showmebtn = (Button) view.findViewById(R.id.showmebtn);
            productiv = (ImageView) view.findViewById(R.id.productiv);
        }

        public void onBindView(final Attachment attachment) {
            productnametv.setText(attachment.getContent().getTitle());
            productpricetv.setText(attachment.getContent().getSubtitle());
            productdesctv.setText(attachment.getContent().getText());
            showmebtn.setText(attachment.getContent().getButtons().get(0).getTitle());
            Glide.with(context)
                    .load(attachment.getContent().getImages().get(0).getUrl())
                    .into(productiv);
            showmebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("info", attachment.getContent().getText());
                    ((TokenCallback) context).productSelectionCallback(attachment.getContent()
                            .getButtons().get(0).getValue());
                }
            });
        }
    }


    public AttachmentAdapter(Context context, List<Attachment> attachments) {
        this.attachments = attachments;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBindView(attachments.get(position));
    }

    @Override
    public int getItemCount() {
        return attachments.size();
    }


}


