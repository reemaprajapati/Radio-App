package com.example.otimus.radioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



//        ImageView imageView=(ImageView)findViewById(R.id.list_play);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ListActivity.this,HomePage.class));
//            }
//        });

        List<ItemStation> stationList=new ArrayList<>();
         stationList=getStation();
        StationListAdapter  stationListAdapter= new StationListAdapter(stationList, new StationListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemStation item) {
                Intent intent=new Intent(getApplicationContext(), HomePage.class);
                intent.putExtra("data",item);
                startActivity(intent);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(stationListAdapter);


    }

    private List<ItemStation> getStation(){
        List<ItemStation> stationList=new ArrayList<>();
        stationList.add(new ItemStation(1,R.drawable.kantipur,"Kantipur","96.1"));
        stationList.add(new ItemStation(2,R.drawable.ujyalo,"Ujyalo","90"));
        stationList.add(new ItemStation(3,R.drawable.bkt,"Bhaktapur FM","104.5"));
        stationList.add(new ItemStation(4,R.drawable.capital,"Capital FM","92.4"));
        stationList.add(new ItemStation(5,R.drawable.hits,"Hits FM","91.2"));
        stationList.add(new ItemStation(6,R.drawable.image,"Image FM","97.9"));
        stationList.add(new ItemStation(7,R.drawable.classic,"Classic FM","101.2"));
        stationList.add(new ItemStation(8,R.drawable.radionepal,"Radio Nepal","98"));


        return  stationList;

    }
}
