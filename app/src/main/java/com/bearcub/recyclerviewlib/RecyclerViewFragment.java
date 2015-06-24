package com.bearcub.recyclerviewlib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcub.recyclerviewlibrary.AbsRecyclerViewFragment;
import com.bearcub.recyclerviewlibrary.ExtRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Home on 6/10/2015.
 */
public class RecyclerViewFragment extends AbsRecyclerViewFragment {
    private RecyclerView recyclerView;
    private ExtRecyclerViewAdapter adapter; // created field to access in overridden methods, can change to a field ref to list

    public RecyclerViewFragment(){}

    public static RecyclerViewFragment newInstance(){
        return new RecyclerViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recyclerView =(RecyclerView)view.findViewById(R.id.recycler_view);
        adapter = new ExtRecyclerViewAdapter(getActivity(), getRecyclerViewData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(new ExtTouchListener(getActivity(), recyclerView, new ExtClickListener() {
            @Override
            public void onClick(View view, int position) {
                mCallBack.onTouchItemSelected(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "omg this is working!", Toast.LENGTH_SHORT).show();
            }
        }));

        return view;
    }

    @Override
    public List<AbsItem> getRecyclerViewData() {
        List<AbsItem> list = new ArrayList<>();
        String[] items = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh"};

        for (int i = 0; i < items.length; i++){
            AbsItem item = new AbsItem();
            item.label = items[i];
            list.add(item);
        }
        return list;
    }

    @Override
    public int getViewHolderLayoutId() {
        return R.layout.item_recycler_view;
    }

    @Override
    public int getTextViewId() {
        return R.id.textView;
    }

    @Override
    public void implementOnBindVH(AbsRecyclerViewFragment.ExtRecyclerViewAdapter.MyViewHolder viewHolder, int position) {
        AbsItem currentItem = adapter.list.get(position);
        viewHolder.label.setText(currentItem.label);
    }

    @Override
    public int implementGetItemCount() {
        return adapter.list.size();
    }
}
