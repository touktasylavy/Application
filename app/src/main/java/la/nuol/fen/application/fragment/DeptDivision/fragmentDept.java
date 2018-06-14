package la.nuol.fen.application.fragment.DeptDivision;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.adapter.BuildingAdapter;
import la.nuol.fen.application.adapter.DeptAdapter;
import la.nuol.fen.application.dao.BuildingDAO;
import la.nuol.fen.application.dao.Buildings;
import la.nuol.fen.application.dao.DepartmentDAO;
import la.nuol.fen.application.dao.Departments;
import la.nuol.fen.application.manager.HttpManager;
import la.nuol.fen.application.utils.ContactUrls;
import la.nuol.fen.application.utils.FunctionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unused")
public class fragmentDept extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recycleViewDept;
    private RecyclerView.LayoutManager layoutManager;
    private DeptAdapter deptAdapter;
    private List<DepartmentDAO> departmentDAOS;
    private Departments departments;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvShowNull;

    public fragmentDept() {
        super();
    }

    @SuppressWarnings("unused")
    public static fragmentDept newInstance() {
        fragmentDept fragment = new fragmentDept();
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
        View rootView = inflater.inflate(R.layout.fragment_dept, container, false);
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
        recycleViewDept = rootView.findViewById(R.id.recycle);
        tvShowNull = rootView.findViewById(R.id.tv_show_null);
        tvShowNull.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(getContext());
        recycleViewDept.setHasFixedSize(true);
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


    @Override
    public void onRefresh() {
        loadData();
    }

    private void loadData() {
        if (FunctionService.isOnline(getContext(), getActivity())) {
            swipeRefreshLayout.setRefreshing(true);
            Call<Departments> call = HttpManager.getInstance().getApiManager().getDepartment();
            call.enqueue(new Callback<Departments>() {
                @Override
                public void onResponse(Call<Departments> call, Response<Departments> response) {
                    if (response.isSuccessful()) {
                        departments = response.body();
                        swipeRefreshLayout.setRefreshing(false);
                        departmentDAOS = departments.getDepartBuild();
                        if (departments.getDepartBuild().size() > 0) {
                            tvShowNull.setVisibility(View.GONE);
                            deptAdapter = new DeptAdapter(getContext(),  departmentDAOS, getActivity());
                            recycleViewDept.setAdapter(deptAdapter);
                            recycleViewDept.setLayoutManager(layoutManager);
                            deptAdapter.notifyDataSetChanged();
                        }else {
                            departmentDAOS.clear();
                            deptAdapter = new DeptAdapter(getContext(),  departmentDAOS, getActivity());
                            recycleViewDept.setAdapter(deptAdapter);
                            recycleViewDept.setLayoutManager(layoutManager);
                            deptAdapter.notifyDataSetChanged();
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
                public void onFailure(Call<Departments> call, Throwable t) {
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

}
