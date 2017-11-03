package com.example.theodhor.retrofit2.interfaces;


import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.Events.ApiTokenModel;
import com.example.theodhor.retrofit2.Events.ConvserationModel;
import com.example.theodhor.retrofit2.Events.StartConversationModel;

import retrofit2.Response;

public interface TokenCallback {
    void getToken(Response<ApiTokenModel> apiTokenModel);
    void startConvsersationCallbacl(Response<StartConversationModel> apiTokenModel);
    void fetchResponseFromBot(Response<ConvserationModel> response);
    void showBotResponse(Response<ActivityModel> response);
    void productSelectionCallback(String productId);
}
