package la.nuol.fen.application.fragment.BuildingRoom;

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
public class FragmentBuildingRoom extends Fragment {

    private String strToken;

    public FragmentBuildingRoom() {
        super();
    }

    @SuppressWarnings("unused")
    public static FragmentBuildingRoom newInstance() {
        FragmentBuildingRoom fragment = new FragmentBuildingRoom();
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
        View rootView = inflater.inflate(R.layout.fragment_building_room, container, false);
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
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragmentBuilding.newInstance();
                    case 1:
                        return fragmentClassRoom.newInstance();
                    case 2:
                        return fragmentOffice.newInstance();
                    case 3:
                        return fragmentMeetingRoom.newInstance();
                    case 4:
                        return fragmentLibraryRoom.newInstance();
                    case 5:
                        return fragmentLabRoom.newInstance();
                    default:
                        return null;
                } // end of switch
            }

            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return ContactUrls.FRAGMENT_TAB_TITLE.BUILDING;
                    case 1:
                        return ContactUrls.FRAGMENT_TAB_TITLE.CLASSROOM;
                    case 2:
                        return ContactUrls.FRAGMENT_TAB_TITLE.OFFICE;
                    case 3:
                        return ContactUrls.FRAGMENT_TAB_TITLE.MEETING_ROOM;
                    case 4:
                        return ContactUrls.FRAGMENT_TAB_TITLE.LIBRARY_ROOM;
                    case 5:
                        return ContactUrls.FRAGMENT_TAB_TITLE.LAB_ROOM;
                    default:
                        return null;
                }

            }
        });
        // Set Tabs inside Toolbar
        TabLayout tabs = rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
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
