package com.example.gamers_chat.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gamers_chat.R;
import com.example.gamers_chat.activities.MainActivity;
import com.example.gamers_chat.models.GameModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public gameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static gameFragment newInstance(String param1, String param2) {
        gameFragment fragment = new gameFragment();
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
        MainActivity mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        TextView gameName = view.findViewById(R.id.text_title);
        TextView gamePublisher = view.findViewById(R.id.text_publisher);
        TextView gamePlatform = view.findViewById(R.id.text_platform);
        TextView gameDesc = view.findViewById(R.id.text_description);
        ImageView gameImage = view.findViewById(R.id.image_banner);
        TextView gameGenre = view.findViewById(R.id.text_genre);
        TextView gameReleaseDate = view.findViewById(R.id.text_release_date);
        TextView gameUrl = view.findViewById(R.id.text_game_url);
        TextView gameDeveloper = view.findViewById(R.id.text_developer);

        gameName.setText(mainActivity.currentGame.title);
        gamePublisher.setText(mainActivity.currentGame.publisher);
        gamePlatform.setText(mainActivity.currentGame.platform);
        gameDesc.setText(mainActivity.currentGame.short_description);
        Glide.with(view.findViewById(R.id.game_profile).getContext())
                .load(mainActivity.currentGame.thumbnail)
                .into(gameImage);

        gameGenre.setText(mainActivity.currentGame.genre);
        gameReleaseDate.setText(mainActivity.currentGame.release_date);
        gameUrl.setText(mainActivity.currentGame.game_url);
        gameDeveloper.setText(mainActivity.currentGame.developer);

        return view;
    }
}