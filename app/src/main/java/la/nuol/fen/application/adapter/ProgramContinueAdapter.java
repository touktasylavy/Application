package la.nuol.fen.application.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.ProgramContinueDAO;

public class ProgramContinueAdapter extends RecyclerView.Adapter<ProgramContinueAdapter.ViewHolder> {

    private Context context;
    private List<ProgramContinueDAO> programContinueDao;
    private Activity activity;

    public ProgramContinueAdapter(Context context, List<ProgramContinueDAO> programContinueDao, Activity activity) {
        this.context = context;
        this.programContinueDao = programContinueDao;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProgramContinueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_continue, parent, false);
        itemView.setClickable(true);
        return new ProgramContinueAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramContinueAdapter.ViewHolder holder, int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvProgramName.setText("ຫລັກສູດ: " + programContinueDao.get(position).getProgrammeName());
            holder.tvPeriod.setText( "ລະບົບ: " + programContinueDao.get(position).getPeriod());
            holder.tvDeptName.setText(programContinueDao.get(position).getDepartmentName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (programContinueDao == null) return 0;
        return programContinueDao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCount, tvProgramName, tvPeriod, tvDeptName;
        public ViewHolder(View itemView) {
            super(itemView);

            tvCount =  itemView.findViewById(R.id.tv_count);
            tvProgramName = itemView.findViewById(R.id.tv_program_name);
            tvPeriod = itemView.findViewById(R.id.tv_period);
            tvDeptName = itemView.findViewById(R.id.tv_dept_name);
        }

    }
}
