package la.nuol.fen.application.fragment.Programs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import la.nuol.fen.application.R;
import la.nuol.fen.application.fragment.DeptDivision.fragmentDept;
import la.nuol.fen.application.fragment.DeptDivision.fragmentDivision;
import la.nuol.fen.application.utils.ContactUrls;


@SuppressWarnings("unused")
public class FragmentPrograms extends Fragment {

    private String strToken;

    public FragmentPrograms() {
        super();
    }

    @SuppressWarnings("unused")
    public static FragmentPrograms newInstance() {
        FragmentPrograms fragment = new FragmentPrograms();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program, container, false);
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
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragmentProgramBachelor.newInstance();
                    case 1:
                        return fragmentProgramContinue.newInstance();
                    case 2:
                        return fragmentProgramMaster.newInstance();

                    default:
                        return null;
                } // end of switch
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return ContactUrls.FRAGMENT_TAB_TITLE.PROGRAM_BACHELOR;
                    case 1:
                        return ContactUrls.FRAGMENT_TAB_TITLE.PROGRAM_CONTINUE;
                    case 2:
                        return ContactUrls.FRAGMENT_TAB_TITLE.PROGRAM_MASTER;

                    default:
                        return null;
                }

            }
        });
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
//        Toast.makeText(getActivity(), "OnStart", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
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
