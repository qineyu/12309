package com.qinyue.monitor.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qinyue.monitor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/25
 * 描述:
 **/
public class WorkFragment extends Fragment {
    Unbinder unbinder;
    MainActivity mainActivity;
    public WorkFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();
        initOnCliclek();
        return view;
    }

    private void initOnCliclek() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
