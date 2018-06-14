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

import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.DepartmentDAO;
import la.nuol.fen.application.dao.DivisionDAO;

public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.ViewHolder> {

    private Context context;
    private List<DivisionDAO> divisionDAOS;
    private Activity activity;

    public DivisionAdapter(Context context, List<DivisionDAO> divisionDAOS, Activity activity) {
        this.context = context;
        this.divisionDAOS = divisionDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DivisionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_division, parent, false);
        itemView.setClickable(true);
        return new DivisionAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DivisionAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvDivisionName.setText(divisionDAOS.get(position).getFullName() + " (" + divisionDAOS.get(position).getShortName() + ")");
            holder.tvTelephone.setText(divisionDAOS.get(position).getTelephone());
            holder.tvEmail.setText(divisionDAOS.get(position).getEmail());
            holder.tvBuildingName.setText(divisionDAOS.get(position).getBuilding());
            holder.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = divisionDAOS.get(position).getTelephone();
                    Intent intentCall = new Intent();
                    intentCall.setAction(Intent.ACTION_DIAL);
                    intentCall.setData(Uri.parse("tel:" + number));
                    activity.startActivity(Intent.createChooser(intentCall, "Call To"));
                }
            });

            holder.btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = divisionDAOS.get(position).getEmail();
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
        if (divisionDAOS == null) return 0;
        return divisionDAOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCount, tvDivisionName, tvTelephone, tvEmail, tvBuildingName;
        Button btnCall, btnEmail;
        public ViewHolder(View itemView) {
            super(itemView);

            tvCount =  itemView.findViewById(R.id.tv_count);
            tvDivisionName = itemView.findViewById(R.id.tv_division_name);
            tvTelephone = itemView.findViewById(R.id.tv_telephone);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvBuildingName = itemView.findViewById(R.id.tv_building_name);
            btnCall = itemView.findViewById(R.id.btn_call);
            btnEmail = itemView.findViewById(R.id.btn_email);
        }
    }
}
