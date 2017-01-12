package com.andrewfisher.discountapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrewfisher.discountapp.R;
import com.andrewfisher.discountapp.model.DiscountItem;

import java.util.List;

import static com.andrewfisher.discountapp.R.id.discountAddress;

/**
 * Created by andrewfisher on 1/12/17.
 */

public class DiscountItemAdapter extends RecyclerView.Adapter<DiscountItemAdapter.ViewHolder>{
    private List<DiscountItem> mDiscountItems;
    private Context mContext;


    //address and phone, MAYBE LAT LONG OR ALL UP HERE
    TextView mAddress;
    TextView mPhoneNumber;
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
    public void onBindViewHolder(DiscountItemAdapter.ViewHolder holder, final int position) {
        final DiscountItem discountedItem = mDiscountItems.get(position);

        TextView storeName = holder.mItemStore;
        storeName.setText(discountedItem.getStore());

        TextView discountBlurb = holder.mDiscountDescription;
        discountBlurb.setText(discountedItem.getDiscount());

        mAddress = holder.mDiscountAddress;
        mAddress.setText(discountedItem.getAddress()+", " + discountedItem.getCity() + ", "+discountedItem.getState() + " "+discountedItem.getZipcode());

        mPhoneNumber = holder.mDiscountPhoneNumber;
        mPhoneNumber.setText(discountedItem.getPhone());

        TextView milesAway = holder.mMilesAway;
        milesAway.setText(discountedItem.getMiles()+" miles way");



        //set implicit intent for the stores phone number
        mPhoneNumber.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + discountedItem.getPhone()));
                mContext.startActivity(phoneIntent);
            }
        });

        //set implicit intent for the stores address
        mAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent addressIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:" + discountedItem.getLatitude()
                                + "," + discountedItem.getLongitude()
                                + "?q=(" + discountedItem.getStore() + ")"));
                mContext.startActivity(addressIntent);
            }
        });

//        mPhoneNumber.setOnClickListener(this);
        //MAYBE GET LONGITUDE/LATITUDE TO MAP YOU THERE









    }

    @Override
    public int getItemCount() {
        return mDiscountItems.size();
    }


    //View Holder Class to find all views
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
            mDiscountAddress = (TextView) itemView.findViewById(discountAddress);
            mDiscountPhoneNumber = (TextView) itemView.findViewById(R.id.discountPhoneNumber);
            mMilesAway = (TextView) itemView.findViewById(R.id.distanceAway);
        }

    }


}
