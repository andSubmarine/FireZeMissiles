package one.blanke.firezemissiles;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import one.blanke.firezemissiles.model.Target;
import one.blanke.firezemissiles.model.TargetGenerator;

/**
 * Created by andre_000 on 20-04-2017.
 */

public class TargetListFragment extends Fragment {

    public interface TargetListItemClicked {
        public void onTargetListItemClick(int guestId);
    }
    private TargetListItemClicked targetListListener;

    private Target marked;
    private List<Target> targets;
    private List<Target> filteredList;

    // Adapter til vores liste
    private TargetListAdapter adapter;

    public TargetListFragment() {

        TargetGenerator.initialize();
        targets = TargetGenerator.getTargetList();
        filteredList = new ArrayList<Target>();
        filteredList.addAll(targets);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frament_target_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Når dette fragment placeres i en aktivitet, tilføjer vi aktiviteten som lytter,
        // så vi kan fortælle når en bruger klikker på et target i listen
        targetListListener = (TargetListItemClicked) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView targetListView = (ListView) getActivity().findViewById(R.id.target_list);
        adapter = new TargetListAdapter();
        targetListView.setAdapter(adapter);

        final EditText searchField = (EditText) getActivity().findViewById(R.id.search_field);
        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                filteredList.clear();
                String searchString = searchField.getText().toString().toLowerCase();

                for(Target target : targets) {
                    if(target.getName().toLowerCase().contains(searchString)) {
                        filteredList.add(target);
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });

        registerForContextMenu(targetListView);

        targetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Target target = filteredList.get(position);
                targetListListener.onTargetListItemClick(target.getID());
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.target_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Target target = TargetGenerator.getTarget(((AdapterContextMenuInfo) item.getMenuInfo()).position);
        switch (item.getItemId()) {
            case R.id.mark:
                marked = target;
                marked.setStatus(Status.ALIVE);
                return true;
            case R.id.send_missile:
                if (marked == null) {
                    Toast.makeText(getActivity(), "Launched missile towards " + target.getShortName(), Toast.LENGTH_SHORT).show();
                    target.setStrength(-200);
                    target.setStatus(Status.HIT_BY_MISSILE);
                } else {
                    if (!marked.equals(target)) {
                        if (marked.getStatus() != Status.DESTROYED) {
                            marked.setStatus(Status.LAUNCHING_MISSILE);
                            Toast.makeText(getActivity(), marked.getShortName() +
                                    " launched missile towards " + target.getShortName(),
                                    Toast.LENGTH_SHORT).show();
                            target.setStrength(-200);
                            target.setStatus(Status.HIT_BY_MISSILE);
                        } else {
                            Toast.makeText(getActivity(), marked.getShortName()+
                                            " has been destroyed and cannot launch missiles",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Cannot launch against itself",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            case R.id.provoke:
                Toast.makeText(getActivity(),target.getLeader()+" did NOT like that...",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // Liste adapter for vores gæste liste
    class TargetListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public Object getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(view == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                view = inflater.inflate(R.layout.target_item, null);
            }

            TextView name = (TextView) view.findViewById(R.id.target_name);
            TextView description = (TextView) view.findViewById(R.id.target_description);

            Target target = filteredList.get(position);

            if(target != null) {
                name.setText(target.getShortName());
                description.setText(target.getDescription());
            }
            return view;
        }
    }
}
