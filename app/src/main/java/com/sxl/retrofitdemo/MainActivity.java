package com.sxl.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Tngou>{

    @Bind(R.id.news_listView)
    ListView newsListView;

    private NewsAdapter adapter;

    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService newsService = retrofit.create(NewsService.class);
        Call<Tngou> call = newsService.getNews(0,1,20);
        call.enqueue((Callback<Tngou>) this);

        ListView newsListView = (ListView) findViewById(R.id.news_listView);
        adapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(adapter);

    }

    @Override
    public void onResponse(Call<Tngou> call, Response<Tngou> response) {
        response.body().getNewsList();
    }

    @Override
    public void onFailure(Call<Tngou> call, Throwable t) {

    }
}
