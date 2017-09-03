package com.example.theodhor.retrofit2;

import android.util.Log;

import com.example.theodhor.retrofit2.Events.ErrorEvent;
import com.example.theodhor.retrofit2.Events.ServerEvent;
import com.example.theodhor.retrofit2.net.Interface;
import com.example.theodhor.retrofit2.net.ServerResponse;
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
    private static  final String TAG = "AzureCommunicator";
    private static final String SERVER_URL = "https://commerce-search.search.windows.net";
    private static final String apiKey = "6731AD6C28D7688B13AC68FCC0DDBD4E";
    private static final String apiVersion = "2015-02-28";

    //categories
    private static final String topLevelCategoryFilter = "parent eq null";
    private static final String subCatListByParentCategoryName = "title:\"";
    private static final String subCatListByParentCategoryID = "parent eq '";

    //products
    private static final String productsByCategoryTitle = "subcategory:'";
    private static final String searchproductByID = "id eq '";
    private static final String searchproductsByTitle = "title:'";

    //variants
    private static final String searchVariantsByProductID = "productId eq '";


    public void getTopCategories(){

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



        //to get Variant by ID, this is hard coded for now. Once fixed, we'll make it dynamic
        Call<ServerResponse> call = service.findVariantsByID(apiKey, apiVersion, searchVariantsByProductID + "1585703581309206800" + "\'");






        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    public void loginGet(String username, String password){

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

        Call<ServerResponse> call = service.get("login",username,password);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
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
}
