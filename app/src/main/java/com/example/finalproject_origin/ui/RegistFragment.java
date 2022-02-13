package com.example.finalproject_origin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.finalproject_origin.MainActivity;
import com.example.finalproject_origin.R;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
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


    private EditText et_regist_id, et_regist_name, et_regist_email, et_regist_password, et_regist_password2;
    private Button btn_regist_cancel, btn_regist_regist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_regist, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        et_regist_id = v.findViewById(R.id.et_regist_id);
        et_regist_password = v.findViewById(R.id.et_regist_password);
        et_regist_password2 = v.findViewById(R.id.et_regist_password2);
        et_regist_name = v.findViewById(R.id.et_regist_name);
        et_regist_email = v.findViewById(R.id.et_regist_email);

        btn_regist_cancel = v.findViewById(R.id.btn_regist_cancel);
        btn_regist_regist = v.findViewById(R.id.btn_regist_regist);

        btn_regist_cancel.setOnClickListener(view -> {
            et_regist_id.setText("");
            et_regist_name.setText("");
            et_regist_email.setText("");
            et_regist_password.setText("");
            et_regist_password2.setText("");
        });



        btn_regist_regist.setOnClickListener(view -> {
            if ( et_regist_id.length() == 0 ||
            et_regist_password.length() == 0 ||
            et_regist_password2.length() == 0 ||
            et_regist_name.length() == 0 ||
            et_regist_email.length() == 0){

                mainActivity.makeToast("Please input all the data");

            } else {

                String id = et_regist_id.getText().toString();
                String password = et_regist_password.getText().toString();
                String password2 = et_regist_password2.getText().toString();
                String name = et_regist_name.getText().toString();
                String email = et_regist_email.getText().toString();

                if ( ! password.equals(password2) ){
                    mainActivity.makeToast("Your password is not equal , please retype");
                    return;
                }

                if (mainActivity.customerFirebaseDataCheck("id",id) == true){

                    mainActivity.makeToast("your Id have been created , please chose another one");
                    return;
                }

                Map<String, String> data = new HashMap<>();
                data.put("id", id);
                data.put("name",name);
                data.put("password",password);
                data.put("email",email);
                Task<Void> result = mainActivity.customerFirebaseDataSet(id,data);
                result.addOnSuccessListener(unused -> {
                   mainActivity.makeToast("Registering ok~~~~~");
                });

                result.addOnFailureListener(e -> {
                    mainActivity.makeToast("Register failed. Try again ");
                });

            }

        });


        return v;
    }
}