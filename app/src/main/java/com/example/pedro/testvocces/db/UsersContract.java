package com.example.pedro.testvocces.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.pedro.testvocces.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Esquema de la base de datos para abogados
 */
public class UsersContract extends BaseModel {

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME ="user";

        public static final String GENDER = "gender";
        public static final String TITLE = "title";
        public static final String FIRST = "first";
        public static final String LAST = "last";
        public static final String DOB = "dob";
        public static final String PHONE = "phone";
        public static final String LARGE = "large";
        public static final String MEDIUM = "medium";
        public static final String THUMBNAIL = "thumbnail";
        public static final String VISITS = "visits";
    }
    
    public static int insert(Context context, User user){
        SQLiteDatabase db = abrirConexion(context, 1);
        int id = 0;
        if (db != null) {
            id = (int) db.insert(UserEntry.TABLE_NAME, null, toContentValues(user));
            cerrarConexion(db);
        }
        return id;
    }

    public static void insertUsers(Context context, List<User> users){
        for(User user : users){
            insert(context, user);
        }
    }


    public static List<User> getAll(Context context) {
        SQLiteDatabase db = abrirConexion(context, 0);
        List<User> users = new ArrayList<User>();
        if (db != null) {
            Cursor cursor = db.rawQuery(
                    "select * from "
                            + UserEntry.TABLE_NAME, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    User user = fromCursor(cursor);
                    users.add(user);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            cerrarConexion(db);
        }
        return users;
    }

    public static ContentValues toContentValues(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserEntry.GENDER, user.getGender());
        cv.put(UserEntry.TITLE, user.getName().getTitle());
        cv.put(UserEntry.FIRST, user.getName().getFirst());
        cv.put(UserEntry.LAST, user.getName().getLast());
        cv.put(UserEntry.DOB, user.getDob().getTime());
        cv.put(UserEntry.PHONE, user.getPhone());
        cv.put(UserEntry.LARGE, user.getPicture().getLarge());
        cv.put(UserEntry.MEDIUM, user.getPicture().getMedium());
        cv.put(UserEntry.THUMBNAIL, user.getPicture().getThumbnail());
        cv.put(UserEntry.VISITS, user.getVisits());
        return cv;
    }
    
    public static User fromCursor(Cursor cursor){
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(UserEntry._ID)));
        user.setGender(cursor.getString(cursor.getColumnIndex(UserEntry.GENDER)));
        user.getName().setTitle(cursor.getString(cursor.getColumnIndex(UserEntry.TITLE)));
        user.getName().setFirst(cursor.getString(cursor.getColumnIndex(UserEntry.FIRST)));
        user.getName().setLast(cursor.getString(cursor.getColumnIndex(UserEntry.LAST)));
        user.setDob(cursor.getLong(cursor.getColumnIndex(UserEntry.DOB)));
        user.setPhone(cursor.getString(cursor.getColumnIndex(UserEntry.PHONE)));
        user.getPicture().setLarge(cursor.getString(cursor.getColumnIndex(UserEntry.LARGE)));
        user.getPicture().setMedium(cursor.getString(cursor.getColumnIndex(UserEntry.MEDIUM)));
        user.getPicture().setThumbnail(cursor.getString(cursor.getColumnIndex(UserEntry.THUMBNAIL)));
        user.setVisits(cursor.getInt(cursor.getColumnIndex(UserEntry.VISITS)));
        return user;
    }

    public static void clear(Context context) {
        SQLiteDatabase db = abrirConexion(context, 1);
        if (db != null) {
            String sql = "DELETE FROM "
                    + UserEntry.TABLE_NAME;
            db.execSQL(sql);
            cerrarConexion(db);
        }
    }

    public static void updateParamsById(Context context, int id,
                                        HashMap<String, String> params) {
        SQLiteDatabase db = abrirConexion(context, 1);
        if (db != null) {
            ContentValues cv = new ContentValues();
            Iterator<Map.Entry<String, String>> it = params.entrySet()
                    .iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                cv.put(e.getKey(), e.getValue());
            }
            db.update(UserEntry.TABLE_NAME, cv, UserEntry._ID + " = "
                    + id, null);
            cerrarConexion(db);
        }
    }

}
