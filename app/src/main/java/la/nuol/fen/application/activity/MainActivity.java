package la.nuol.fen.application.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import la.nuol.fen.application.R;
import la.nuol.fen.application.dao.NewsDAO;
import la.nuol.fen.application.fragment.BuildingRoom.FragmentBuildingRoom;
import la.nuol.fen.application.fragment.DeptDivision.FragmentDeptDivision;
import la.nuol.fen.application.fragment.LocationFragment;
import la.nuol.fen.application.fragment.MainFragment;
import la.nuol.fen.application.fragment.NewsFragment;
import la.nuol.fen.application.fragment.Programs.FragmentPrograms;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public static ActionBarDrawerToggle actionBarDrawerToggle;

    private TextView username;
    private TextView displayName;
    private ImageView profile;
    private RelativeLayout navigationHeader;

    private MenuItem previousMenuItem;
    private MenuItem mainMenuItem,
            deptDivisionMenuItem,
            roomMenuItem,
            programMenuItem,
            locationMenuItem,
            newsMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializes();

    }

    private void initializes() {
        bindView();
        setUpView();
        attachFragment();
    }

    private void findModMenuById() {
        mainMenuItem = navigationView.getMenu().getItem(0);
        deptDivisionMenuItem = navigationView.getMenu().getItem(1);
        roomMenuItem = navigationView.getMenu().getItem(2);
        programMenuItem = navigationView.getMenu().getItem(3);
        locationMenuItem = navigationView.getMenu().getItem(4);
        newsMenuItem = navigationView.getMenu().getItem(5);
    }

    private void setUpView() {
        setSupportActionBar(toolbar);
        findModMenuById();
        navigationViewItemClick();
    }

    private void navigationViewItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                if (previousMenuItem != null) previousMenuItem.setChecked(false);
//                menuItem.setChecked(true);
                int selectedMenu = menuItem.getItemId();

                if (selectedMenu == R.id.drawer_main) {
                    mainMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, MainFragment.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.main_name));
                    previousMenuItem = mainMenuItem;

                } else if (selectedMenu == R.id.drawer_dept_division) {
                    deptDivisionMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, FragmentDeptDivision.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.dept_division));
                    previousMenuItem = deptDivisionMenuItem;

                } else if (selectedMenu == R.id.drawer_room) {
                    roomMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, FragmentBuildingRoom.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.room));
                    previousMenuItem = roomMenuItem;

                }else if(selectedMenu == R.id.drawer_program){
                    programMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, FragmentPrograms.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.program));
                    previousMenuItem = programMenuItem;

                }else if(selectedMenu == R.id.drawer_location){
                    locationMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, LocationFragment.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.location));
                    previousMenuItem = locationMenuItem;

                } else if (selectedMenu == R.id.drawer_news) {
                    newsMenuItem.setChecked(true);
                    getSupportFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, NewsFragment.newInstance());
                    fragmentTransaction.commit();
                    toolbar.setTitle(getString(R.string.news));
                    previousMenuItem = newsMenuItem;

                } else {
                    Toast.makeText(getApplicationContext(), "bug ?", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                actionBarDrawerToggle.setDrawerIndicatorEnabled(getSupportFragmentManager().getBackStackEntryCount() == 0);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void bindView() {
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationHeader = navigationView.findViewById(R.id.navigation_head);
        View header = navigationView.getHeaderView(0);
        username =  header.findViewById(R.id.username);
        displayName = header.findViewById(R.id.display_name);
        profile = header.findViewById(R.id.profile_image_circle);
    }//Inflate View's

    private void attachFragment() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, MainFragment.newInstance())
                .commit();
        toolbar.setTitle(getString(R.string.main_name));
        previousMenuItem = mainMenuItem;
        mainMenuItem.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
//        getSupportFragmentManager().popBackStack();
//        actionBarDrawerToggle.syncState();
//        return true;
        return super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to exit!",
                    Toast.LENGTH_LONG).show();
        } else {

            super.onBackPressed();
        }
    } // end of onBackPressed
}
