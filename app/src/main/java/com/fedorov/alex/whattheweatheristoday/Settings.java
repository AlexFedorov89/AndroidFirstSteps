package com.fedorov.alex.whattheweatheristoday;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Settings extends Fragment implements PublishGetter {
    private static final String CITY = "city";

    private Publisher publisher = new Publisher();

    private String mCity;

    public Settings() {
        // Required empty public constructor
    }

    public static Settings newInstance(String city) {
        Settings fragment = new Settings();
        fragment.setArguments(getBundle(city));

        return fragment;
    }

    private static Bundle getBundle(String city) {
        Bundle args = new Bundle();
        args.putString(CITY, city);

        return args;
    }

    @Override
    public void onAttach(Context context) {
        // Подпишем activity.
        publisher.subscribe((Observer) getActivity());

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCity = getArguments().getString(CITY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView cityView = view.findViewById(R.id.cityInput);

        if (mCity != null) {
            cityView.setText(mCity);
        }

        return view;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}
