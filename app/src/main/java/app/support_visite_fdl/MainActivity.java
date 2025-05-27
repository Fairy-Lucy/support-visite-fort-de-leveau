package app.support_visite_fdl;

import android.os.Bundle;
import android.util.Log;
// It's good practice to import specific classes rather than wildcard if not too many
// import com.google.android.material.bottomnavigation.BottomNavigationView; // Already have this
import androidx.annotation.NonNull; // For @NonNull annotation if you use it in lambda
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
// For Navigation Component, if you were planning to use it with NavController later
// import androidx.navigation.NavController;
// import androidx.navigation.Navigation;
// import androidx.navigation.ui.AppBarConfiguration;
// import androidx.navigation.ui.NavigationUI;

// You are using View Binding, but not referencing `binding` variable in this code.
// If you intend to use View Binding for `BottomNavigationView`, you'd do:
// ActivityMainBinding binding;
// binding = ActivityMainBinding.inflate(getLayoutInflater());
// setContentView(binding.getRoot());
// BottomNavigationView navView = binding.navView;
// Otherwise, the `findViewById` is fine.

import com.google.android.material.bottomnavigation.BottomNavigationView;
// R class is usually available without explicit import if package name is correct in AndroidManifest
// import app.support_visite_fdl.R; // Generally not needed

public class MainActivity extends AppCompatActivity {

    // It's better to initialize selectedFragment to null or handle the default case more explicitly
    private Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure you call setContentView first!

        Log.d("TEST_R", "layout id: " + R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set default fragment
        if (savedInstanceState == null) { // Only set default if not recreating from a saved state
            selectedFragment = new LieuFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, selectedFragment)
                    .commit();
        }


        navView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Get the ID

            if (itemId == R.id.navigation_lieu) {
                selectedFragment = new LieuFragment();
            } else if (itemId == R.id.navigation_chronologie) {
                selectedFragment = new ChronologieFragment();
            } else if (itemId == R.id.navigation_mot_cle) {
                selectedFragment = new MotCleFragment();
            } else if (itemId == R.id.navigation_doc) {
                selectedFragment = new DocumentationFragment();
            } else {
                // Optionally handle unknown item ID or do nothing
                return false; // Indicate that the item selection was not handled
            }

            // Only proceed with fragment transaction if a fragment was selected
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
            }
            return true; // Indicate that the item selection was handled
        });

        // If you want a default selection highlighted in BottomNavigationView
        // ensure the menu item for LieuFragment is checked if it's the default.
        // You might need to do this after setting the listener, or by setting
        // `navView.setSelectedItemId(R.id.navigation_lieu);`
        // if you want the listener to also trigger for the default.
        // However, the fragment transaction for the default is already handled above.
    }
}