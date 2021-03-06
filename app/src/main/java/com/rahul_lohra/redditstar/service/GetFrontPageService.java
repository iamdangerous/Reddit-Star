package com.rahul_lohra.redditstar.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.rahul_lohra.redditstar.Application.Initializer;
import com.rahul_lohra.redditstar.modal.custom.AfterModal;
import com.rahul_lohra.redditstar.modal.frontPage.FrontPageChild;
import com.rahul_lohra.redditstar.modal.frontPage.FrontPageResponse;
import com.rahul_lohra.redditstar.retrofit.ApiInterface;
import com.rahul_lohra.redditstar.storage.MyProvider;
import com.rahul_lohra.redditstar.Utility.Constants;
import com.rahul_lohra.redditstar.Utility.SpConstants;
import com.rahul_lohra.redditstar.Utility.UserState;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Response;
import retrofit2.Retrofit;


@SuppressWarnings("HardCodedStringLiteral")
public class GetFrontPageService extends IntentService {

    @Inject
    @Named("withoutToken")
    Retrofit retrofitWithoutToken;

    @Inject
    @Named("withToken")
    Retrofit retrofitWithToken;

    ApiInterface apiInterface;
    final Uri mUri = MyProvider.PostsLists.CONTENT_URI;

    public static String after = null;
    public boolean isUserLoggedIn = false;
    private final  String TAG = GetFrontPageService.class.getSimpleName();
    SharedPreferences sp;
    public GetFrontPageService() {
        super(GetFrontPageService.class.getSimpleName());
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ((Initializer) getApplication()).getNetComponent().inject(this);
        isUserLoggedIn = UserState.isUserLoggedIn(getApplicationContext());
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            apiInterface = (isUserLoggedIn)?retrofitWithToken.create(ApiInterface.class):retrofitWithoutToken.create(ApiInterface.class);
            after = intent.getStringExtra("after");
            if(null ==after){
                return;
            }
            Map<String,String> map = new HashMap<>();
            map.put("limit","10");
            map.put("after",after);
            map.put(SpConstants.OVER_18,String.valueOf(sp.getBoolean(SpConstants.OVER_18,false)));
            String token = (isUserLoggedIn) ? "bearer " + UserState.getAuthToken(getApplicationContext()) : "";

            try {
                Response<FrontPageResponse> res = apiInterface.getFrontPage(token,map).execute();
                if(res.code()==200)
                {
//                    EventBus.getDefault().post(res.body().getData());
                    EventBus.getDefault().post(new AfterModal(res.body().getData().getAfter()));
                    Constants.insertPostsIntoTable(getApplicationContext(),res.body(),Constants.TYPE_POST);
                    for(FrontPageChild frontPageChild:res.body().getData().getChildren()){
                        String url = frontPageChild.getData().getThumbnail();
                        FutureTarget<File> future = Glide.with(getApplicationContext())
                                .load(url)
                                .downloadOnly(100, 100);
//                        String url = frontPageChild.getData();
//                        Preview preview = frontPageChild.getData().getPreview();
//                        String bigUrl = (preview!=null)?preview.getImages().get(0).getSource().getUrl():null;
//                        if(bigUrl!=null){
//                            FutureTarget<File> future_2 = Glide.with(getApplicationContext())
//                                    .load(url)
//                                    .downloadOnly(250,250);
//                        }


                    }
                }
                Log.d(TAG,"response:"+res.code());
            } catch (IOException e) {
                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
            } finally {
                after = null;
            }
        }
    }

}
