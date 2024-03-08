package com.example.mobilka3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 0);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,
                            Fragment_1.class, bundle)
                    .commit();
        }
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true).add(R.id.fragment_container_view, Fragment_3.class, null)
                .commit();
        getSupportFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String
                                                 requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey");
            }
        });
    }
}