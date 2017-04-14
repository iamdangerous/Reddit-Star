package com.rahul_lohra.redditstar.Utility;

import android.content.ContentValues;

import com.rahul_lohra.redditstar.modal.frontPage.FrontPageChildData;
import com.rahul_lohra.redditstar.modal.frontPage.Media;
import com.rahul_lohra.redditstar.modal.frontPage.Preview;
import com.rahul_lohra.redditstar.storage.column.MyPostsColumn;

import static com.rahul_lohra.redditstar.Utility.Constants.TYPE_POST;
import static com.rahul_lohra.redditstar.Utility.Constants.TYPE_SEARCH;
import static com.rahul_lohra.redditstar.Utility.Constants.TYPE_TEMP;
import static com.rahul_lohra.redditstar.Utility.Constants.TYPE_WIDGET;

/**
 * Created by rkrde on 27-02-2017.
 */

@SuppressWarnings("HardCodedStringLiteral")
public class CustomOrm {

    public static  ContentValues FrontPageChildDataToContentValues(FrontPageChildData data,@Constants.ArticleType int type){
        ContentValues cv = new ContentValues();

        cv.put(MyPostsColumn.KEY_ID,"t3_"+data.getId());
        cv.put(MyPostsColumn.KEY_AUTHOR,data.getAuthor());
        cv.put(MyPostsColumn.KEY_SUBREDDIT_ID,data.getSubredditId());
        cv.put(MyPostsColumn.KEY_SUBREDDIT,data.getSubreddit());
        cv.put(MyPostsColumn.KEY_UPS,data.getUps());
        cv.put(MyPostsColumn.KEY_TITLE,data.getTitle());
        cv.put(MyPostsColumn.KEY_COMMENTS_COUNT,data.getNumComments());
        cv.put(MyPostsColumn.KEY_CREATED_UTC,data.getCreatedUtc());
        cv.put(MyPostsColumn.KEY_CREATED,data.getCreated());
        cv.put(MyPostsColumn.KEY_NAME,data.getName());
        cv.put(MyPostsColumn.KEY_CLICKED,data.getClicked());
        Boolean likes = data.getLikes();
        cv.put(MyPostsColumn.KEY_LIKES,likes!=null?(likes?1:-1):0); //check Boolean
//        cv.put(MyPostsColumn.KEY_LIKES,(data.getLikes()!=null)?((data.getLikes())?1:-1):0);
        cv.put(MyPostsColumn.KEY_DOMAIN,data.getDomain());
        cv.put(MyPostsColumn.KEY_IS_SELF,data.getSelf());//check Boolean
        cv.put(MyPostsColumn.KEY_OVER_18,data.getOver18()); //check Boolean
        cv.put(MyPostsColumn.KEY_PERMALINK,data.getPermalink());
        cv.put(MyPostsColumn.KEY_POST_HINT,data.getPostHint());
        cv.put(MyPostsColumn.KEY_SCORE,data.getScore());
        cv.put(MyPostsColumn.KEY_THUMBNAIL,data.getThumbnail());
        cv.put(MyPostsColumn.KEY_URL,data.getUrl());
        cv.put(MyPostsColumn.KEY_VISITED,data.getVisited());//check boolean
        cv.put(MyPostsColumn.KEY_LOCKED,data.getLocked());//check boolean

        /*
        Special Case
        1.media Embeded
         */
        Media media = data.getMedia();
        cv.put(MyPostsColumn.KEY_MEDIA_OEMBED_TYPE,(media!=null)?data.getMedia().getType():null);
        Preview preview = data.getPreview();
        cv.put(MyPostsColumn.KEY_BIG_IMAGE_URL,(preview!=null)?preview.getImages().get(0).getSource().getUrl():null);
        String bigImageUrl = (preview!=null)?preview.getImages().get(0).getSource().getUrl():null;
        cv.put(MyPostsColumn.KEY_IS_BIG_IMAGE_URL_HAS_IMAGE,Constants.isBigImageUrlValid(bigImageUrl)?1:0);
        cv.put(MyPostsColumn.KEY_IS_THUMBNAIL_HAS_IMAGE,Constants.isBigImageUrlValid(data.getThumbnail())?1:0);



        switch (type){
            case TYPE_POST:
                cv.put(MyPostsColumn.TYPE_POST,1);
                break;
            case TYPE_SEARCH:
                cv.put(MyPostsColumn.TYPE_SEARCH,1);
                break;
            case TYPE_WIDGET:
                cv.put(MyPostsColumn.TYPE_WIDGET,1);
                break;
            case TYPE_TEMP:
                cv.put(MyPostsColumn.TYPE_TEMP,1);
                break;
        }

        return cv;

    }
}
