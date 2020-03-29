package com.example.vortex.main.fragments;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vortex.R;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainFragment extends Fragment{

    private MainViewModel mViewModel;
    private NiceSpinner niceSpinner;
    private NiceSpinner niceSpinner2;
    private TextView arrival;
    private TextView depart;
    private ImageView historyButton;
    private Button date;
    private boolean isExpend = false;
    private List<String> dataset2;
    private Context context;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        if(container != null){
            niceSpinner = (NiceSpinner) rootView.findViewById(R.id.nice_spinner);
            niceSpinner2 = (NiceSpinner) rootView.findViewById(R.id.nice_spinner2);
            arrival =  (TextView) rootView.findViewById(R.id.valueArrival);
            depart =  (TextView) rootView.findViewById(R.id.valueDepart);
            date = (Button) rootView.findViewById(R.id.date);
            historyButton = (ImageView) rootView.findViewById(R.id.btnhistory);
        }

        List<String> dataset = new LinkedList<>(Arrays.asList("Douala", "Yaounde", "Bamenda", "Kousserie", "Bafang"));
        dataset2 = new LinkedList<>(Arrays.asList("DLA", "YDE", "BMD", "KSR", "BFA"));
        arrival.setText(dataset2.get(1));
        depart.setText(dataset2.get(0));
        niceSpinner.attachDataSource(dataset);
        niceSpinner2.attachDataSource(dataset);
        niceSpinner2.setSelectedIndex(1);
        context = rootView.getContext();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                String item = (String) parent.getItemAtPosition(position);
                depart.setText((String)dataset2.get(position));
                if(context != null)
                     Toast.makeText(context, item, Toast.LENGTH_LONG).show();
            }
        });

        niceSpinner2.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                // This example uses String, but your type can be any
                String item = (String) parent.getItemAtPosition(position);
                arrival.setText((String)dataset2.get(position));
                if(context != null)
                    Toast.makeText(context, item, Toast.LENGTH_LONG).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainFragment.this.getActivity());
                // Get the layout inflater
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View customDate = inflater.inflate(R.layout.date_picker, null);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(customDate)
                        // Add action buttons
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                DatePicker newDate = (DatePicker)customDate.findViewById(R.id.datePicker);
                                StringBuilder builder = new StringBuilder();;
                                builder.append(newDate.getMonth()).append(" - ");//month is 0 based
                                builder.append(newDate.getDayOfMonth()).append(" - ");
                                builder.append(newDate.getYear());
                                date.setText(builder.toString());
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                 builder.create().show();
            }
        });
    }



}
