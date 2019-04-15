package com.example.adoptme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.KittenViewHolder> {
    private Context mContext;
    private ArrayList<Cat> mCatList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener { //forward click from the catadapter to the main activity.
        void onItemClick(int position);
    }
public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener; //we call this method later in the mainactivity and pass the mainactivity as this listener.
}

    public CatAdapter(Context context, ArrayList<Cat> catList){
        mContext = context;
        mCatList = catList;
    }


    @Override
    public KittenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(mContext).inflate(R.layout.single_cat, parent, false);
      return new KittenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KittenViewHolder holder, int position) {
    Cat currentItem = mCatList.get(position); //position of carItem will be saved in current item

        String imageUrl = currentItem.getImageUrl();
        String catName = currentItem.getName();
        int ageCount = currentItem.getAgeCount();

        holder.mTextViewName.setText(catName);
        holder.mTextViewAge.setText("Age: " + ageCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCatList.size();
    }

    public class KittenViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewAge;


        public KittenViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewAge = itemView.findViewById(R.id.text_view_age);
            mTextViewName = itemView.findViewById(R.id.text_view_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){ //if listener for this interface is not null -> if have listener
                        int position = getAdapterPosition(); //the position of the item will be stored here
                        if (position!= RecyclerView.NO_POSITION){
                            mListener.onItemClick(position); //the click gets caught on the item view in the adapter passed to the interface to the activity
                        }
                    }
                }
            });
        }
    }
}
