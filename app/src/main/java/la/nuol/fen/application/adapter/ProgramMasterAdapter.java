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
import la.nuol.fen.application.dao.ProgramMasterDAO;

public class ProgramMasterAdapter extends RecyclerView.Adapter<ProgramMasterAdapter.ViewHolder> {

    private Context context;
    private static List<ProgramMasterDAO> programMasterDao;
    private Activity activity;

    public ProgramMasterAdapter(Context context, List<ProgramMasterDAO> programMasterDao, Activity activity) {
        this.context = context;
        this.programMasterDao = ProgramMasterAdapter.programMasterDao;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProgramMasterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_master, parent, false);
        itemView.setClickable(true);
        return new ProgramMasterAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramMasterAdapter.ViewHolder holder, int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvProgramName.setText("ຫລັກສູດ: " + programMasterDao.get(position).getProgrammeName());
            holder.tvPeriod.setText( "ລະບົບ: " + programMasterDao.get(position).getPeriod());
            holder.tvDeptName.setText(programMasterDao.get(position).getDepartmentName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (programMasterDao == null) return 0;
        return programMasterDao.size();
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
