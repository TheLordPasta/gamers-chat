package com.example.gamers_chat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.example.gamers_chat.R;
import com.example.gamers_chat.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gameSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gameSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public gameSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gameSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static gameSearch newInstance(String param1, String param2) {
        gameSearch fragment = new gameSearch();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_game, container, false);
        String[] options = {"name", "genre", "developer", "year", "platform", "publisher"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, options);
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapterSpinner);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.CreateGameList(view);

        return view;
    }
}