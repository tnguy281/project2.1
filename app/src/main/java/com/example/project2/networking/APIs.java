package com.example.project2.networking;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;


public interface APIs {
    String BASE_URL = "https://fakestoreapi.com/products";

    @GET("products")
    Call<List<ProductResult>> getProducts();
}
