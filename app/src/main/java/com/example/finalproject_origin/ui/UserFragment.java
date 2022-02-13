package com.example.finalproject_origin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.finalproject_origin.MainActivity;
import com.example.finalproject_origin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity mainActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserCenter.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private Button btn_user_regist, btn_user_login;
    private EditText et_user_id, et_user_password, et_user_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_user, container, false);
        btn_user_regist = v.findViewById(R.id.btn_user_regist);
        btn_user_login = v.findViewById(R.id.btn_user_login);

        et_user_id = v.findViewById(R.id.et_user_id);
        et_user_password = v.findViewById(R.id.et_user_password);

        btn_user_login.setOnClickListener(new DataCheck());
        btn_user_regist.setOnClickListener(new DataCheck());


        mainActivity = (MainActivity) getActivity();

        return v;
    }

    class DataCheck implements View.OnClickListener {
        private Intent intent;

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.btn_user_login:

                    String id = et_user_id.getText().toString();
                    String password = et_user_password.getText().toString();
                    if(mainActivity.firebaseLoginCheck(id, password)){

                        mainActivity.showSendingFragment();
                    } else {
                        mainActivity.makeToast("The id or password was wrong!!!!");
                    }

                    break;

                case R.id.btn_user_regist:

                    mainActivity.showRegistFragment();

                    break;
            }
        }
    }


}