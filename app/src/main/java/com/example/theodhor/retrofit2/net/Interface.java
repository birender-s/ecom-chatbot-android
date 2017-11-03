package com.example.theodhor.retrofit2.net;

import com.example.theodhor.retrofit2.model.ActivityModel;
import com.example.theodhor.retrofit2.Events.ApiTokenModel;
import com.example.theodhor.retrofit2.Events.ConvserationModel;
import com.example.theodhor.retrofit2.Events.SendRequest;
import com.example.theodhor.retrofit2.Events.StartConversationModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dori on 12/28/2016.
 */

public interface Interface {

    @FormUrlEncoded
    @POST("/sp/index.php")
    Call<ServerResponse> post(
            @Field("method") String method,
            @Field("username") String username,
            @Field("password") String password
    );

    //Azure search API
    @GET("/indexes/categories/docs")
    Call<TopProductModel> get(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    @GET("/indexes/categories/docs")
    Call<TopProductModel> subCategoriesByCategoryName(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );

    @GET("/indexes/categories/docs")
    Call<ServerResponse> subcategoriesByParentId(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    @GET("/indexes/products/docs")
    Call<ProductList> findProductsByTitle(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );
    @GET("/indexes/variants/docs")
    Call<ProductList> findVariantsByID(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @GET("/indexes/products/docs")
    Call<ProductList> findProductByID(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @POST("/v3/directline/tokens/generate")
    Call<ApiTokenModel> getApiToken(
            @Header("Authorization") String apiKey
    );

    //Azure search API
    @POST("/v3/directline/conversations")
    Call<StartConversationModel> startConversation(
            @Header("Authorization") String token
    );

    //Azure search API
    @POST("/v3/directline/conversations/{conversationId}/activities")
    Call<ConvserationModel> sendActivityToBot(
            @Header("Authorization") String token, @Body SendRequest sendRequest,@Path(value = "conversationId") String conversationId
    );

    //Azure search API
    @GET("/v3/directline/conversations/{conversationId}/activities")
    Call<ActivityModel> getConversationFromBot(
            @Header("Authorization") String token,@Path(value = "conversationId") String
            conversationId,@Query("watermark") String value
    );
}