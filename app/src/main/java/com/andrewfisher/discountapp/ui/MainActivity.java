package com.andrewfisher.discountapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

//COULD INSTEAD OF A SCROLL BOX TO SORT???
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.frontPageHeader) TextView mFrontPageHeader;
    @Bind(R.id.itemCategories) ListView mCategoriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final ArrayList<DiscountItem> discountList= allDiscounts();


        //display simple list
        final ArrayList<String> listCategories = new ArrayList<>();
        listCategories.add("View All Items");
        for(DiscountItem item : discountList){
            //MAYBE TRY WITH STRING BUFFER OR BUIDER
            String categoryIdString = Integer.toString(item.getCategoryId());
            if(!listCategories.contains(categoryIdString)){
                listCategories.add(categoryIdString);
            }
        }
        Collections.sort(listCategories);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listCategories);
        mCategoriesListView.setAdapter(adapter);
        //end of list code

        //when user clicks on a single list item
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







    public String getJSONFile(){

        String jsonFileString;
        try{

            InputStream asset = getAssets().open("discounts.json");
            int size = asset.available();
            byte[] buffer = new byte[size];
            asset.read(buffer);
            asset.close();
            jsonFileString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Log.d("test",jsonFileString);
        return jsonFileString;
    }


    public ArrayList<DiscountItem> allDiscounts(){
        String jsonString = getJSONFile();
        ArrayList<DiscountItem> allDiscountedItems = new ArrayList<>();

        try{
//            JSONObject discountJSON = new JSONObject(jsonString);

            //retrieves the json array
            JSONArray discountJSON = new JSONArray(jsonString);

            //loops through each discount
            for(int i = 0; i< discountJSON.length();i++){
                //maybe not json objects?
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

                DiscountItem discountItem = new DiscountItem(id,businessName,address,city,state,zip,phoneNumber,discountInformation,categoryId,latitude,longitude,miles);

                allDiscountedItems.add(discountItem);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allDiscountedItems;
    }

}
