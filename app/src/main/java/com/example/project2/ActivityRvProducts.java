package com.example.project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project2.adaptors.RVRetrofitAdapter;
import com.example.project2.networking.ProductResult;
import com.example.project2.networking.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityRvProducts extends AppCompatActivity {
    RecyclerView rvProducts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_products);
        rvProducts = findViewById(R.id.rvProducts);

        getProducts();
    }

    private void getProducts() {
        Call<List<ProductResult>> apiCall = RetrofitClient.getInstance().getApis().getProducts();
        apiCall.enqueue(new Callback<List<ProductResult>>() {
            @Override
            public void onResponse(Call<List<ProductResult>> call, Response<List<ProductResult>> response) {
                List<ProductResult> productResults = response.body();
                Toast.makeText(ActivityRvProducts.this, "Sum Products !",
                        Toast.LENGTH_SHORT).show();
                setAdapter(productResults);
            }

            @Override
            public void onFailure(Call<List<ProductResult>> call, Throwable t) {
                Toast.makeText(ActivityRvProducts.this, "Error! Error!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(List<ProductResult> productResults) {
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        RVRetrofitAdapter rvRetrofitAdapter = new RVRetrofitAdapter(this, productResults);
        rvProducts.setAdapter(rvRetrofitAdapter);
    }

}
