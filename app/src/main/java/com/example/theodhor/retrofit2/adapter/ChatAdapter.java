package com.example.theodhor.retrofit2.adapter;


import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.R;

import java.util.Calendar;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private List<ActivityModel> chatPojoModelList;
    private Context context;
    private LayoutInflater layoutInflater;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView inMessageTv, outMessageTv, attachmentmessagetv,productnametv,
        productpricetv,productdesctv;
        private LinearLayout productListContainer,productdetailll;
        private RecyclerView recycler_view_items;

        public MyViewHolder(View view) {
            super(view);
            productListContainer = (LinearLayout) view.findViewById(R.id.productcontainer);
            inMessageTv = (TextView) view.findViewById(R.id.inmessahetv);
            outMessageTv = (TextView) view.findViewById(R.id.outmessahetv);
            attachmentmessagetv = (TextView) view.findViewById(R.id.attachmentmessagetv);
            recycler_view_items = (RecyclerView) view.findViewById(R.id.recycler_view_items);
            productdetailll=(LinearLayout) view.findViewById(R.id.productdetailll);
            productnametv = (TextView) view.findViewById(R.id.productnametv);
            productpricetv = (TextView) view.findViewById(R.id.productpricetv);
            productdesctv = (TextView) view.findViewById(R.id.productdesctv);
        }

        public void onBindView(ActivityModel chatPojoModel) {
            if (chatPojoModel.getType().equalsIgnoreCase("userinput")) {
                inMessageTv.setText(chatPojoModel.getUserInput());
                outMessageTv.setVisibility(View.GONE);
                inMessageTv.setVisibility(View.VISIBLE);
                productListContainer.setVisibility(View.GONE);
                recycler_view_items.setVisibility(View.GONE);
                attachmentmessagetv.setVisibility(View.GONE);
                productdetailll.setVisibility(View.GONE);
            } else if (chatPojoModel.getType().equalsIgnoreCase("welcomemessage")) {
                Calendar c = Calendar.getInstance();
                int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

                if (timeOfDay >= 0 && timeOfDay < 12) {
                    outMessageTv.setText("Good Morning.\n\nWelcome to DemoApp\n How can " +
                            "i help you?");
                } else if (timeOfDay >= 12 && timeOfDay < 16) {
                    outMessageTv.setText("Good Afternoon.\n\nWelcome to DemoApp\n How can " +
                            "i help you?");
                } else if ((timeOfDay >= 16 && timeOfDay < 21) || (timeOfDay >= 21 && timeOfDay < 24)) {
                    outMessageTv.setText("Good Evening.\n\nWelcome to DemoApp\n How can " +
                            "i help you?");
                }
                outMessageTv.setVisibility(View.VISIBLE);
                inMessageTv.setVisibility(View.GONE);
                productListContainer.setVisibility(View.GONE);
                recycler_view_items.setVisibility(View.GONE);
                attachmentmessagetv.setVisibility(View.GONE);
                productdetailll.setVisibility(View.GONE);
            } else if (chatPojoModel.getType().equalsIgnoreCase("messagfrombot")) {
                if (null != chatPojoModel.getActivities() && chatPojoModel.getActivities().size() > 0) {
                    if (chatPojoModel.getActivities().size() > 0) {
                        for (int i = 0; i < chatPojoModel.getActivities().size(); i++) {
                            if (chatPojoModel.getActivities().get(i).getAttachmentLayout() != null
                                  && chatPojoModel.getActivities().get(i).getAttachmentLayout()
                                    .equalsIgnoreCase("list")) {
                                recycler_view_items.setVisibility(View.VISIBLE);
                                AttachmentAdapter attachmentAdapter = new AttachmentAdapter
                                        (context, chatPojoModel.getActivities().get
                                                (i).getAttachments());
                                RecyclerView.LayoutManager mLayoutManager = new
                                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                                recycler_view_items.setLayoutManager(mLayoutManager);
                                recycler_view_items.setItemAnimator(new DefaultItemAnimator());
                                recycler_view_items.setAdapter(attachmentAdapter);
                                if (outMessageTv.getVisibility() != View.VISIBLE)
                                    outMessageTv.setVisibility(View.GONE);
                                inMessageTv.setVisibility(View.GONE);
                                productListContainer.setVisibility(View.GONE);
                                productdetailll.setVisibility(View.GONE);
                            } else if (chatPojoModel.getActivities().get(i).getAttachmentLayout()
                                    == null && chatPojoModel.getActivities().get(i).getAttachments()
                                    != null && chatPojoModel.getActivities().get(i)
                                    .getAttachments().get(0).getContent().getImages()!=null) {
                              //  if (outMessageTv.getVisibility() != View.VISIBLE)


                                productnametv.setText(chatPojoModel.getActivities().get(i)
                                        .getAttachments().get(0).getContent().getTitle());
                                productpricetv.setText(chatPojoModel.getActivities().get(i)
                                        .getAttachments().get(0).getContent().getSubtitle());
                                productdesctv.setText(chatPojoModel.getActivities().get(i)
                                        .getAttachments().get(0).getContent().getText());
                                productdetailll.setVisibility(View.VISIBLE);
                                outMessageTv.setVisibility(View.GONE);
                                inMessageTv.setVisibility(View.GONE);
                                productListContainer.setVisibility(View.GONE);
                                recycler_view_items.setVisibility(View.GONE);
                                attachmentmessagetv.setVisibility(View.GONE);
                            } else {
                                if (chatPojoModel.getActivities().get(i).getText() != null &&
                                        !chatPojoModel.getActivities().get(i).getText()
                                                .equalsIgnoreCase("")) {
                                    outMessageTv.setText(chatPojoModel.getActivities().get(i).getText());
                                    outMessageTv.setVisibility(View.VISIBLE);
                                    inMessageTv.setVisibility(View.GONE);
                                    productListContainer.setVisibility(View.GONE);
                                    recycler_view_items.setVisibility(View.GONE);
                                    attachmentmessagetv.setVisibility(View.GONE);
                                    productdetailll.setVisibility(View.GONE);
                                }

                            }
                        }
                    }

                }

            }
        }
    }


    public ChatAdapter(Context context, List<ActivityModel> chatPojoModelList) {
        this.chatPojoModelList = chatPojoModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBindView(chatPojoModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatPojoModelList.size();
    }


//    @Override
//    public long getItemId(int position) {
//        return chatPojoModelList.get(position);
//    }
}