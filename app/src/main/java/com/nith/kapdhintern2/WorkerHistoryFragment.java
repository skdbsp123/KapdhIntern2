package com.nith.kapdhintern2;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.nith.kapdhintern2.GetWorkerHistoryList.workerHistoryItemArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerHistoryFragment extends Fragment {

    static RecyclerView workerHistoryRecyclerView;
    static WorkerHistoryAdapter workerHistoryAdapter;
    ProgressDialog pd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkerHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkerHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkerHistoryFragment newInstance(String param1, String param2) {
        WorkerHistoryFragment fragment = new WorkerHistoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_worker_history, container, false);
        workerHistoryRecyclerView = view.findViewById(R.id.workerHistoryRecyclerView);
        pd = new ProgressDialog(getActivity());
        pd.show();
        pd.setMessage("Wait");
        callRetrieveData();
        return view;
    }

    // setting up recycler view
    public void setWorkerHistoryRecyclerView() {
        if (pd.isShowing()) {
            pd.cancel();
        }
        workerHistoryRecyclerView.setHasFixedSize(true);
        workerHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        workerHistoryAdapter = new WorkerHistoryAdapter (getContext(), workerHistoryItemArrayList);
        workerHistoryRecyclerView.setAdapter(workerHistoryAdapter);
    }

    public void callRetrieveData() {
        GetWorkerHistoryList.getDataFromDatabase(new GetWorkerHistoryList.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<WorkerHistoryItem> orderItemArrayList, ArrayList<String> dataKeys) {
                setWorkerHistoryRecyclerView();
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {
            }
        });
    }
}