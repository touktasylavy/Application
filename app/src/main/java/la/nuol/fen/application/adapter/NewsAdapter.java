package la.nuol.fen.application.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import la.nuol.fen.application.R;
import la.nuol.fen.application.activity.NewsActivity;
import la.nuol.fen.application.dao.NewsDAO;
import la.nuol.fen.application.utils.FunctionService;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<NewsDAO> newsDAOS;
    private Activity activity;

    public NewsAdapter(Context context, List<NewsDAO> newsDAOS, Activity activity) {
        this.context = context;
        this.newsDAOS = newsDAOS;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        itemView.setClickable(true);
        return new NewsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, final int position) {
        try {
            holder.tvTitle.setText(newsDAOS.get(position).getNewsTitle());
            holder.tvDatePost.setText(FunctionService.formatDate2(newsDAOS.get(position).getDatePost()));
            holder.tvDes.setText(newsDAOS.get(position).getDescription().toString());
            Glide.with(activity)
                    .load(newsDAOS.get(position).getImage())
                    .placeholder(R.drawable.item_up)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imvShow);
            holder.cardViewClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDAO dao = newsDAOS.get(position);
                    Intent intent = new Intent(context, NewsActivity.class);
                    intent.putExtra("dao", dao);
                    activity.startActivity(intent);
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (newsDAOS == null) return 0;
        return newsDAOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewClick;
        ImageView imvShow;
        TextView tvTitle, tvDatePost, tvDes;
        public ViewHolder(View itemView) {
            super(itemView);
            imvShow =  itemView.findViewById(R.id.imvShow);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDatePost = itemView.findViewById(R.id.tv_date_post);
            tvDes = itemView.findViewById(R.id.tv_des);
            cardViewClick = itemView.findViewById(R.id.cardViewClick);
        }

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
