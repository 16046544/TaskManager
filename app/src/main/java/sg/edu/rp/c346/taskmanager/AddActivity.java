package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnAddTask, btnCancel;
    int reqCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        btnAddTask = (Button)findViewById(R.id.btnAddTask);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        Intent i = getIntent();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameStr = etName.getText().toString();
                String descStr = etDesc.getText().toString();

                DBHelper dbh = new DBHelper(AddActivity.this);
                long row_affected = dbh.insertTask(nameStr,descStr);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(AddActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
                finish();
                setResult(RESULT_OK);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
