package com.example.mbkcj8;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private DatePicker datePicker;
    private Button btnAdd;
    private ListView listView;
    private List<Person> birthdayList = new ArrayList<>();
    private ArrayAdapter<Person> birthdayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        datePicker = findViewById(R.id.datePicker);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        birthdayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, birthdayList);
        listView.setAdapter(birthdayAdapter);

        btnAdd.setOnClickListener(v -> addPerson());
    }

    private void addPerson() {
        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Wypelnij pole imienia", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1;
        int day = datePicker.getDayOfMonth();

        LocalDate birthDate = LocalDate.of(year, month, day);

        Person newPerson = new Person(name, birthDate);
        birthdayList.add(newPerson);
        birthdayAdapter.notifyDataSetChanged();

        etName.setText("");
    }
}
