package one.blanke.firezemissiles;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MainActivity extends AppCompatActivity implements TargetListFragment.TargetListItemClicked {

    public static final String TARGET_ID = "";

    private boolean isDualPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDualPanel = findViewById(R.id.fragment_container) == null;

        // Hvis vi ikke har adgang til 2 fragments skal vi tilføje en fragment til vores container
        if(!isDualPanel) {

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            TargetListFragment fragment = new TargetListFragment();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean supercall = super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return supercall;
    }

    @Override
    public void onTargetListItemClick(int ID) {

        // Hvis vi har adagng til vores detalje fragment kan vi nøjes med at opdatere navnet. Hvis ikke skal vi lave en ny
        // fragment og tilføje den i stedet for vores liste
        if(isDualPanel) {
            DetailsFragment fragment = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details_fragment);
            fragment.updateTarget(ID);
        } else {
            DetailsFragment fragment = new DetailsFragment();
            Bundle b = new Bundle();
            b.putInt(TARGET_ID, ID);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            fragment.setArguments(b);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
