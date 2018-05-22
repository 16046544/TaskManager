package sg.edu.rp.c346.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int reqCode = 12345;
    Button btnAddTask;
    ListView lvTask;
    ArrayAdapter aa;
    DBHelper db;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);

        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);

        //Set the alarm
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


        db = new DBHelper(this);
        db.getWritableDatabase();

        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        lvTask = (ListView)findViewById(R.id.lvTask);

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lvTask.setAdapter(aa);

        DBHelper db = new DBHelper(MainActivity.this);

        al.addAll(db.getTaskContent());
        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
        lvTask.setAdapter(aa);


        db.close();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i,9);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            db = new DBHelper(this);
            db.getWritableDatabase();
            db.close();

            aa.notifyDataSetChanged();
        }
    }
}
