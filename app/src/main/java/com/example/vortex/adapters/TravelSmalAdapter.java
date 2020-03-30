package com.example.vortex.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vortex.R;
import com.example.vortex.fakeForUi.TravelFake;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TravelSmalAdapter extends RecyclerView.Adapter<TravelSmalAdapter.ViewHolder>{

    private Context context;
    private List<TravelFake> ListItem;
    private View view;

    public TravelSmalAdapter(Context context, List<TravelFake> listItem) {
        this.context = context;
        ListItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_history_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TravelFake travel = ListItem.get(position);
         holder.agence.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), travel.getImage(), 60, 60));
         holder.rating.setImageBitmap(decodeSampledBitmapFromResource(view.getResources(), travel.getRatingImage(), 60, 60));
        holder.ville1.setText(travel.getDepart());
        holder.ville2.setText(travel.getArrival());
        holder.date.setText(travel.getTravelDate());
        String time = String.format(" / %dh", travel.getHr());
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return ListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ville1;
        private TextView ville2;
        private TextView date;
        private TextView time;
        private ImageView agence;
        private ImageView rating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ville1 = (TextView) itemView.findViewById(R.id.name_ville1);
            ville2 = (TextView) itemView.findViewById(R.id.name_ville2);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            agence = (ImageView) itemView.findViewById(R.id.agence_image);
            rating = (ImageView) itemView.findViewById(R.id.rating);
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
