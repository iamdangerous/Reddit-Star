package com.rahul_lohra.redditstar.retrofit;

import com.rahul_lohra.redditstar.modal.AboutMe;
import com.rahul_lohra.redditstar.modal.GifyCat.GifyCatResponse;
import com.rahul_lohra.redditstar.modal.RefreshTokenResponse;
import com.rahul_lohra.redditstar.modal.SubredditResponse;
import com.rahul_lohra.redditstar.modal.frontPage.FrontPageResponse;
import com.rahul_lohra.redditstar.modal.reply.ReplyResponse;
import com.rahul_lohra.redditstar.modal.search.T5_SearchResponse;
import com.rahul_lohra.redditstar.modal.t5_Subreddit.t5_Response;
import com.rahul_lohra.redditstar.Utility.Constants;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by rkrde on 24-01-2017.
 */

public interface ApiInterface {

    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    @GET("/subreddits/mine/subscriber")
    Call<SubredditResponse> getMySubscribedSubreddits(@Header(Constants.AUTHORIZATION) String authorization,
                                                      @QueryMap Map<String, String> options);

    @GET("/api/v1/me")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<AboutMe> getAboutMe(@Header(Constants.AUTHORIZATION) String authorization);

    @FormUrlEncoded
    @POST("/api/v1/access_token")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<RefreshTokenResponse> refreshToken(@Header(Constants.AUTHORIZATION) String authorization,
                                            @Field("grant_type") String grant_type,
                                            @Field("refresh_token") String refresh_token);

    @GET("/{filter_param_1}/.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<FrontPageResponse> getFrontPage(@Header(Constants.AUTHORIZATION) String authorization,
                                         @Path(value = "filter_param_1", encoded = true) String filterParam_1,
                                         @QueryMap Map<String, String> options);

    @GET("/r/{subbreddit_name}/.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<FrontPageResponse> getSubredditList(@Header(Constants.AUTHORIZATION) String authorization,
                                             @Path(value = "subbreddit_name", encoded = true) String subbreddit_name,
                                             @QueryMap Map<String, String> map);

    @GET("/r/{subbreddit_name}/about/.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<t5_Response> getAboutSubreddit(@Header(Constants.AUTHORIZATION) String authorization,
                                        @Path(value = "subbreddit_name", encoded = true) String subbreddit_name
    );

    @GET("/r/{subbreddit_name}/comments/{postId}.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ResponseBody> getComments(@Header(Constants.AUTHORIZATION) String authorization,
                                   @Path(value = "postId", encoded = true) String postId,
                                   @Path(value = "subbreddit_name", encoded = true) String subbreddit_name,
                                   @QueryMap Map<String, String> map);

    @POST("/api/vote")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ResponseBody> postVote(@Header(Constants.AUTHORIZATION) String authorization,
                                @QueryMap Map<String, String> map);

    @GET("/subreddits/search.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<T5_SearchResponse> searchSubreddits(@Header(Constants.AUTHORIZATION) String authorization,
                                             @QueryMap Map<String, Object> map);

    @GET("/subreddits/search.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Observable<T5_SearchResponse> searchSubredditsRx(@Header(Constants.AUTHORIZATION) String authorization,
                                      @QueryMap Map<String, Object> map);

    @GET("search.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<FrontPageResponse> searchLinks(@Header(Constants.AUTHORIZATION) String authorization,
                                        @QueryMap Map<String, Object> map);

    @GET("search.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Observable<FrontPageResponse> searchLinksRx(@Header(Constants.AUTHORIZATION) String authorization,
                                        @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/comment")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ReplyResponse> postComment(@Header(Constants.AUTHORIZATION) String authorization,
                                    @Field("api_type")String api_type,
                                    @Field("text")String text,
                                    @Field("thing_id")String thing_id);

    @FormUrlEncoded
    @POST("/api/subscribe")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ResponseBody> subscribeSubreddit_new(@Header(Constants.AUTHORIZATION) String authorization,
                                              @Field("action") String action,
                                              @Field("skip_initial_defaults") boolean skip_initial_defaults,
                                              @Field("sr") String fullName
                                              );
//    https://www.reddit.com/r/all/controversial/?sort=controversial&t=month
    @GET("/r/{subbreddit_name}/{sort}/.json")
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<FrontPageResponse> getSubredditPosts(@Header(Constants.AUTHORIZATION) String authorization,
                                              @Path(value = "subbreddit_name", encoded = true) String subreddit_name,
                                              @Path(value = "sort", encoded = true) String sort,
                                              @QueryMap Map<String, String> options);

    @GET
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ResponseBody> getDataFromGfyCat(@Url String url);

    @GET
    @Headers("User-Agent: android:com.android.rahul_lohra.redditstar:v1.0 (by /u/rahul_lohra)")
    Call<ResponseBody> getDataFromImgur(@Header(Constants.AUTHORIZATION) String authorization,@Url String url);
}
