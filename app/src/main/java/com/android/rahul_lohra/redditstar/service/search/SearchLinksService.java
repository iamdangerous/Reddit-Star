package com.android.rahul_lohra.redditstar.service.search;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.rahul_lohra.redditstar.application.Initializer;
import com.android.rahul_lohra.redditstar.modal.search.T3_SearchResponse;
import com.android.rahul_lohra.redditstar.modal.search.T5_SearchResponse;
import com.android.rahul_lohra.redditstar.retrofit.ApiInterface;
import com.android.rahul_lohra.redditstar.utility.UserState;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rkrde on 20-02-2017.
 */

public class SearchLinksService extends IntentService {

    @Inject
    @Named("withoutToken")
    Retrofit retrofitWithoutToken;

    @Inject
    @Named("withToken")
    Retrofit retrofitWithToken;
    ApiInterface apiInterface;
    private static final String TAG = SearchLinksService.class.getSimpleName();
    public static String after = null;
    public SearchLinksService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ((Initializer) getApplication()).getNetComponent().inject(this);
        apiInterface = (UserState.isUserLoggedIn(getApplicationContext())) ? retrofitWithToken.create(ApiInterface.class) : retrofitWithoutToken.create(ApiInterface.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            boolean isUserLoggedIn = UserState.isUserLoggedIn(getApplicationContext());
            apiInterface = (isUserLoggedIn)?retrofitWithToken.create(ApiInterface.class):retrofitWithoutToken.create(ApiInterface.class);
            String token = (isUserLoggedIn) ? "bearer " + UserState.getAuthToken(getApplicationContext()) : "";
            String subredditName = intent.getStringExtra("query");
            after = intent.getStringExtra("after");
            Map<String, String> map = new HashMap<>();
            map.put("after", after);
            map.put("limit", "2");
            map.put("q",subredditName);
            try {
                Response<T3_SearchResponse> res = apiInterface.searchLinks(token,map).execute();
                Log.d(TAG,"resCode:"+res.code());
                if (res.code() == 200) {
                    EventBus.getDefault().post(res.body().data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                after = null;
            }
        }

    }
}