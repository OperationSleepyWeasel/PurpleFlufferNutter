package sleepyweasel.purplefluffernutter.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

import sleepyweasel.purplefluffernutter.MovieEntry;
import sleepyweasel.purplefluffernutter.MovieEntryStorage;

public class MovieContentProvider extends ContentProvider implements MovieEntryStorage {

    @Override
    public boolean onCreate() {
        return (database != null);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void addMovie(MovieEntry entry) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public MovieEntry getEntry(int id) {
        return null;
    }

    @Override
    public MovieEntry getEntryByTitle(String movieTitle) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public ArrayList<String> getMovieTitles() {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
