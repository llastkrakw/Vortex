package com.example.vortex.main.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vortex.R;
import com.example.vortex.adapters.BookListAdapter;
import com.example.vortex.fakeForUi.TravelListFake;
import com.example.vortex.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {

    private BookListViewModel mViewModel;
    private View rootView;
    private RecyclerView recyclerView;
    private Context context;


    public static BookListFragment newInstance() {
        return new BookListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.book_list_fragment, container, false);
        context = rootView.getContext();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        List<TravelListFake> list = new ArrayList<>();
        TravelListFake travel1 = new TravelListFake(R.drawable.image_test, R.drawable.ic_star_gold_24dp,
                "10-3-2018", 5, "6:00", "11:00", "Vip", 8000.0, true, "Finex Voyage", 1);
        TravelListFake travel2 = new TravelListFake(R.drawable.image_test, R.drawable.ic_star_gold_24dp,
                "10-8-2018", 3, "15:00", "18:00", "classic", 3000.0, false, "Finex Voyage", 1);
        TravelListFake travel3 = new TravelListFake(R.drawable.image_test, R.drawable.ic_star_gray_24dp,
                "12-25-2018", 2, "12:00", "14:00", "vip", 5000.0, false, "Buca Voyage", 1);
        TravelListFake travel4 = new TravelListFake(R.drawable.image_test, R.drawable.ic_star_gray_24dp,
                "1-14-2019", 6, "10:00", "16:00", "classic", 2500.0, true, "Buca Voyage", 1);
        list.add(travel1);
        list.add(travel2);
        list.add(travel3);
        list.add(travel4);
        RecyclerView.Adapter adapter = new BookListAdapter(list, context);
        recyclerView.setAdapter(adapter);
    }
}
