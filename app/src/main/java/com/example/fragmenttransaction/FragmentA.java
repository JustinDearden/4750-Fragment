package com.example.fragmenttransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;


public class FragmentA extends ListFragment implements AdapterView.OnItemClickListener {
    private FragmentAListener listener;

    //When an item is clicked it calls the listener that sends data to FragmentB
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Obtains the location names from strings.xml
        String[] locationList = getActivity().getResources().getStringArray(R.array.location_list);

        //Sends the location data to FragmentB
        listener.onInputASent(locationList[position]);
    }

    //Implemented in MainActivity - to send data
    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    //Inflate the layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        return v;
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    public void updateEditText(CharSequence newText) {
        //Implement a function here to send  data from fragmentB to fragmentA - this was a test on my part to send it both ways
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //Bind the String-Array into the listview of the fragment
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.location_list, android.R.layout.simple_list_item_1);

        setListAdapter(arrayAdapter);

        getListView().setOnItemClickListener(this);
    }

}