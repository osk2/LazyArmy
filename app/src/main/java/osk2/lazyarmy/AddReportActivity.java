package osk2.lazyarmy;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddReportActivity extends AppCompatActivity {

    String[] strings = { "Red", "Blue", "Green" };
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);
        Spinner spinner = (Spinner) findViewById(R.id.report_method_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new String[] {"Line", this.getResources().getString(R.string.report_method_sms)});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Button time_picker_btn = (Button)findViewById(R.id.time_picker_btn);

        GregorianCalendar calendar = new GregorianCalendar();
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
                time_picker_btn.setText((hourOfDay > 12 ? time_pm : time_am) + " " + (hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                        + ":" + sMinute );
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
                false);

        time_picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
