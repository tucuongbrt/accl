package pt.lsts.accl.systemlist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import pt.lsts.accl.bus.AcclBus;
import pt.lsts.accl.event.EventSystemDisconnected;
import pt.lsts.accl.event.EventSystemVisible;
import pt.lsts.accl.sys.Sys;


/**
 * A placeholder fragment containing a simple view.
 */
public class SystemListFragment extends Fragment {

    public static final String TAG ="SystemListFragment";

    View v;
    ListView systemListListView=null;
    ArrayAdapter<String> arrayAdapter;

    public SystemListFragment() {
        AcclBus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_system_list, container, false);
        initListView();

        return v;
    }

    @Subscribe
    public void on(EventSystemVisible event) {
        Log.v(TAG, event.toString());
        final EventSystemVisible eventFinal = event;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.add(eventFinal.getSys().getName());
            }
        });
    }

    @Subscribe
    public void on(EventSystemDisconnected event) {
        Log.v(TAG, event.toString());
        final EventSystemDisconnected eventFinal = event;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.remove(eventFinal.getSys().getName());
            }
        });
    }

    public void initListView(){
        systemListListView = (ListView) v.findViewById(R.id.systemListListView);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        systemListListView.setAdapter(arrayAdapter);
    }

}