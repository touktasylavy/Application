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
import la.nuol.fen.application.adapter.ProgramContinueAdapter;
import la.nuol.fen.application.dao.ProgramContinue;
import la.nuol.fen.application.dao.ProgramContinueDAO;
import la.nuol.fen.application.manager.HttpManager;
import la.nuol.fen.application.utils.ContactUrls;
import la.nuol.fen.application.utils.FunctionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unused")
public class fragmentProgramContinue extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recycleViewProgramContinue;
    private RecyclerView.LayoutManager layoutManager;
    private ProgramContinueAdapter programContinueAdapter;
    private List<ProgramContinueDAO> programContinueDAOS;
    private ProgramContinue programContinue;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvShowNull;

    public fragmentProgramContinue() {
        super();
    }

    @SuppressWarnings("unused")
    public static fragmentProgramContinue newInstance() {
        fragmentProgramContinue fragment = new fragmentProgramContinue();
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
        View rootView = inflater.inflate(R.layout.fragment_program_continue, container, false);
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
        recycleViewProgramContinue = rootView.findViewById(R.id.recycle);
        tvShowNull = rootView.findViewById(R.id.tv_show_null);
        tvShowNull.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(getContext());
        recycleViewProgramContinue.setHasFixedSize(true);
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
            Call<ProgramContinue> call = HttpManager.getInstance().getApiManager().getProgramContinue();
            call.enqueue(new Callback<ProgramContinue>() {
                @Override
                public void onResponse(Call<ProgramContinue> call, Response<ProgramContinue> response) {
                    if (response.isSuccessful()) {
                        programContinue = response.body();
                        swipeRefreshLayout.setRefreshing(false);
                        programContinueDAOS = programContinue.getContinue();
                        if (programContinue.getContinue().size() > 0) {
                            tvShowNull.setVisibility(View.GONE);
                            programContinueAdapter = new ProgramContinueAdapter(getContext(), programContinueDAOS, getActivity());
                            recycleViewProgramContinue.setAdapter(programContinueAdapter);
                            recycleViewProgramContinue.setLayoutManager(layoutManager);
                            programContinueAdapter.notifyDataSetChanged();
                        }else {
                            programContinueDAOS.clear();
                            programContinueAdapter = new ProgramContinueAdapter(getContext(), programContinueDAOS, getActivity());
                            recycleViewProgramContinue.setAdapter(programContinueAdapter);
                            recycleViewProgramContinue.setLayoutManager(layoutManager);
                            programContinueAdapter.notifyDataSetChanged();
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
                public void onFailure(Call<ProgramContinue> call, Throwable t) {
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
