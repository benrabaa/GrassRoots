package com.example.grassroots.fragment.congress;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.grassroots.R;
import com.example.grassroots.model.CivicInfo.CivicInfoModel;
import com.example.grassroots.model.CivicInfo.ElectedRepresentatives;
import com.example.grassroots.model.ProPublica.Members.CongressOverviewVM;
import com.example.grassroots.network.CivicInfo.CivicInfoPresenter;
import com.example.grassroots.utils.LocalRepsUIListener;

public class OverviewFragmentv2 extends Fragment {

    private View rootView;
    private String fecID;
    private TabContactListener tabContactListener;
    private ElectedRepresentatives electedRepresentatives;

    public OverviewFragmentv2() {
    }

    public static OverviewFragmentv2 newInstance() {
        return new OverviewFragmentv2();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TabContactListener) {
            tabContactListener = (TabContactListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement TabContactListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_overview_design_v3, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
    }

    private void initialize() {
        CongressOverviewVM congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);

        ImageView img_profile_rep = rootView.findViewById(R.id.img_profile_rep);
        TextView txt_title_name = rootView.findViewById(R.id.txt_title_name);
        TextView txt_party_state = rootView.findViewById(R.id.txt_party_state);
        TextView txt_fec_id = rootView.findViewById(R.id.txt_fec_id_number);
        TextView txt_year = rootView.findViewById(R.id.txt_year);

//        String rep_name = congressOverviewVM.getCongressMember().getFirst_name() + " " + congressOverviewVM.getCongressMember().getLast_name();
//
//        CivicInfoPresenter civicInfoPresenter = new CivicInfoPresenter(new LocalRepsUIListener() {
//            @Override
//            public void updateUI(CivicInfoModel civicInfoModel) {

//                if (rep_name.equals(civicInfoModel.getElectedRepresentatives().get(0).getName())) {
//                    Glide.with(rootView.getContext())
//                            .load(electedRepresentatives.getPhotoUrl())
//                            .centerCrop()
//                            .into(img_profile_rep);
//                }
//            }
//        });

//        civicInfoPresenter.networkCall(this.getString(R.string.Civic_Info_API_Key));

        txt_title_name.setText(
                String.format("%s %s %s", congressOverviewVM.getCongressMember().getShort_title(), congressOverviewVM.getCongressMember().getFirst_name(), congressOverviewVM.getCongressMember().getLast_name()));

        txt_party_state.setText(
                String.format("%s, %s", congressOverviewVM.getCongressMember().getParty(), congressOverviewVM.getCongressMember().getState()));

        fecID = congressOverviewVM.getCongressMember().getFec_candidate_id();
        txt_fec_id.setText(fecID);

        txt_year.setText(congressOverviewVM.getCongressMember().getNext_election());


        ImageView twitter = rootView.findViewById(R.id.acct_twitter);
        ImageView facebook = rootView.findViewById(R.id.acct_facebook);
        ImageView youtube = rootView.findViewById(R.id.acct_youtube);
        ImageView gmail = rootView.findViewById(R.id.acct_gmail);
        ImageView website = rootView.findViewById(R.id.website);
        ImageView phone = rootView.findViewById(R.id.phone);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: TWITTER");
                if (tabContactListener != null) {
                    tabContactListener.openTwitter(congressOverviewVM.getCongressMember().getTwitter_account());
                    Log.d("OFRAG2", "onClick: TWITTER" + congressOverviewVM.getCongressMember().getTwitter_account());

                }
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: FACEBOOK");
                if (tabContactListener != null) {
                    tabContactListener.openFacebook(congressOverviewVM.getCongressMember().getFacebook_account());
                    Log.d("OFRAG2", "onClick: FACEBOOK" + congressOverviewVM.getCongressMember().getFacebook_account());

                }
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: YOUTUBE");

                if (tabContactListener != null) {
                    tabContactListener.openYoutube(congressOverviewVM.getCongressMember().getYoutube_account());
                    Log.d("OFRAG2", "onClick: YOUTUBE" + congressOverviewVM.getCongressMember().getYoutube_account());

                }

            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: GMAIL");
                if (tabContactListener != null) {
                    tabContactListener.openEmail(congressOverviewVM.getCongressMember().getContact_form());
                    Log.d("OFRAG2", "onClick: GMAIL" + " " + congressOverviewVM.getCongressMember().getContact_form());

                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: WEBSITE");

                if (tabContactListener != null) {
                    tabContactListener.openWebsite(congressOverviewVM.getCongressMember().getUrl());
                    Log.d("OFRAG2", "onClick: WEBSITE" + congressOverviewVM.getCongressMember().getUrl());

                }

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OFRAG2", "onClick: PHONE");

                if (tabContactListener != null) {
                    tabContactListener.openPhont(congressOverviewVM.getCongressMember().getPhone());
                    Log.d("OFRAG2", "onClick: PHONE" + congressOverviewVM.getCongressMember().getPhone());

                }
            }
        });

    }
}