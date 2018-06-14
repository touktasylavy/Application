package la.nuol.fen.application.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.adapter.BuildingAdapter;
import la.nuol.fen.application.dao.BuildingDAO;
import la.nuol.fen.application.dao.Buildings;
import la.nuol.fen.application.manager.HttpManager;
import la.nuol.fen.application.utils.ContactUrls;
import la.nuol.fen.application.utils.FunctionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("unused")
public class fragmentBuilding extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recycleViewBuilding;
    private RecyclerView.LayoutManager layoutManager;
    private BuildingAdapter buildingAdapter;
    private List<BuildingDAO> buildingDAOS;
    private Buildings buildings;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvShowNull;

    public fragmentBuilding() {
        super();
    }

    @SuppressWarnings("unused")
    public static fragmentBuilding newInstance() {
        fragmentBuilding fragment = new fragmentBuilding();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_building, container, false);
        setHasOptionsMenu(true);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.color_status);
        recycleViewBuilding = rootView.findViewById(R.id.recycle);
        tvShowNull = rootView.findViewById(R.id.tv_show_null);
        tvShowNull.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(getContext());
        recycleViewBuilding.setHasFixedSize(true);
        loadData();
        swipeRefreshLayout.setOnRefreshListener(this);
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        loadData();
                                    }
                                }
        );
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    private void loadData() {
        if (FunctionService.isOnline(getContext(), getActivity())) {
            swipeRefreshLayout.setRefreshing(true);
            Call<Buildings> call = HttpManager.getInstance().getApiManager().getBuilding();
            call.enqueue(new Callback<Buildings>() {
                @Override
                public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                    if (response.isSuccessful()) {
                        buildings = response.body();
                        swipeRefreshLayout.setRefreshing(false);
                        buildingDAOS = buildings.getBuilding();
                        if (buildings.getBuilding().size() > 0) {
                            tvShowNull.setVisibility(View.GONE);
                            buildingAdapter = new BuildingAdapter(getContext(),  buildingDAOS, getActivity());
                            recycleViewBuilding.setAdapter(buildingAdapter);
                            recycleViewBuilding.setLayoutManager(layoutManager);
                            buildingAdapter.notifyDataSetChanged();
                        }else {
                            buildingDAOS.clear();
                            buildingAdapter = new BuildingAdapter(getContext(),  buildingDAOS, getActivity());
                            recycleViewBuilding.setAdapter(buildingAdapter);
                            recycleViewBuilding.setLayoutManager(layoutManager);
                            buildingAdapter.notifyDataSetChanged();
                            tvShowNull.setVisibility(View.VISIBLE);
                        }
                    } else {
                        try {
                            swipeRefreshLayout.setRefreshing(false);
                            Log.d(ContactUrls.LOG_TAG, "Error: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Buildings> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d(ContactUrls.LOG_TAG, "Load Fail: " + t.toString());
                }
            });
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Error Connecting");
            builder.setMessage("Check your internet Connection");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            builder.show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }
}
