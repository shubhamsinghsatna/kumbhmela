package com.mahakumbh.dishanirdesh.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

import java.util.ArrayList;
import java.util.List;

public class EntityLocationManager {

    public static final String TABLE_NAME = "ENTITY_LOCATIONS";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_MENU = "menu";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_TAGS = "tags";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CATEGORY + " TEXT NOT NULL, " +
            COLUMN_TITLE + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            COLUMN_LATITUDE + " REAL NOT NULL, " +
            COLUMN_LONGITUDE + " REAL NOT NULL, " +
            COLUMN_MENU + " INTEGER, " +
            COLUMN_IMAGE + " INTEGER, " +
            COLUMN_ADDRESS + " TEXT, " +
            COLUMN_MOBILE_NUMBER + " TEXT, " +
            COLUMN_TAGS + " TEXT" +
            ");";

    private final Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public EntityLocationManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(EntityLocationModel model) {
        ContentValues values = new ContentValues();
        if (model.getId() != 0)
            values.put(COLUMN_ID, model.getId());
        values.put(COLUMN_CATEGORY, model.getCategory());
        values.put(COLUMN_TITLE, model.getTitle());
        values.put(COLUMN_DESCRIPTION, model.getDescription());
        values.put(COLUMN_LATITUDE, model.getLatitude());
        values.put(COLUMN_LONGITUDE, model.getLongitude());
        values.put(COLUMN_MENU, model.getMenu());
        values.put(COLUMN_IMAGE, model.getImage());
        values.put(COLUMN_ADDRESS, model.getAddress());
        values.put(COLUMN_MOBILE_NUMBER, model.getMobileNumber());
        values.put(COLUMN_TAGS, model.getTags());
        db.insert(TABLE_NAME, null, values);
    }

    public void update(EntityLocationModel model) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, model.getCategory());
        values.put(COLUMN_TITLE, model.getTitle());
        values.put(COLUMN_DESCRIPTION, model.getDescription());
        values.put(COLUMN_LATITUDE, model.getLatitude());
        values.put(COLUMN_LONGITUDE, model.getLongitude());
        values.put(COLUMN_MENU, model.getMenu());
        values.put(COLUMN_IMAGE, model.getImage());
        values.put(COLUMN_ADDRESS, model.getAddress());
        values.put(COLUMN_MOBILE_NUMBER, model.getMobileNumber());
        values.put(COLUMN_TAGS, model.getTags());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(model.getId())});
    }

    public void delete(long id) {
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public EntityLocationModel getLocationById(long id) {
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            EntityLocationModel model = cursorToModel(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public int getCount() {
        return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public void clearTable() {
        db.delete(TABLE_NAME, null, null);
    }

    @SuppressLint("Range")
    private EntityLocationModel cursorToModel(Cursor cursor) {
        EntityLocationModel model = new EntityLocationModel();
        model.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
        model.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        model.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        model.setLatitude(cursor.getLong(cursor.getColumnIndex(COLUMN_LATITUDE)));
        model.setLongitude(cursor.getLong(cursor.getColumnIndex(COLUMN_LONGITUDE)));
        model.setMenu(cursor.getInt(cursor.getColumnIndex(COLUMN_MENU)));
        model.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
        model.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        model.setMobileNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE_NUMBER)));
        model.setTags(cursor.getString(cursor.getColumnIndex(COLUMN_TAGS)));
        return model;
    }


    public List<EntityLocationModel> getLocationsByCategory(String category) {
        List<EntityLocationModel> locationList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_CATEGORY + " = ?",
                new String[]{category}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                EntityLocationModel model = cursorToModel(cursor);
                locationList.add(model);
            }
            cursor.close();
        }
        return locationList;
    }

    public List<EntityLocationModel> getLocationsByTagWord(String word) {
        List<EntityLocationModel> locationList = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                COLUMN_TAGS + " LIKE ?",
                new String[]{"%" + word + "%"},
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                EntityLocationModel model = cursorToModel(cursor);
                locationList.add(model);
            }
            cursor.close();
        }
        return locationList;
    }

    public List<EntityLocationModel> searchLocationsByWord(String word) {
        List<EntityLocationModel> locationList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_TITLE + " LIKE ? OR " +
                COLUMN_DESCRIPTION + " LIKE ? OR " +
                COLUMN_CATEGORY + " LIKE ? OR " +
                COLUMN_TAGS + " LIKE ?";
        String searchPattern = "%" + word + "%";

        Cursor cursor = db.rawQuery(query, new String[]{searchPattern, searchPattern, searchPattern, searchPattern});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                EntityLocationModel model = cursorToModel(cursor);
                locationList.add(model);
            }
            cursor.close();
        }
        return locationList;
    }



}
