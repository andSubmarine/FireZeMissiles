package one.blanke.firezemissiles;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import one.blanke.firezemissiles.model.Target;
import one.blanke.firezemissiles.model.TargetGenerator;

import static android.widget.Toast.LENGTH_SHORT;

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

        if (target != null) {
            TextView textName = (TextView) getActivity().findViewById(R.id.detail_target_name);
            if (textName != null) {
                textName.setText(target.getName());
            }

            TextView textShort = (TextView) getActivity().findViewById(R.id.detail_target_short_name);
            if (textShort != null) {
                textShort.setText(target.getShortName());
            }

            TextView textLeader = (TextView) getActivity().findViewById(R.id.detail_target_leader);
            if (textLeader != null) {
                textLeader.setText(target.getLeader());
            }

            TextView textDescription = (TextView) getActivity().findViewById(R.id.detail_target_description);
            if (textDescription != null) {
                textDescription.setText(target.getDescription());
            }

            ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar);
            if (progressBar != null) {
                progressBar.setMax(target.getMaxStrength());
                progressBar.setSecondaryProgress(target.getStrength());

                TextView progress_text = (TextView) getActivity().findViewById(R.id.progress_text);
                if (progress_text != null) {
                    progress_text.setText(target.getStrength() + " / " + target.getMaxStrength());
                }
            }

            TextView status = (TextView) getActivity().findViewById(R.id.detail_target_state);
            if (progressBar != null) {
                Resources resources = getActivity().getResources();
                switch (target.getStatus()) {
                    case MISSILE_INCOMING:
                        status.setBackgroundColor(resources.getColor(R.color.colorWarning));
                        status.setText(resources.getText(R.string.warning_incoming));
                        break;
                    case HIT_BY_MISSILE:
                        status.setBackgroundColor(resources.getColor(R.color.colorError));
                        status.setText(resources.getText(R.string.warning_hit));
                        break;
                    case LAUNCHING_MISSILE:
                        status.setBackgroundColor(resources.getColor(R.color.colorWarning));
                        status.setText(resources.getText(R.string.warning_launching));
                        break;
                    case DESTROYED:
                        status.setBackgroundColor(resources.getColor(R.color.colorError));
                        status.setText(resources.getText(R.string.warning_destroyed));
                        break;
                    case ALIVE:
                        status.setBackgroundColor(resources.getColor(R.color.colorPrimaryLight));
                        status.setText(resources.getText(R.string.no_warning));
                        break;
                }
            }
        }

    }
}
