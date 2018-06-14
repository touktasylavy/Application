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
import la.nuol.fen.application.dao.LibraryDAO;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private Context context;
    private List<LibraryDAO> libraryDAO;
    private Activity activity;

    public LibraryAdapter(Context context, List<LibraryDAO> libraryDAO, Activity activity) {
        this.context = context;
        this.libraryDAO = libraryDAO;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library_room, parent, false);
        itemView.setClickable(true);
        return new LibraryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LibraryAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvCount.setText(position + 1 + "");
            holder.tvClassName.setText(libraryDAO.get(position).getRoomName());
            holder.tvFloor.setText(libraryDAO.get(position).getFloor());
            holder.tvBuildingName.setText(libraryDAO.get(position).getBuilding());
            holder.tvRoomType.setText(libraryDAO.get(position).getRoomType());
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (libraryDAO == null) return 0;
        return libraryDAO.size();
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
