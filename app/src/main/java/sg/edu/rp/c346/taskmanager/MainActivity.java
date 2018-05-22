package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddTask;
    ListView lvTask;
    ArrayAdapter<String> Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        ArrayList<String> data = db.getTaskContent();
        db.close();

        String txt = "";
        for (int i = 0; i < data.size(); i++) {
            txt += data.get(i) + "\n\n";
        }
        lvTask.(txt);

        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        lvTask = (ListView)findViewById(R.id.lvTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });
    }
}
