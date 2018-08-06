package com.tinmegali.mylocation;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by 9DOTS04 on 3/1/2017.
 */

public interface latlngapi {
    @FormUrlEncoded
    @POST("/get_latlng")
    public void getforward(@Field("device") String device,
                           Callback<latlng> response);
}
