package com.rahul_lohra.redditstar.Utility;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.rahul_lohra.redditstar.storage.MyProvider;
import com.rahul_lohra.redditstar.storage.column.UserCredentialsColumn;

/**
 * Created by rkrde on 14-02-2017.
 */

public class UserState {

    public static boolean isUserLoggedIn(Context context){

        Uri mUri = MyProvider.UserCredentialsLists.CONTENT_URI;
        String mProjection[]={UserCredentialsColumn.ACCESS_TOKEN};
        String mSelection=UserCredentialsColumn.ACTIVE_STATE+"=?";
        String mSelectionArgs[]={"1"};
        Cursor cursor =context.getContentResolver().query(mUri,mProjection,mSelection,mSelectionArgs,null);
        boolean loggedIn = false;
        if(cursor!=null) {
            loggedIn = (cursor.moveToFirst());
            cursor.close();
        }
        return loggedIn;
    }

    public static String getAuthToken(Context context){

        String token = null;
        Uri mUri = MyProvider.UserCredentialsLists.CONTENT_URI;
        String mProjection[]={UserCredentialsColumn.ACCESS_TOKEN};
        String mSelection=UserCredentialsColumn.ACTIVE_STATE+"=?";
        String mSelectionArgs[]={"1"};
        Cursor cursor = context.getContentResolver().query(mUri,mProjection,mSelection,mSelectionArgs,null);

        if(null==cursor){
            return token;
        }
        if(cursor.moveToFirst())
        {
            do{
                token = cursor.getString(cursor.getColumnIndex(UserCredentialsColumn.ACCESS_TOKEN));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return token;
    }
}
