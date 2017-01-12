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

        //set the arraylist of discounts passed from the intent on MainActivity
        discountList = (ArrayList) intent.getSerializableExtra("discount_list");

        //creates the recycler adapter
        DiscountItemAdapter adapter = new DiscountItemAdapter(this,discountList);
        mCurrentCategoryRecyclerView.setAdapter(adapter);
        mCurrentCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
