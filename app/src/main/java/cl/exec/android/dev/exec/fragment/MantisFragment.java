package cl.exec.android.dev.exec.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.exec.android.dev.exec.R;

/**
 * Created by fran on 04-06-15.
 */
public class MantisFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mantis, container, false);

        return rootView;
    }


    @Override
    public void onStart(){
        super.onStart();
        //new GetDataAsyncTask().execute();
    }

}
