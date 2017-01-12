package com.andrewfisher.discountapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewfisher.discountapp.R;
import com.andrewfisher.discountapp.model.DiscountItem;

import java.util.List;

/**
 * Created by andrewfisher on 1/12/17.
 */

public class DiscountItemAdapter extends RecyclerView.Adapter<DiscountItemAdapter.ViewHolder> {
    private List<DiscountItem> mDiscountItems;
    private Context mContext;

    public DiscountItemAdapter(Context context, List<DiscountItem> items){
        mContext = context;
        mDiscountItems = items;
    }

    private Context getContext(){
        return mContext;
    }

    //inflates the recycler view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View discountItemView = inflater.inflate(R.layout.single_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(discountItemView);
        return viewHolder;
    }

    //binds and sets text to the proper item and its components
    @Override
    public void onBindViewHolder(DiscountItemAdapter.ViewHolder holder, int position) {
        DiscountItem discountedItem = mDiscountItems.get(position);

        TextView storeName = holder.mItemStore;
        storeName.setText(discountedItem.getStore());

        TextView discountBlurb = holder.mDiscountDescription;
        discountBlurb.setText(discountedItem.getDiscount());

        TextView discountAddress = holder.mDiscountAddress;
        discountAddress.setText(discountedItem.getAddress()+", " + discountedItem.getCity() + ", "+discountedItem.getState() + " "+discountedItem.getZipcode());

        TextView milesAway = holder.mMilesAway;
        milesAway.setText(discountedItem.getMiles()+" miles way");


        //MAYBE GET LONGITUDE/LATITUDE TO MAP YOU THERE

    }

    @Override
    public int getItemCount() {
        return mDiscountItems.size();
    }

    //static class to find all the views
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mItemStore;
        public TextView mDiscountDescription;
        public TextView mDiscountAddress;
        public TextView mDiscountPhoneNumber;
        public TextView mMilesAway;

        public ViewHolder(View itemView){
            super(itemView);

            mItemStore = (TextView) itemView.findViewById(R.id.itemStore);
            mDiscountDescription = (TextView) itemView.findViewById(R.id.discountDescription);
            mDiscountAddress = (TextView) itemView.findViewById(R.id.discountAddress);
            mDiscountPhoneNumber = (TextView) itemView.findViewById(R.id.discountPhoneNumber);
            mMilesAway = (TextView) itemView.findViewById(R.id.distanceAway);
        }

    }


}
