package com.sxl.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 123 on 2016/11/25.
 */

public interface NewsService {
    @GET("/api/cook/list" )
    Call<Tngou> getNews(@Query("id") int id,@Query("page") int page, @Query("rows") int rows);

}
