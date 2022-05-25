package com.example.allinone.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.allinone.R;


public class SortDialogFragment extends DialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.search_sort_layout, null);
        v.findViewById(R.id.popular_sort).setOnClickListener(radioButtonClickListener);
        v.findViewById(R.id.date_sort).setOnClickListener(radioButtonClickListener);
        v.findViewById(R.id.reposts_sort).setOnClickListener(radioButtonClickListener);

        return v;

    }


    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean checked = ((RadioButton) v).isChecked();
            RadioButton rb = (RadioButton) v;

            ((SearchFragment) getParentFragment()).changeSortBtnText((String) rb.getText());
            switch (rb.getId()) {
                case R.id.popular_sort:
                    if (checked)
                        ((SearchFragment) getParentFragment()).sort_items("popular");
                    break;
                case R.id.date_sort:
                    if (checked)
                        ((SearchFragment) getParentFragment()).sort_items("date");
                    break;
                case R.id.reposts_sort:
                    if (checked)

                        break;


                default:
                    break;
            }
        }
    };
}

