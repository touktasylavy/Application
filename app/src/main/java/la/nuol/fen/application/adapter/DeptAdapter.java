package la.nuol.fen.application.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.BuildingDAO;
import la.nuol.fen.application.dao.DepartmentDAO;

public class DeptAdapter extends RecyclerView.Adapter<DeptAdapter.ViewHolder> {

    private Context context;
    private List<DepartmentDAO> departmentDAOS;
    private Activity activity;

    public DeptAdapter(Context context, List<DepartmentDAO> departmentDAOS, Activity activity) {
        this.context = context;
        this.departmentDAOS = departmentDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DeptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department, parent, false);
        itemView.setClickable(true);
        return new DeptAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeptAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvDeptName.setText(departmentDAOS.get(position).getFullName() + " (" + departmentDAOS.get(position).getShortName() + ")");
            holder.tvTelephone.setText(departmentDAOS.get(position).getTelephone());
            holder.tvEmail.setText(departmentDAOS.get(position).getEmail());
            holder.tvBuildingName.setText(departmentDAOS.get(position).getBuilding());
            holder.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = departmentDAOS.get(position).getTelephone();
                    Intent intentCall = new Intent();
                    intentCall.setAction(Intent.ACTION_DIAL);
                    intentCall.setData(Uri.parse("tel:" + number));
                    activity.startActivity(Intent.createChooser(intentCall, "Call To"));
                }
            });

            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = departmentDAOS.get(position).getEmail();
                    Intent emailIntent = new Intent();
                    emailIntent.setAction(Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Send Email");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Faculty of Engineering");
                    activity.startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (departmentDAOS == null) return 0;
        return departmentDAOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCount, tvDeptName, tvTelephone, tvEmail, tvBuildingName;
        Button btnCall, btnEmail;
        public ViewHolder(View itemView) {
            super(itemView);

            tvCount =  itemView.findViewById(R.id.tv_count);
            tvDeptName = itemView.findViewById(R.id.tv_dept_name);
            tvTelephone = itemView.findViewById(R.id.tv_telephone);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvBuildingName = itemView.findViewById(R.id.tv_building_name);
            btnCall = itemView.findViewById(R.id.btn_call);
            btnEmail = itemView.findViewById(R.id.btn_email);
        }
    }
}
