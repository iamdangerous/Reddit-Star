package com.rahul_lohra.redditstar.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.rahul_lohra.redditstar.Application.Initializer;
import com.rahul_lohra.redditstar.modal.frontPage.FrontPageResponse;
import com.rahul_lohra.redditstar.retrofit.ApiInterface;
import com.rahul_lohra.redditstar.Utility.UserState;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rkrde on 09-02-2017.
 */

public class SubredditLoader extends AsyncTaskLoader<FrontPageResponse> {
    private static final String TAG = SubredditLoader.class.getSimpleName();

    FrontPageResponse frontPageResponse = null;

    @Inject
    @Named("withoutToken")
    public Retrofit retrofitWithoutToken;

    @Inject
    @Named("withToken")
    public Retrofit retrofitWithToken;

    private ApiInterface apiInterface;
    private String subbreditName;
    private boolean isUserLoggedIn;
    public static String after = null;


    public SubredditLoader(Context context, String subbreditName,String after) {
        super(context);
        ((Initializer) context.getApplicationContext()).getNetComponent().inject(this);
        this.subbreditName = subbreditName;
        this.after = after;
    }

    @Override
    public FrontPageResponse loadInBackground() {
        //determine whether you are logged in or not
        isUserLoggedIn = UserState.isUserLoggedIn(getContext());
        apiInterface = (isUserLoggedIn)?retrofitWithToken.create(ApiInterface.class):retrofitWithoutToken.create(ApiInterface.class);
        String token = (isUserLoggedIn) ? "bearer " + UserState.getAuthToken(getContext()) : "";

        after = (after!=null)?after:"";
        Map<String, String> map = new HashMap<>();
        map.put("after", after);
        map.put("limit", "15");
        try {
            Response<FrontPageResponse> res = apiInterface.getSubredditList(token,subbreditName,map).execute();
            Log.d(TAG,"resCode:"+res.code());
            if (res.code() == 200) {
                frontPageResponse = res.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            after = null;
        }
        return frontPageResponse;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (frontPageResponse != null) {
            // Use cached data
            deliverResult(frontPageResponse);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }

    @Override
    public void onCanceled(FrontPageResponse data) {
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(FrontPageResponse data) {
        frontPageResponse = data;
        super.deliverResult(data);

    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    protected void onReleaseResources(FrontPageResponse data) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
}
