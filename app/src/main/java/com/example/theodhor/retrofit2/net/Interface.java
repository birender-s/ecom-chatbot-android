package com.example.theodhor.retrofit2.net;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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
    Call<ServerResponse> get(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @GET("/indexes/categories/docs")
    Call<ServerResponse> subCategoriesByCategoryName(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );

    //Azure search API
    @GET("/indexes/categories/docs")
    Call<ServerResponse> subcategoriesByParentId(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @GET("/indexes/products/docs")
    Call<ServerResponse> productsByCategoryTitle(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );

    //Azure search API
    @GET("/indexes/products/docs")
    Call<ServerResponse> findProductByID(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @GET("/indexes/products/docs")
    Call<ServerResponse> findProductsByTitle(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );

    //Azure search API
    @GET("/indexes/variants/docs")
    Call<ServerResponse> findVariantsByID(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("$filter") String filter
    );

    //Azure search API
    @POST("/oauth/access_token")
    @FormUrlEncoded
    Call<ServerResponse> authenticateMoltin(
            @Field("client_id")String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grant_type     //client_credentials
    );


}