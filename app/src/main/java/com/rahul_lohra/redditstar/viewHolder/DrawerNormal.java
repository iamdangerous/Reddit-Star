package com.rahul_lohra.redditstar.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rahul_lohra.redditstar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rkrde on 22-01-2017.
 */

public class DrawerNormal extends RecyclerView.ViewHolder {

    @BindView(R.id.tv)
    public TextView tv;

    public DrawerNormal(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
