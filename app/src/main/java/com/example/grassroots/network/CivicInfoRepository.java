package com.example.grassroots.network;

import android.util.Log;

import com.example.grassroots.MainActivity;
import com.example.grassroots.model.CivicInfoModel;
import com.example.grassroots.model.ElectedPositions;
import com.example.grassroots.model.ElectedRepresentatives;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CivicInfoRepository {

    private static Retrofit retrofit;
    private static final String CIVIC_INFO_BASE_URL = "https://www.googleapis.com/";

    public CivicInfoRepository() {
    }

    static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(CIVIC_INFO_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    void fetchElectedRepresentatives(String streetAddress, String civicAPIKey, final CivicInfoListener civicInfoListener) {
        getInstance().create(CivicInfoService.class)
                .getCivicInfo(civicAPIKey, streetAddress)
                .enqueue(new Callback<CivicInfoModel>() {
                    @Override
                    public void onResponse(Call<CivicInfoModel> call, Response<CivicInfoModel> response) {
                        CivicInfoModel civicInfoModel = response.body();
                        if (response.body() != null) {
                            Log.d(MainActivity.TAG, "onResponse: " + response.body().getElectedRepresentatives().get(0).getName());
                            civicInfoListener.onSuccess(civicInfoModel);
                        }
                    }

                    @Override
                    public void onFailure(Call<CivicInfoModel> call, Throwable t) {
                        Log.d(MainActivity.TAG, "onFailure: " + t.getMessage());
                        civicInfoListener.onFailure();
                    }
                });
    }


}