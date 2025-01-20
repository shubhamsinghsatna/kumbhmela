package com.mahakumbh.dishanirdesh.misc;

import android.content.Context;

import com.mahakumbh.dishanirdesh.database.EntityLocationManager;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

public class DataInitializer {
    private Context context;
    private EntityLocationManager dataManager;

    public  DataInitializer(Context context){
        this.context=context;
        dataManager = new EntityLocationManager(context);

        if(dataManager.getCount()==0){
            saveData();
        }
    }

    private void saveData() {

        //1. //Inserting Data For Ghat
        {
            dataManager.insert(
                    new EntityLocationModel(
                            1,
                            String.valueOf(LocationCategories.GHAT),
                            "Saraswati GHAT", //Title
                            "Saraswati Ghat Description", //Description
                            (long) 24.567043, //Latitude
                            (long) 80.865055, //Longitude
                            0, //Menu
                            0, // Photo
                            "Here is Address", //Addres
                            "Mobile Number", //Mobile Number
                            "Ghat, ganga, tags" //Tags
                    )
            );
        }

        //2. //Inserting Data For Ghat
        {
            dataManager.insert(
                    new EntityLocationModel(
                            String.valueOf(LocationCategories.GHAT),
                            "Sangam GHAT", //Title
                            "Sangam Ghat Description", //Description
                            (long) 24.567043, //Latitude
                            (long) 80.865055, //Longitude
                            0, //Menu
                            0, // Photo
                            "Here is Address", //Addres
                            "Mobile Number", //Mobile Number
                            "Ghat, ganga, tags" //Tags
                    )
            );
        }
    }


}
