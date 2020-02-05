package com.example.doit.wapproject2_test1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;


import com.example.doit.wapproject2_test1.db.ConsumeDAO;
import com.example.doit.wapproject2_test1.model.ConsmListAdapter;
import com.example.doit.wapproject2_test1.model.Consume;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FragmentPage2 extends Fragment implements OnItemClickListener,
        OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "consume_list";

    Activity activity;
    ListView consumeListView;
    ArrayList<Consume> consumes;

    ConsmListAdapter consumeListAdapter;
    ConsumeDAO consumeDAO;

    private GetConsmTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        consumeDAO = new ConsumeDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findViewsById(view);

        task = new GetConsmTask(activity);
        task.execute((Void) null);

        consumeListView.setOnItemClickListener(this);
        consumeListView.setOnItemLongClickListener(this);
        // Employee e = employeeDAO.getEmployee(1);
        // Log.d("employee e", e.toString());
        return view;
    }

    private void findViewsById(View view) {
        consumeListView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.app_name);
        //getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> list, View arg1, int position,
                            long arg3) {
        Consume consume = (Consume) list.getItemAtPosition(position);

        if (consume != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedConsume", consume);
            //**CustomEmpDialogFragment customEmpDialogFragment = new CustomEmpDialogFragment();
            //**customEmpDialogFragment.setArguments(arguments);
            //**customEmpDialogFragment.show(getFragmentManager(),
            //**        CustomEmpDialogFragment.ARG_ITEM_ID);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long arg3) {
        Consume consume = (Consume) parent.getItemAtPosition(position);

        // Use AsyncTask to delete from database
        //**employeeDAO.delete(employee);
        //**employeeListAdapter.remove(employee);
        return true;
    }


    public class GetConsmTask extends AsyncTask<Void, Void, ArrayList<Consume>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetConsmTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Consume> doInBackground(Void... arg0) {
            ArrayList<Consume> consumeList = consumeDAO.getConsumes();
            return consumeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Consume> consmList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("consumes", consmList.toString());
                consumes = consmList;
                if (consmList != null) {
                    if (consmList.size() != 0) {
                        consumeListAdapter = new ConsmListAdapter(activity,
                                consmList);
                        consumeListView.setAdapter(consumeListAdapter);
                    } else {
                        Toast.makeText(activity, "No Consume Records",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }

    /*
     * This method is invoked from MainActivity onFinishDialog() method. It is
     * called from CustomEmpDialogFragment when an employee record is updated.
     * This is used for communicating between fragments.
     */
    public void updateView() {
        task = new GetConsmTask(activity);
        task.execute((Void) null);
    }
}