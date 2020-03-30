package com.test.banner.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.test.banner.R;
import com.test.banner.adapter.MyRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlankFragment extends Fragment {

    public static Fragment newInstance() {
        return new BlankFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test,null);
    }


}
