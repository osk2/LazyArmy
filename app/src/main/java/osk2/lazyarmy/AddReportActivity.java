package osk2.lazyarmy;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddReportActivity extends AppCompatActivity {

    private TimePickerDialog timePickerDialog;
    private DBHelper dbhelper = null;
    private String report_date = "";
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);
        Spinner spinner = (Spinner) findViewById(R.id.report_method_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new String[] {"Line", this.getResources().getString(R.string.report_method_sms)});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Button time_picker_btn = (Button)findViewById(R.id.time_picker_btn);
        final Button add_done_btn = (Button)findViewById(R.id.add_done);
        final Context that = this;
        GregorianCalendar calendar = new GregorianCalendar();
        EditText name_input =  (EditText)findViewById(R.id.report_name);
        name = name_input.getText().toString();

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            String time_pm = getResources().getString(R.string.time_pm);
            String time_am = getResources().getString(R.string.time_am);
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String sMinute;
                //fill 0 into time format
                if (String.valueOf(minute).length() < 2) {
                    sMinute = "0" + minute;
                } else {
                    sMinute = String.valueOf(minute);
                }
                report_date = (hourOfDay > 12 ? time_pm : time_am) + " " + (hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                        + ":" + sMinute;
                time_picker_btn.setText(report_date);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
                false);

        time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        add_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper sqlitedb = new DBHelper(that);
                SQLiteDatabase db = sqlitedb.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("description", name);
                cv.put("report_at", report_date);
                db.insert("report", null, cv);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
