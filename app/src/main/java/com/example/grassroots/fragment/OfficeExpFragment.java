package com.example.grassroots.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.grassroots.CongressOverviewVM;
import com.example.grassroots.R;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpResult;
import com.example.grassroots.model.ProPublica.OfficeExpenses.OfficeExpenseResponse;
import com.example.grassroots.network.ProPublica.OfficeExpense.OfficeExpensePresenter;
import com.example.grassroots.recyclerview.ExpenseAdapter;

import java.util.ArrayList;
import java.util.List;

public class OfficeExpFragment extends Fragment {

    private CongressOverviewVM congressOverviewVM;

    private static final String KEY_YEAR = "Year" ;
    private static final String KEY_QUARTER = "Quarter" ;

    private String member_id;
//    private String year;
    private String quarter;

    public static final String TAG = "HERE";

    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerView;

    public OfficeExpFragment() {
    }

    public static OfficeExpFragment newInstance(String year, String quarter){
        OfficeExpFragment officeExpFragment = new OfficeExpFragment();
        Bundle officeArgs = new Bundle();
        officeArgs.putString(KEY_YEAR, year);
        officeArgs.putString(KEY_QUARTER, quarter);
        officeExpFragment.setArguments(officeArgs);
        return officeExpFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            quarter = (getArguments().getString(KEY_QUARTER)).substring(0,1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_office_exp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle officeArgs = getArguments();

        recyclerView = view.findViewById(R.id.rv_oe);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        congressOverviewVM = ViewModelProviders.of((FragmentActivity) requireContext()).get(CongressOverviewVM.class);
        member_id = congressOverviewVM.getCongressMember().getId();
        int year = Integer.parseInt(officeArgs.getString(KEY_YEAR));
        int quarterToInt = Integer.parseInt(quarter);

        OfficeExpensePresenter expensePresenter = new OfficeExpensePresenter(new OfficeExpUIListener() {
            @Override
            public void updateOfficeExpUI(OfficeExpenseResponse officeExpenseResponse) {
                expenseAdapter = new ExpenseAdapter(officeExpenseResponse.getResults());
                recyclerView.setAdapter(expenseAdapter);
                expenseAdapter.notifyDataSetChanged();
            }
        });

        expensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
                member_id, year, quarterToInt);
        Log.d(TAG, "ARGS FOR NETWORK CALL: " + member_id + " " + year + " " + quarterToInt);


//        if(year == 2009 && quarterToInt >= 3) {
//            expensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
//                    member_id, year, quarterToInt);
//            Log.d("OFFICEEXPFRAG", "ARGS FOR NETWORK CALL: " + " " + member_id + " " + year + " " + quarterToInt);
//        } else if(year >= 2010 && year <= 2017 && quarterToInt >= 1 && quarterToInt <= 4){
//            expensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
//                    member_id, year, quarterToInt);
//            Log.d("OFFICEEXPFRAG", "ARGS FOR NETWORK CALL: " + " " + member_id + " " + year + " " + quarterToInt);
//        } else {
//            insertInfoNotAvailableFragment();
//            Toast.makeText(requireContext(), "Information is not yet available.", Toast.LENGTH_LONG).show();
//        }


    }

//        expensePresenter.expenseNetworkCall(requireContext().getString(R.string.ProPublica_Congress_API_Key),
//                member_id, year, quarterToInt);
//        Log.d(TAG, "ARGS FOR NETWORK CALL: " + member_id + " " + year + " " + quarterToInt);
//
//    }

    private void insertInfoNotAvailableFragment() {
        InfoNotAvailableFragment infoNotAvailableFragment = new InfoNotAvailableFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.container_spinner, infoNotAvailableFragment).addToBackStack(null).commit();

    }
}
