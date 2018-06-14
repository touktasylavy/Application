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

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {

    private Context context;
    private List<BuildingDAO> buildingDAOS;
    private Activity activity;

    public BuildingAdapter(Context context, List<BuildingDAO> buildingDAOS, Activity activity) {
        this.context = context;
        this.buildingDAOS = buildingDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BuildingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_building, parent, false);
        itemView.setClickable(true);
        return new BuildingAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuildingAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvBuildingName.setText(buildingDAOS.get(position).getBuildingName());
            holder.tvDept.setText(buildingDAOS.get(position).getLocation());
            holder.btnViewMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = buildingDAOS.get(position).getLocation();
                    String lat = buildingDAOS.get(position).getLatitude();
                    String lng = buildingDAOS.get(position).getLongtitude();
                    String map = lat + "," + lng;
                    String query = lat + "," + lng + "(" + name + ")";
                    Uri location = Uri.parse("geo:" + map + "?q=" + Uri.encode(query));
                    Intent intentViewMap = new Intent(Intent.ACTION_VIEW, location);
                    activity.startActivity(intentViewMap);
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (buildingDAOS == null) return 0;
        return buildingDAOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCount, tvBuildingName, tvDept;
        Button btnViewMap;
        public ViewHolder(View itemView) {
            super(itemView);

            tvCount =  itemView.findViewById(R.id.tv_count);
            tvBuildingName = itemView.findViewById(R.id.tv_building_name);
            tvDept = itemView.findViewById(R.id.tv_dept);
            btnViewMap = itemView.findViewById(R.id.btn_view_map);
        }



        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Click View Map ", Toast.LENGTH_LONG).show();
        }
    }
}
