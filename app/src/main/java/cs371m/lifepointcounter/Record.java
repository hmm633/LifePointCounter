package cs371m.lifepointcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

//package info.androidhive.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
        import java.util.List;
/**
 * Created by Eddy on 5/4/2016.
 */
public class Record extends AppCompatActivity {
    public static List<Entry> entryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecordAdapter mAdapter;
    // Turns off initialization (for testing)
    private static boolean initAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RecordAdapter(entryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // Only initialize on first run
//        if(!initAlready){
//            prepareEntryData();
//            initAlready = true;
//        }

//        prepareEntryData();
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        Intent intent = new Intent(Record.this, ScoreScreen.class);
//        startActivity(intent);
//    }

    private void prepareEntryData() {
        // There probably shouldn't be anything here
        // We'll put a dummy one in here just for kicks

        Entry entry = new Entry("Eddy", "0", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "1", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "2", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "3", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "4", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "5", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "6", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "7", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "8", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "9", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "10", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "11", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "12", "20", "0");
        entryList.add(entry);

        entry = new Entry("Eddy", "13", "20", "0");
        entryList.add(entry);

        mAdapter.notifyDataSetChanged();
    }

//    public void removeAt(int position){
//        entryList.remove(position);
//
//        mAdapter.notifyDataSetChanged();
//    }
//
//    public void append(String string1, String string2, String string3, String string4){
//        Entry entry = new Entry(string1, string2, string3, string4);
//        entryList.add(entry);
//
//        mAdapter.notifyDataSetChanged();
//    }

}

