package com.andrewfisher.discountapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.andrewfisher.discountapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity {
    @Bind(R.id.categoryHeader) TextView mCategoryHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String headline = intent.getStringExtra("category");
        mCategoryHeader.setText(headline);

    }
}
