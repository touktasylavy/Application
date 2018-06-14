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
import la.nuol.fen.application.dao.ClassroomDAO;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder> {

    private Context context;
    private List<ClassroomDAO> classroomDAOS;
    private Activity activity;

    public ClassroomAdapter(Context context, List<ClassroomDAO> classroomDAOS, Activity activity) {
        this.context = context;
        this.classroomDAOS = classroomDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ClassroomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classroom, parent, false);
        itemView.setClickable(true);
        return new ClassroomAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassroomAdapter.ViewHolder holder, final int position) {
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
