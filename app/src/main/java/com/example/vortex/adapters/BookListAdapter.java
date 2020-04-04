package com.example.vortex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vortex.R;
import com.example.vortex.fakeForUi.TravelFake;
import com.example.vortex.fakeForUi.TravelListFake;

import java.util.List;
import java.util.Locale;

import static com.example.vortex.helper.ImageResize.decodeSampledBitmapFromResource;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private Context context;
    private List<TravelListFake> ListItem;
    private View view;

    public BookListAdapter(List<TravelListFake> listItem, Context context) {
        ListItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_book_element, parent, false);

        return new BookListAdapter.ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder holder, int position) {

        TravelListFake item = ListItem.get(position);

        holder.rating.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), item.getRatingImage(), 24, 24));
        holder.agence_img.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), item.getImage(), 32, 32));
        if(item.isLike()){
            holder.like.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), R.drawable.ic_favorite_black_24dp, 24, 24));
        }
        else {
            holder.like.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), R.drawable.ic_favorite_border_black_24dp, 32, 32));
        }
        holder.agence_name.setText(item.getName());
        holder.number_items.setText(String.format( " +%d more", item.getNumber()));
        holder.time.setText(String.format("%dH", item.getHr()));
        holder.ville1_name.setText(view.getResources().getString(R.string.ville1));
        holder.ville2_name.setText(view.getResources().getString(R.string.ville2));
        holder.ville1.setText(view.getResources().getString(R.string.dla));
        holder.ville2.setText(view.getResources().getString(R.string.yde));
        holder.hr_depart.setText(item.getDepart());
        holder.hr_arrive.setText(item.getArrival());
        holder.date.setText(item.getTravelDate());
        holder.price.setText(String.format("%s for %s", item.getClasse(), item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return ListItem.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        private TextView agence_name;
        private TextView number_items;
        private TextView time;
        private TextView ville1;
        private TextView ville2;
        private TextView ville1_name;
        private TextView ville2_name;
        private TextView hr_depart;
        private TextView hr_arrive;
        private TextView date;
        private TextView price;
        private ImageView agence_img;
        private ImageView rating;
        private ImageView like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            agence_name = (TextView) itemView.findViewById(R.id.agence_name);
            number_items = (TextView) itemView.findViewById(R.id.number_item);
            time = (TextView) itemView.findViewById(R.id.time);
            ville1 = (TextView) itemView.findViewById(R.id.ville_1);
            ville2 = (TextView) itemView.findViewById(R.id.ville_2);
            ville1_name = (TextView) itemView.findViewById(R.id.name_ville1);
            ville2_name = (TextView) itemView.findViewById(R.id.name_ville2);
            hr_depart = (TextView) itemView.findViewById(R.id.hr_depart);
            hr_arrive = (TextView) itemView.findViewById(R.id.hr_arrive);
            date = (TextView) itemView.findViewById(R.id.date);
            price = (TextView) itemView.findViewById(R.id.classe_price);
            agence_img = (ImageView) itemView.findViewById(R.id.img_agence);
            rating = (ImageView) itemView.findViewById(R.id.rating);
            like = (ImageView) itemView.findViewById(R.id.like);
        }
    }
}
