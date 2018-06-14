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
import android.widget.Toast;

import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.OfficeDAO;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeAdapter.ViewHolder> {

    private Context context;
    private List<OfficeDAO> classroomDAOS;
    private Activity activity;

    public OfficeAdapter(Context context, List<OfficeDAO> classroomDAOS, Activity activity) {
        this.context = context;
        this.classroomDAOS = classroomDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public OfficeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office, parent, false);
        itemView.setClickable(true);
        return new OfficeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OfficeAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvClassName.setText(classroomDAOS.get(position).getRoomName());
            holder.tvFloor.setText(classroomDAOS.get(position).getFloor());
            holder.tvBuildingName.setText(classroomDAOS.get(position).getBuilding());
            holder.tvRoomType.setText(classroomDAOS.get(position).getRoomType());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (classroomDAOS == null) return 0;
        return classroomDAOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCount, tvClassName, tvFloor, tvBuildingName, tvRoomType;
        public ViewHolder(View itemView) {
            super(itemView);

            tvCount =  itemView.findViewById(R.id.tv_count);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvFloor = itemView.findViewById(R.id.tv_floor);
            tvBuildingName = itemView.findViewById(R.id.tv_building_name);
            tvRoomType = itemView.findViewById(R.id.tv_room_type);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Click View Map ", Toast.LENGTH_LONG).show();
        }
    }
}
