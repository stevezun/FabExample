package com.ebookfrenzy.fabexample;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  private void addListItem() {
    SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss MM/dd/yyyy", Locale.US );
    listItems.add(dateFormat.format( new Date(  ) ));
    adapter.notifyDataSetChanged();
  }

  ArrayList<String> listItems = new ArrayList<String>();
  ArrayAdapter<String> adapter;
  private ListView myListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );
    Toolbar toolbar = findViewById( R.id.toolbar );
    setSupportActionBar( toolbar );

    myListView = findViewById(R.id.listView);

    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems  );

    myListView.setAdapter( adapter );

    FloatingActionButton fab = findViewById( R.id.fab );
    fab.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        addListItem();
        Snackbar.make( view, "Item added to list.", Snackbar.LENGTH_LONG )
            .setAction( "Undo", undoOnClickListener ).show();
      }
    } );

    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        listItems.remove( listItems.size() -1 );
        adapter.notifyDataSetChanged();
        Snackbar.make( view, "Item removed", Snackbar.LENGTH_LONG )
            .setAction( "Action", null ).show();
      }
    };
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate( R.menu.menu_main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected( item );
  }
}
