package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.UserBaseHelper;
import com.example.myapplication.database.UserDbSchema;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList userList;
    private Context context;
    private SQLiteDatabase database;
    private List users;

    public static UserList get(Context context) {
        if (userList == null) {
            userList = new UserList(context);
        }
        return userList;
    }

    private UserList(Context context) {
        this.context = context.getApplicationContext();
        database = new UserBaseHelper(context).getWritableDatabase();
    }

    public List getUsers() {
        users = new ArrayList();
        UserCursorWrapper cursor = queryUsers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.FIRSTNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.LASTNAME, user.getUserLastName());
        values.put(UserDbSchema.UserTable.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                UserDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new UserCursorWrapper(cursor);
    }

    public void updateUser(User user) {
        String uuidString = user.getUuid().toString();
        ContentValues values = getContentValues(user);

        database.update(UserDbSchema.UserTable.NAME, values,
                UserDbSchema.UserTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    public void deleteUser(User user) {

        String uuidString = user.getUuid().toString();
        database.delete(UserDbSchema.UserTable.NAME,
                UserDbSchema.UserTable.Cols.UUID + " = ? ",
                new String[]{uuidString});
    }
}

        // ContentValues values = getContentValues(user);
        //database.delete(UserDbSchema.UserTable.NAME, null, null);
        //database.update(UserDbSchema.UserTable.NAME, null,null, new String[]{delete});
         //       UserDbSchema.UserTable.Cols.UUID + "=?",
        //@Override
        //    public void deleteUser(User user) {
        //        String uuidString = user.getId().toString();
        //        database.delete(UserDbSchema.UserTable.NAME,
        //                UserDbSchema.UserTable.Cols.UUID+" = ? ",
        //                new String[] { uuidString });

        //UserDbSchema databaseHandler = new UserDbSchema.(this);
        //databaseHandler.hashCode();

        // adapter.notifyDataSetChanged();

        // cancelAlarm();

        //deleteUser = deleteUser(UUID.fromString(UserDbSchema.UserTable.Cols.UUID));

        //ContentValues values = getContentValues(user);
        //database.delete(UserDbSchema.UserTable.NAME, null, null, null)
        //database.delete(UserDbSchema.UserTable.NAME, UUID, null);
        //

        /* new String[]{String.valueOf(user.getUuid())});
        UserDbSchema.UserTable.Cols.UUID+"=?",
               new String[]{uuidString});*/

    //  ContentValues values = getContentValues(user);

    // database.update(UserDbSchema.UserTable.NAME,values,
    //       UserDbSchema.UserTable.Cols.UUID+"=?",
    //     new String[]{uuid});
