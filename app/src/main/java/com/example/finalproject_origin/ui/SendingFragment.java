package com.example.finalproject_origin.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalproject_origin.MainActivity;
import com.example.finalproject_origin.R;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SendingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserCenter.
     */
    // TODO: Rename and change types and number of parameters
    public static SendingFragment newInstance(String param1, String param2) {
        SendingFragment fragment = new SendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SendingFragment() {
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

    private EditText et_sending_title, et_sending_content;
    private TextView tv_sending_userName;
    private Button bt_sending_clear, bt_sending_send;
    String title, content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_sending, container, false);


        et_sending_title = v.findViewById(R.id.et_sending_title);
        et_sending_content = v.findViewById(R.id.et_sending_content);

        tv_sending_userName = v.findViewById(R.id.tv_sending_userName);

        MainActivity mainActivity = (MainActivity) getActivity();

        String userid = mainActivity.loginId;
        String userName = mainActivity.loginName;

        tv_sending_userName.setText(userName);

        bt_sending_clear = v.findViewById(R.id.bt_sending_clear);
        bt_sending_send = v.findViewById(R.id.bt_sending_send);


        bt_sending_clear.setOnClickListener(view->{
            et_sending_title.setText("");
            et_sending_content.setText("");
        });

        bt_sending_send.setOnClickListener(view ->{
            title = et_sending_title.getText().toString();
            content = et_sending_content.getText().toString();

            if( title.length() == 0 || content.length() == 0){
                mainActivity.makeToast("Please input the article's title & content!!!");

            } else {
                Map<String,String> data = new HashMap<>();
                data.put("title",title);
                data.put("content",content);
                Task<Void> result = mainActivity.articleFirebaseDataSet(data);
                result.addOnSuccessListener(unused -> {
                    mainActivity.makeToast("Sending ok~~~~~");
                });

                result.addOnFailureListener(e -> {
                    mainActivity.makeToast("Sending failed. Try again ");
                });
            }
        });

        return v;
    }
}