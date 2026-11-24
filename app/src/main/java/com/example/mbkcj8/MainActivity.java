package com.example.mbkcj8;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etDate;
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
        etDate = findViewById(R.id.etDate);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        birthdayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, birthdayList);
        listView.setAdapter(birthdayAdapter);

        btnAdd.setOnClickListener(v -> addPerson());
    }

    private void addPerson() {
        String name = etName.getText().toString().trim();
        String dateText = etDate.getText().toString().trim();

        if (name.isEmpty() || dateText.isEmpty()) {
            Toast.makeText(this, "Wypelnij pola", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            LocalDate birthDate = LocalDate.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE);

            Person newPerson = new Person(name, birthDate);
            birthdayList.add(newPerson);
            birthdayAdapter.notifyDataSetChanged();

            etName.setText("");
            etDate.setText("");
        } catch (DateTimeParseException e) {
            Toast.makeText(this, "Niepoprawny format daty. Użyj RRRR-MM-DD", Toast.LENGTH_LONG).show();
        }
    }
}
