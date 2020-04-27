package com.example.fragmenttransaction;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FragmentB extends Fragment {
    //Implement this to send data from FragmentB to FragmentA - it'll go both ways
    private FragmentBListener listener;
    private TextView resultsFrag;

    private static final String KEY_TITLE = "title_key";

    //Implemented in MainActivity - to send data
    public interface FragmentBListener {
        void onInputBSent(CharSequence input);
    }

    //Inflates the layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        resultsFrag = v.findViewById(R.id.tvFragmentB);

        return v;
    }

    public void updateEditText(CharSequence newText) {

        String newOption = newText.toString();

        Resources res = getResources();
        String[] locationDesc = res.getStringArray(R.array.location_description);

        //Checks the location sent from FragmentA then pull the description from strings.xml
        if (newOption.equals("Ambassador Bridge")) {
            resultsFrag.setText(locationDesc[0]);
        } else if (newOption.equals("Adventure Bay Water Park")) {
            resultsFrag.setText(locationDesc[1]);
        } else if (newOption.equals("Caesars Windsor")) {
            resultsFrag.setText(locationDesc[2]);
        } else if (newOption.equals("Dieppe Gardens")) {
            resultsFrag.setText(locationDesc[3]);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener) {
            listener = (FragmentBListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //Save the state to the bundle data
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_TITLE, resultsFrag.getText().toString());
    }

    //Restores the view when the screen orientation is changed
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            String itemTo = savedInstanceState.getString(KEY_TITLE);
            resultsFrag.setText(itemTo);
        } else {
            //Put an error or anything here is need be
        }
    }

}