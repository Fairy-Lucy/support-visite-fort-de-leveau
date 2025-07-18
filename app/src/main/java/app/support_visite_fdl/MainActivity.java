package app.support_visite_fdl;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("TEST_R", "layout id: " + R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        if (savedInstanceState == null) {
            selectedFragment = new LieuFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, selectedFragment)
                    .commit();
        }

        navView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_lieu) {
                selectedFragment = new LieuFragment();
            } else if (itemId == R.id.navigation_chronologie) {
                selectedFragment = new ChronologieFragment();
            } else if (itemId == R.id.navigation_mot_cle) {
                selectedFragment = new MotCleFragment();
            } else if (itemId == R.id.navigation_doc) {
                selectedFragment = new DocumentationFragment();
            } else {
                return false;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sync) {
            DataSynchronizer synchronizer = new DataSynchronizer(MainActivity.this);
            synchronizer.synchronizeLieux();
            synchronizer.synchronizeDocuments();
            synchronizer.synchronizeImages();
            synchronizer.synchronizeThemes();
            synchronizer.synchronizeMotsCles();
            Log.d("SYNC", "Synchronisation lancée !");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
