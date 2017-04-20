package one.blanke.firezemissiles;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import one.blanke.firezemissiles.model.Target;
import one.blanke.firezemissiles.model.TargetGenerator;

/**
 * Created by andre_000 on 20-04-2017.
 */

public class DetailsFragment extends Fragment {
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            int guestId = getArguments().getInt(MainActivity.TARGET_ID);
            updateTarget(guestId);
        }
    }

    public void updateTarget(int targetID) {
        Target target = TargetGenerator.getTarget(targetID);

        TextView textName = (TextView) getActivity().findViewById(R.id.detail_target_name);
        if (textName != null && target != null) {
            textName.setText(target.getName());
        }

        TextView textShort = (TextView) getActivity().findViewById(R.id.detail_target_short_name);
        if (textShort != null && target != null) {
            textShort.setText(target.getShortName());
        }

        TextView textLeader = (TextView) getActivity().findViewById(R.id.detail_target_leader);
        if (textLeader != null && target != null) {
            textLeader.setText(target.getLeader());
        }

        TextView textDescription = (TextView) getActivity().findViewById(R.id.detail_target_description);
        if (textDescription != null && target != null) {
            textDescription.setText(target.getDescription());
        }

        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar);
        if (progressBar != null && target != null) {
            int progress = target.getProgress();
            progressBar.setMax(target.getMaxStrength());
            progressBar.setSecondaryProgress(target.getStrength());

            TextView progress_text = (TextView) getActivity().findViewById(R.id.progress_text);
            if (progress_text != null) {
                progress_text.setText(target.getStrength() + " / " + target.getMaxStrength());
            }
        }
    }
}
