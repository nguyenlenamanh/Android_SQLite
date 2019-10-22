package com.example.student.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBContext db;
    ListView listView;
    ArrayList<SinhVien> sinhViens;
    int position = -1;
    Button btnAdd, btnEdit, btnClear;
    EditText edtID, edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBContext(this);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnClear = findViewById(R.id.btnClear);

        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);

        sinhViens = db.sinhViens(db.getTable("SV"));

        //final ArrayAdapter<SinhVien> sinhVienArrayAdapter = new ArrayAdapter<SinhVien>(this,android.R.layout.simple_list_item_1,sinhViens);

        final ArrayAdapter<SinhVien> sinhVienArrayAdapter = new CustomAdapter(this, R.layout.item, sinhViens);

        listView = findViewById(R.id.lvListView);
        listView.setAdapter(sinhVienArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, Integer.toString(i), Toast.LENGTH_SHORT).show();
                position = i;

                SinhVien sv = sinhViens.get(i);
                edtID.setText(Integer.toString(sv.getId()));
                edtName.setText(sv.getName());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete sinh vien?")
                        .setMessage("Ban co muon xoa sinh vien " + sinhViens.get(i).getName() + " khong?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SinhVien sv = sinhViens.get(pos);
                                db.DeleteSV(new SinhVien(sv.getId(),sv.getName()));

                                sinhViens.remove(pos);
                                sinhVienArrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtID.getText().toString());
                String name = edtName.getText().toString();

                db.InsertSV(new SinhVien(id,name));
                sinhViens.add(new SinhVien(id,name));

                sinhVienArrayAdapter.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtID.getText().toString());
                String name = edtName.getText().toString();
                //db.UpdateSV(new SinhVien(id,name));
                SinhVien sv = sinhViens.get(position);
                db.UpdateSV(new SinhVien(id,name), sv.getId());

                sv.setId(id);
                sv.setName(name);

                sinhVienArrayAdapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtID.setText("");
                edtName.setText("");
            }
        });
    }




}
