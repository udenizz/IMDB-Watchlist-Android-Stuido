package com.example.c1811070_imdb_watchlist.ACTIVITY.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.c1811070_imdb_watchlist.R;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class WatchListFragment extends Fragment {

    public WatchListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_watch_list, container, false);



    }
}