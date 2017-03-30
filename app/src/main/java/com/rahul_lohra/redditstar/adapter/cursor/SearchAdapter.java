package com.rahul_lohra.redditstar.adapter.cursor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahul_lohra.redditstar.R;
import com.rahul_lohra.redditstar.viewHolder.SearchViewHolder;

import java.util.List;

/**
 * Created by rkrde on 29-01-2017.
 */

public class SearchAdapter extends RecyclerView.Adapter{

    private Context context;
    private final String TAG = SearchAdapter.class.getSimpleName();
    private List<String> stringList;
    public interface ISearchAdapter{
        void onRvTextViewClick(String string);
    }
    private ISearchAdapter iSearchAdapter;

    public SearchAdapter(Context context,List<String> stringList,ISearchAdapter iSearchAdapter) {
        this.context = context;
        this.stringList = stringList;
        this.iSearchAdapter = iSearchAdapter;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder)holder;
        String text = stringList.get(position);
        searchViewHolder.tv.setText(text);

        searchViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSearchAdapter.onRvTextViewClick(stringList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(context,LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


}
