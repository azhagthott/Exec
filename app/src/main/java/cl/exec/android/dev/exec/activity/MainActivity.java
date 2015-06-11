package cl.exec.android.dev.exec.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import cl.exec.android.dev.exec.R;
import cl.exec.android.dev.exec.fragment.MainFragment;
import cl.exec.android.dev.exec.fragment.MantisFragment;
import cl.exec.android.dev.exec.fragment.NavigationDrawerFragment;



public class MainActivity extends Activity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    private final String LOG_TAG = MainFragment.class.getSimpleName();
    private CharSequence mTitle;
    private String actionBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new MainFragment();
                onSectionAttached(position);
                break;
            case 1:
                fragment = new MantisFragment();
                onSectionAttached(position);
                break;
            default:
                fragment = new MainFragment();
                onSectionAttached(position);
                break;
        }

        if(fragment!=null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    public void onSectionAttached(int position) {
        switch (position) {

            case 0:
                mTitle = getString(R.string.title_section_main);
                actionBarColor = "#2B547E";
                Log.v(LOG_TAG, "title_section_main");
                break;

            case 1:
                mTitle = getString(R.string.title_section_mantis);
                actionBarColor = "#E56717";
                Log.v(LOG_TAG, "title_section_mantis");
                break;

            case 2:
                mTitle = getString(R.string.title_section_statistics);
                actionBarColor = "#8467D7";
                Log.v(LOG_TAG, "title_section_statistics");
                break;

            default:
                mTitle = getString(R.string.title_section_main);
                actionBarColor = "#2B547E";
                Log.v(LOG_TAG, "title_section_main");
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setDrawerProfileInfo() {
        if (mNavigationDrawerFragment != null) {
            mNavigationDrawerFragment.setDrawerProfileInfo();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mNavigationDrawerFragment != null) {
            mNavigationDrawerFragment.setDrawerProfileInfo();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}
}
