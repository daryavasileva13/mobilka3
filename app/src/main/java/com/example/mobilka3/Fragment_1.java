package com.example.mobilka3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_1 extends Fragment {
    private int defaultValue = 0;
    private static final String TAG = "MyApp";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("some_int", defaultValue);
        }
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        TextView textView1 = view.findViewById(R.id.text_sections);
        TextView textView = view.findViewById(R.id.section_text);

        getChildFragmentManager().setFragmentResultListener("reqKeySection", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundKeySection");
                textView.setText(result);
            }
        });


        Fragment_2 fragment2 = new Fragment_2();
        getChildFragmentManager().beginTransaction()
                .add(R.id.fragment_ch, fragment2)
                .addToBackStack(null)
                .commit();


        EditText nameEditText = view.findViewById(R.id.editText_Name);
        Button button = view.findViewById(R.id.button_Name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                String Name = nameEditText.getText().toString();
                result.putString("bundleKeyName", Name);
                getParentFragmentManager().setFragmentResult("requestKeyName",
                        result);
            }
        });


        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView");
        return view;
    }
}