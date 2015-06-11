package cl.exec.android.dev.exec.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import cl.exec.android.dev.exec.R;
import cl.exec.android.dev.exec.adapters.ListMantisAdapter;
import cl.exec.android.dev.exec.classes.Mantis;
import cl.exec.android.dev.exec.data.GetDataAsyncTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public MainFragment() {}

    ArrayList<Mantis> arrayMantis;
    ListView listViewMantis;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        arrayMantis = new ArrayList<Mantis>();
        listViewMantis = (ListView) rootView.findViewById(R.id.listViewMantis);
        ListMantisAdapter listMantisAdapter = new ListMantisAdapter(
                getActivity().getApplicationContext(),arrayMantis);
        listViewMantis.setAdapter(listMantisAdapter);




        return rootView;
    }


    @Override
    public void onStart(){
        super.onStart();
        new GetDataAsyncTask().execute();
    }

}
