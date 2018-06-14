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
import la.nuol.fen.application.dao.ProgramBachelorDAO;

public class ProgramBachelorAdapter extends RecyclerView.Adapter<ProgramBachelorAdapter.ViewHolder> {

    private Context context;
    private List<ProgramBachelorDAO> programBachelorDAOS;
    private Activity activity;

    public ProgramBachelorAdapter(Context context, List<ProgramBachelorDAO> programBachelorDAOS, Activity activity) {
        this.context = context;
        this.programBachelorDAOS = programBachelorDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProgramBachelorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_bachelor, parent, false);
        itemView.setClickable(true);
        return new ProgramBachelorAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramBachelorAdapter.ViewHolder holder, int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvProgramName.setText("ຫລັກສູດ: " + programBachelorDAOS.get(position).getProgrammeName());
            holder.tvPeriod.setText( "ລະບົບ: " + programBachelorDAOS.get(position).getPeriod());
            holder.tvDeptName.setText(programBachelorDAOS.get(position).getDepartmentName());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (programBachelorDAOS == null) return 0;
        return programBachelorDAOS.size();
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
