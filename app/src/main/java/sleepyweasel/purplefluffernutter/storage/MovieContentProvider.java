package sleepyweasel.purplefluffernutter.storage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.ArrayList;

import sleepyweasel.purplefluffernutter.MovieEntry;
import sleepyweasel.purplefluffernutter.MovieEntryStorage;

public class MovieContentProvider extends ContentProvider implements MovieEntryStorage {

    public static final String PROVIDER_NAME = "sleepyweasel.purplefluffernutter.MoviesProvider";

    private static final String URL = "content://" + PROVIDER_NAME + "/movies";
    private static final Uri CONTENT_URI = Uri.parse(URL);

    private SQLiteDatabase database;

    public static final String _ID_COLUMN_NAME = "_id";
    public static final String TITLE_COLUMN_NAME = "title";
    public static final String YEAR_COLUMN_NAME = "year";

    private static final String MOVIES_TABLE_NAME = "movies";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Repository";
        private static final String ID_COLUMN = "_id INTEGER PRIMARY KEY AUTOINCREMENT";
        private static final String TITLE_COLUMN = TITLE_COLUMN_NAME + " TEXT NOT NULL";
        private static final String YEAR_COLUMN = YEAR_COLUMN_NAME + " TEXT NOT NULL";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_DB_TABLE = " CREATE TABLE " + MOVIES_TABLE_NAME +
                    " ( " + ID_COLUMN + ", " + TITLE_COLUMN + ", " + YEAR_COLUMN + ");";
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS movies");
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return (database != null);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void addMovie(MovieEntry entry) {
        ContentValues values = new ContentValues();
        values.put(TITLE_COLUMN_NAME, entry.getTitle());
        values.put(YEAR_COLUMN_NAME, entry.getYear());
        insert(CONTENT_URI, values);
    }

    @Override
    public int size() {
        Cursor cursor = query(CONTENT_URI, null, null, null, null);
        return cursor.getCount();
    }

    @Override
    public MovieEntry getEntry(int id) {
        Cursor cursor = query(CONTENT_URI, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int idValue = cursor.getInt(cursor.getColumnIndex(_ID_COLUMN_NAME));
            if (idValue == id) {
                return createMovieEntryFromCursorData(cursor);
            }
        }
        return null;
    }

    public static MovieEntry createMovieEntryFromCursorData(Cursor cursor) {
        String titleValue = cursor.getString(cursor.getColumnIndex(MovieContentProvider.TITLE_COLUMN_NAME));
        int yearValue = cursor.getInt(cursor.getColumnIndex(MovieContentProvider.YEAR_COLUMN_NAME));
        return new MovieEntry(titleValue, yearValue);
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
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MOVIES_TABLE_NAME);
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "movies", 1);
        if (uriMatcher.match(uri) == 1) {
            Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
        else {
            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = database.insert(MOVIES_TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri changeUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(changeUri, null);
            return changeUri;
        }
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
