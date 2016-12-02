package com.example.news.MenuFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.news.R;

import butterknife.ButterKnife;

/**
 * Created by 86409 on 2016/12/2.
 */

public class ForgetPasswordFragment extends Fragment{

    EditText etEmail;
    Button btnConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
