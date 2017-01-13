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


        //converts headline into a category int - all is converted into -1
        int currentCategoryId;
        try{
            currentCategoryId = Integer.parseInt(headline);
        }catch (NumberFormatException e){
            currentCategoryId = -1;
        }


        //set the arraylist of discounts passed from the intent on MainActivity
        discountList = (ArrayList) intent.getSerializableExtra("discount_list");

        //filters out incorrect entries LATER CONTROL FOR VIEW ALL
        for(int i = 0; i<discountList.size();i++){
            //fairly pointless variables to debug
            int itemCategoryId = discountList.get(i).getCategoryId();

            if(itemCategoryId != currentCategoryId){
                discountList.remove(i);
                i--;
            }

            //end of variables
//            if(item.getCategoryId() != currentCategoryId){
//                discountList.remove(discountList.indexOf(item));
//            }
        }




        //creates the filtered recycler adapter
        DiscountItemAdapter adapter = new DiscountItemAdapter(this,discountList);
        mCurrentCategoryRecyclerView.setAdapter(adapter);
        mCurrentCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
