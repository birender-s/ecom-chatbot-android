package com.example.theodhor.retrofit2.interfaces;


import com.example.theodhor.retrofit2.net.ProductList;

import retrofit2.Response;

public interface ProductDetailCallback {

    void fetchDetails(String id);
    void showDetails(Response<ProductList> response);
}
