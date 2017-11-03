package com.example.theodhor.retrofit2;

import android.content.Context;
import android.util.Log;

import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.Events.ApiTokenModel;
import com.example.theodhor.retrofit2.Events.ConvserationModel;
import com.example.theodhor.retrofit2.Events.ErrorEvent;
import com.example.theodhor.retrofit2.Events.SendRequest;
import com.example.theodhor.retrofit2.Events.ServerEvent;
import com.example.theodhor.retrofit2.Events.StartConversationModel;
import com.example.theodhor.retrofit2.Events.UserArray;
import com.example.theodhor.retrofit2.interfaces.ProductDataCallback;
import com.example.theodhor.retrofit2.interfaces.TokenCallback;
import com.example.theodhor.retrofit2.interfaces.TopCateCallback;
import com.example.theodhor.retrofit2.interfaces.TopCategorySelectionCallback;
import com.example.theodhor.retrofit2.net.Interface;
import com.example.theodhor.retrofit2.net.ProductList;
import com.example.theodhor.retrofit2.net.ServerResponse;
import com.example.theodhor.retrofit2.net.TopProductModel;
import com.squareup.otto.Produce;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dori on 12/28/2016.
 */

public class AzureCommunicator {
    private static final String TAG = "AzureCommunicator";
    private static final String SERVER_URL = "https://ecom-search.search.windows.net";
    private static final String URL="https://directline.botframework.com";
    private static final String apiKey = "1D9B788C83C1DD0FBB2EBF0D2F5D75AE";
    private static final String apiVersion = "2015-02-28";
    private static final String topLevelCategoryFilter = "parent eq null";
    TopCateCallback topCateCallback;
    TopCategorySelectionCallback topCategorySelectionCallback;
    Context context;
    ProductDataCallback productDataCallback;
    TokenCallback tokenCallback;
    private static final String searchproductByID = "id eq '";
    public AzureCommunicator(Context context) {
        tokenCallback=(TokenCallback)context;

//        topCateCallback = (TopCateCallback) context;
       // topCategorySelectionCallback = (TopCategorySelectionCallback) context;
      //  productDataCallback = (ProductDataCallback) context;
    }


    public void getTopCategories() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<TopProductModel> call = service.get(apiKey, apiVersion, topLevelCategoryFilter);

        call.enqueue(new Callback<TopProductModel>() {
            @Override
            public void onResponse(Call<TopProductModel> call, Response<TopProductModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                topCateCallback.onSuccess(response);
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<TopProductModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                topCateCallback.onFailure(t);
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }

    public void getProductByCat(String subCatByCategoryName) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);
        Call<TopProductModel> call = service.subCategoriesByCategoryName(apiKey, apiVersion, subCatByCategoryName + "~\"");

        call.enqueue(new Callback<TopProductModel>() {
            @Override
            public void onResponse(Call<TopProductModel> call, Response<TopProductModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                topCategorySelectionCallback.onCategorySelection(response);
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<TopProductModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                topCategorySelectionCallback.onCategoryFailure(t);
                //BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }

    public void searchProduct(String productTitle) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);
        // String productTitle = "Touring-2000";
        //to get PRODUCTS LIST for given category ID (exact ID comparison)
        Call<ProductList> call = service.findProductsByTitle(apiKey, apiVersion, productTitle);


        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                productDataCallback.productCallback(response);
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void loginGet(String username, String password) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<TopProductModel> call = service.get("login", username, password);

        call.enqueue(new Callback<TopProductModel>() {
            @Override
            public void onResponse(Call<TopProductModel> call, Response<TopProductModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                //  BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<TopProductModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }

    private static final String searchVariantsByProductID = "productId eq '";
    public void getProductDetail(String productID) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        //to get TOP LEVEL Categories
//        Call<ServerResponse> call = service.get(apiKey, apiVersion,topLevelCategoryFilter);

        String title = "bikes";
        //to get SUB CATEGORIES for given parent category name (like comparison)
//        Call<ServerResponse> call = service.subCategoriesByCategoryName(apiKey, apiVersion,subCatListByParentCategoryName + title + "~\"");


//        String id = "1585696892350628365";
//        //to get SUB CATEGORIES for given parent category ID (exact ID comparison)
//        Call<ServerResponse> call = service.subcategoriesByParentId(apiKey, apiVersion, subCatListByParentCategoryID + id + "\'");


//        String categoryTitle = "Touring Bikes";
//        //to get PRODUCTS LIST for given category ID (exact ID comparison)
//        Call<ServerResponse> call = service.productsByCategoryTitle(apiKey, apiVersion, productsByCategoryTitle + categoryTitle + "\'");


//        String productID = "1585701252832952333";
//        //to get PRODUCTS LIST for given category ID (exact ID comparison)
//        Call<ServerResponse> call = service.findProductByID(apiKey, apiVersion, searchproductByID + productID + "\'");

//        String productTitle = "Touring-2000";
//        //to get PRODUCTS LIST for given category ID (exact ID comparison)
//        Call<ServerResponse> call = service.findProductsByTitle(apiKey, apiVersion, searchproductsByTitle + productTitle + "\'");


        //to get PRODUCTS LIST for given category ID (exact ID comparison)
        //last one
    /*    Call<ProductList> call = service.findVariantsByID(apiKey, apiVersion,
                searchVariantsByProductID + "1585703581309206800" + "\'" );*/
        Call<ProductList> call = service.findProductByID(apiKey, apiVersion, searchproductByID + productID + "\'");

        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                productDataCallback.productCallback(response);
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void getToken(String apiKey) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<ApiTokenModel> call = service.getApiToken(apiKey);

        call.enqueue(new Callback<ApiTokenModel>() {
            @Override
            public void onResponse(Call<ApiTokenModel> call, Response<ApiTokenModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                // topCateCallback.onSuccess(response);
                tokenCallback.getToken(response);
                Log.e(TAG, "Success"+response);
            }

            @Override
            public void onFailure(Call<ApiTokenModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                topCateCallback.onFailure(t);
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void startConversation(String token) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        Call<StartConversationModel> call = service.startConversation("Bearer "+token);

        call.enqueue(new Callback<StartConversationModel>() {
            @Override
            public void onResponse(Call<StartConversationModel> call, Response<StartConversationModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                tokenCallback.startConvsersationCallbacl(response);
                Log.e(TAG, "Success"+response);
            }

            @Override
            public void onFailure(Call<StartConversationModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                topCateCallback.onFailure(t);
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }

    public void sendActivityToBot(String token,String message,String convserationId) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        Interface service = retrofit.create(Interface.class);

        SendRequest sendRequest=new SendRequest();
        sendRequest.setText(message);
        sendRequest.setType("message");
        UserArray userArray=new UserArray();
        userArray.setId("user1");
        sendRequest.setFrom(userArray);
        Call<ConvserationModel> call = service.sendActivityToBot("Bearer "+token,sendRequest,convserationId);

        call.enqueue(new Callback<ConvserationModel>() {
            @Override
            public void onResponse(Call<ConvserationModel> call, Response<ConvserationModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                call.cancel();
               tokenCallback.fetchResponseFromBot(response);
                Log.e(TAG, "Success"+response);
            }

            @Override
            public void onFailure(Call<ConvserationModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
              //  topCateCallback.onFailure(t);
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void getResponseFromBot(String token,String waterMark,String convserationId) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        Interface service = retrofit.create(Interface.class);


        Call<ActivityModel> call = service.getConversationFromBot("Bearer "+token,convserationId,waterMark);

        call.enqueue(new Callback<ActivityModel>() {
            @Override
            public void onResponse(Call<ActivityModel> call, Response<ActivityModel> response) {
                // response.isSuccessful() is true if the response code is 2xx
                // BusProvider.getInstance().post(new ServerEvent(response.body()));
                tokenCallback.showBotResponse(response);
              //  Log.e(TAG, "Success"+response.body().getActivities().get(0).getText());
            }

            @Override
            public void onFailure(Call<ActivityModel> call, Throwable t) {
                // handle execution failures like no internet connectivity
                //  topCateCallback.onFailure(t);
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }
//4m3laVoNxNdAwAIiOL1jCx|0000000
}
