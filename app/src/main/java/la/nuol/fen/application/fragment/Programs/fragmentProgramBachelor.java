package la.nuol.fen.application.fragment.Programs;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import la.nuol.fen.application.adapter.ProgramBachelorAdapter;
import la.nuol.fen.application.dao.ProgramBachelor;
import la.nuol.fen.application.dao.ProgramBachelorDAO;
import la.nuol.fen.application.manager.HttpManager;
import la.nuol.fen.application.utils.ContactUrls;
import la.nuol.fen.application.utils.FunctionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unused")
public class fragmentProgramBachelor extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recycleViewProgramBachelor;
    private RecyclerView.LayoutManager layoutManager;
    private ProgramBachelorAdapter programBachelorAdapter;
    private List<ProgramBachelorDAO> programBachelorDAOS;
    private ProgramBachelor programBachelor;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvShowNull;

    public fragmentProgramBachelor() {
        super();
    }

    @SuppressWarnings("unused")
    public static fragmentProgramBachelor newInstance() {
        fragmentProgramBachelor fragment = new fragmentProgramBachelor();
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
        View rootView = inflater.inflate(R.layout.fragment_program_bachelor, container, false);
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
        recycleViewProgramBachelor = rootView.findViewById(R.id.recycle);
        tvShowNull = rootView.findViewById(R.id.tv_show_null);
        tvShowNull.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(getContext());
        recycleViewProgramBachelor.setHasFixedSize(true);
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
            Call<ProgramBachelor> call = HttpManager.getInstance().getApiManager().getProgramBachelor();
            call.enqueue(new Callback<ProgramBachelor>() {
                @Override
                public void onResponse(Call<ProgramBachelor> call, Response<ProgramBachelor> response) {
                    if (response.isSuccessful()) {
                        programBachelor = response.body();
                        swipeRefreshLayout.setRefreshing(false);
                        programBachelorDAOS = programBachelor.getBachelor();
                        if (programBachelor.getBachelor().size() > 0) {
                            tvShowNull.setVisibility(View.GONE);
                            programBachelorAdapter = new ProgramBachelorAdapter(getContext(),  programBachelorDAOS, getActivity());
                            recycleViewProgramBachelor.setAdapter(programBachelorAdapter);
                            recycleViewProgramBachelor.setLayoutManager(layoutManager);
                            programBachelorAdapter.notifyDataSetChanged();
                        }else {
                            programBachelorDAOS.clear();
                            programBachelorAdapter = new ProgramBachelorAdapter(getContext(),  programBachelorDAOS, getActivity());
                            recycleViewProgramBachelor.setAdapter(programBachelorAdapter);
                            recycleViewProgramBachelor.setLayoutManager(layoutManager);
                            programBachelorAdapter.notifyDataSetChanged();
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
                public void onFailure(Call<ProgramBachelor> call, Throwable t) {
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
