package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private static final String USGS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private BookAdapter mAdapter;
    private EditText textEditSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        textEditSearch = findViewById(R.id.textSearch);
        mAdapter = new BookAdapter(this, new ArrayList<>());
        listView.setAdapter(mAdapter);
    }
    public void search(View view){
        if(TextUtils.isEmpty(textEditSearch.getText())){
            Toast.makeText(this, "El campo no puede estar vacio", Toast.LENGTH_LONG).show();
            return;
        }
        String url = USGS_REQUEST_URL + textEditSearch.getText();
        BookAsyncTask asyncTask = new BookAsyncTask();
        asyncTask.execute(url);


    }
    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {
        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Book}s as the result.
         */
        /**
         * task.execute --> doInBackground, is passing an string array
         */
        @Override
        protected List<Book> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            /**
             * fetch -> buscar in spanish
             * */
            List<Book> result = QueryUtils.fetchBookData(urls[0]);
            return result;
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        /**
         * doInBackground(List<T></T>) ->  onPostExecute;
         */
        @Override
        protected void onPostExecute(List<Book> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}