package com.andrewfisher.discountapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.andrewfisher.discountapp.R;
import com.andrewfisher.discountapp.adapter.DiscountItemAdapter;
import com.andrewfisher.discountapp.model.DiscountItem;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity {
    @Bind(R.id.categoryHeader) TextView mCategoryHeader;
    @Bind(R.id.currentCategoryRecyclerView) RecyclerView mCurrentCategoryRecyclerView;
    ArrayList<DiscountItem> discountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String headline = intent.getStringExtra("category");
        mCategoryHeader.setText(headline);


        //converts headline into the clicked into category idc - view all is converted into -1, if need be I could check for View all first and then if not found I parse the string else i post everything
        int currentCategoryId;
        try{
            currentCategoryId = Integer.parseInt(headline);
        }catch (NumberFormatException e){
            currentCategoryId = -1;
        }

        //set the arraylist of discounts passed from the intent on MainActivity
        discountList = (ArrayList) intent.getSerializableExtra("discount_list");

        //checks if View all was clicked is shown
        if(currentCategoryId ==-1){
            //sorts list by category id
            Collections.sort(discountList);
        }else{
            // loops to remove an item without the current category id
            for(int i = 0; i<discountList.size();i++){

                if(discountList.get(i).getCategoryId() != currentCategoryId){
                    discountList.remove(i);
                    i--;
                }

            }
        }







        //creates the filtered recycler adapter
        DiscountItemAdapter adapter = new DiscountItemAdapter(this,discountList);
        mCurrentCategoryRecyclerView.setAdapter(adapter);
        mCurrentCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
