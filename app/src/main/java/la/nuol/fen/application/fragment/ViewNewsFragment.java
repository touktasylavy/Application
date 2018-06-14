package la.nuol.fen.application.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.News;
import la.nuol.fen.application.dao.NewsDAO;
import la.nuol.fen.application.utils.FunctionService;

@SuppressWarnings("unused")
public class ViewNewsFragment extends Fragment{

    ImageView imvShow;
    TextView tvPostBy, tvDatePost, tvTypeNews, tvTitle, tvDes;
    NewsDAO dao;

    public ViewNewsFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ViewNewsFragment newInstance(NewsDAO dao) {
        ViewNewsFragment fragment = new ViewNewsFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_news, container, false);
        setHasOptionsMenu(true);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        imvShow = rootView.findViewById(R.id.imvShow);
        tvPostBy = rootView.findViewById(R.id.tv_post_by);
        tvDatePost = rootView.findViewById(R.id.tv_date_post);
        tvTypeNews = rootView.findViewById(R.id.tv_type_news);
        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDes = rootView.findViewById(R.id.tv_des);

        tvPostBy.setText("ໂດຍ: " + dao.getPostedBy());
        tvDatePost.setText("ວັນທີ: " + FunctionService.formatDate2(dao.getDatePost()));
        tvTypeNews.setText("ປະເພດຂ່າວ: " + dao.getNewsType());
        tvTitle.setText(dao.getNewsTitle());
        tvDes.setText(dao.getDescription());
        Glide.with(getActivity())
                .load(dao.getImage())
                .placeholder(R.drawable.item_up)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imvShow);
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }


}
