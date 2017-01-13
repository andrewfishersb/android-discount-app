package com.andrewfisher.discountapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andrewfisher.discountapp.R;
import com.andrewfisher.discountapp.model.DiscountItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.frontPageHeader) TextView mFrontPageHeader;
    @Bind(R.id.itemCategories) ListView mCategoriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ArrayList<DiscountItem> discountList= allDiscounts();

        final ArrayList<String> listCategories = new ArrayList<>();
        listCategories.add("View All Items");

        for(DiscountItem item : discountList){
            String categoryIdString = Integer.toString(item.getCategoryId());
            if(!listCategories.contains(categoryIdString)){
                listCategories.add(categoryIdString);
            }
        }

        //Sorts list "alphabetically" starting with numbers, a built in Java function
        Collections.sort(listCategories);

        //builds a simple list of categories
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCategories);
        mCategoriesListView.setAdapter(adapter);

        //send object and category type when user clicks on a single list item
        mCategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent = new Intent(MainActivity.this,ItemActivity.class);
                //sends the category type and a serialized discount list
                intent.putExtra("category",listCategories.get(position));
                intent.putExtra("discount_list", discountList);

                startActivity(intent);
            }
        });

    }

    //Access a local JSON file filled with discounts and converts it to a string
    public String getJSONFile(){

        String jsonFileString;
        try{
            //opens up access to the file
            InputStream asset = getAssets().open("discounts.json");
            int size = asset.available();
            byte[] buffer = new byte[size];

            //reads int the file from the asset
            asset.read(buffer);
            asset.close();

            //retrieves the buffer and sets it to a string
            jsonFileString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonFileString;
    }

    //Splits apart the json file into an array
    public ArrayList<DiscountItem> allDiscounts(){
        String jsonString = getJSONFile();
        ArrayList<DiscountItem> allDiscountedItems = new ArrayList<>();

        try{
            //retrieves the json array
            JSONArray discountJSON = new JSONArray(jsonString);

            //loops through each discount
            for(int i = 0; i< discountJSON.length();i++){
                //Creates the current JSON object from the file
                JSONObject itemJSON = discountJSON.getJSONObject(i);

                String id = itemJSON.getString("id");
                String businessName = itemJSON.getString("store_name");
                String address = itemJSON.getString("address");
                String city = itemJSON.getString("city");
                String state = itemJSON.getString("state");
                String zip = itemJSON.getString("zip");
                String phoneNumber = itemJSON.getString("phone_number");
                String discountInformation = itemJSON.getString("discount");
                int categoryId = itemJSON.getInt("category_id");
                double latitude = itemJSON.getDouble("latitude");
                double longitude = itemJSON.getDouble("longitude");
                String miles = itemJSON.getString("miles");

                //creates a new discount object, then adds it to the list of discounts
                DiscountItem discountItem = new DiscountItem(id,businessName,address,city,state,zip,phoneNumber,discountInformation,categoryId,latitude,longitude,miles);

                allDiscountedItems.add(discountItem);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allDiscountedItems;
    }

}
