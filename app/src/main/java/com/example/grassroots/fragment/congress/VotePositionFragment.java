package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.Members.CongressMember;
import com.example.grassroots.model.ProPublica.Members.CongressResponse;
import com.example.grassroots.model.ProPublica.VotePositions.VotePositionResponse;
import com.example.grassroots.network.ProPublica.Members.CongressPresenter;
import com.example.grassroots.network.ProPublica.VotePositions.VotePostitionPresenter;
import com.example.grassroots.recyclerview.CongressAdapter;
import com.example.grassroots.recyclerview.VotePositionAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VotePositionFragment extends Fragment {


    public static final String TAG = "HERE";

    private VotePositionAdapter votePositionAdapter;
    private RecyclerView recyclerView;

    public VotePositionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vote_position, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_vote_positions);
        //Toolbar toolbar = view.findViewById(R.id.congress_directory_toolbar);
        //((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        VotePostitionPresenter votePostitionPresenter = new VotePostitionPresenter(new VotePositionUIListener() {
            @Override
            public void updateUI(VotePositionResponse votePositionResponse) {
                Log.d(TAG, "updateUI: " + votePositionResponse.getStatus().toString());
                votePositionAdapter = new VotePositionAdapter(votePositionResponse.getResults());
                recyclerView.setAdapter(votePositionAdapter);
                votePositionAdapter.notifyDataSetChanged();
            }
        });

        votePostitionPresenter.networkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key));

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


    }

}
