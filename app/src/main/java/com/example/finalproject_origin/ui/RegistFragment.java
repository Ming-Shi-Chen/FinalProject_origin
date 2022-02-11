package com.example.finalproject_origin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalproject_origin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistFragment newInstance(String param1, String param2) {
        RegistFragment fragment = new RegistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    private EditText et_regist_id, et_regist_name, et_regist_email;
    private Button btn_regist_cancel, btn_regist_regist;
    private FirebaseDatabase firebaseControl;
    private DatabaseReference dataReference;
    private ArrayList<Map<String, String>> customerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_regist, container, false);

        firebaseControl = FirebaseDatabase.getInstance();
        dataReference = firebaseControl.getReference().child("customer");


        et_regist_id = v.findViewById(R.id.et_regist_id);
        et_regist_name = v.findViewById(R.id.et_regist_name);
        et_regist_email = v.findViewById(R.id.et_regist_email);

        btn_regist_cancel = v.findViewById(R.id.btn_regist_cancel);
        btn_regist_regist = v.findViewById(R.id.btn_regist_regist);


        btn_regist_cancel.setOnClickListener(view -> {
            et_regist_id.setText("");
            et_regist_name.setText("");
        });

        customerList = new ArrayList<>();



        return inflater.inflate(R.layout.fragment_regist, container, false);
    }
}