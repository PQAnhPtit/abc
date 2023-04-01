package com.example.appbc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CongViecAdapter.CongViecItemListener, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private CongViecAdapter adapter;
    private RadioGroup bt1;
    private RadioButton ic1, ic2;
    private ImageView image;
    private EditText eName, eNgay;
    private Button btAdd, btNgay, btUpdate;
    private SearchView searchView;
    private int pcurr;;
    private int[] imgs = {R.drawable.cat1,R.drawable.ic_launcher_background};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new CongViecAdapter(this);
        btNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();
                int cy = c.get(Calendar.YEAR);
                int cm = c.get(Calendar.MONTH);
                int cd = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int d, int m, int y) {
                        eNgay.setText(y+"/"+(m+1)+"/"+d);
                    }
                },cd,cm,cy);
                dialog.show();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongViec CongViec = new CongViec();
                String name = eName.getText().toString();
                String desc = eNgay.getText().toString();
                int img = R.drawable.cat1;
                int check = bt1.getCheckedRadioButtonId();
                switch (check){
                    case R.id.ic1:
                        img = R.drawable.cat1;
                        break;
                    case R.id.ic2:
                        img = R.drawable.ic_launcher_background;
                        break;
                }
                try{
                    CongViec.setImg(img);
                    CongViec.setName(name);
                    CongViec.setNgay(desc);
                    adapter.add(CongViec);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongViec CongViec = new CongViec();
                String name = eName.getText().toString();
                String desc = eNgay.getText().toString();
                int img = R.drawable.cat1;
                int check = bt1.getCheckedRadioButtonId();
                switch (check){
                    case R.id.ic1:
                        img = R.drawable.cat1;
                        break;
                    case R.id.ic2:
                        img = R.drawable.ic_launcher_background;
                        break;
                }
                try{
                    CongViec.setImg(img);
                    CongViec.setName(name);
                    CongViec.setNgay(desc);
                    adapter.update(pcurr, CongViec);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        image = findViewById(R.id.img);
        recyclerView = findViewById(R.id.recyclerView);
        ic1 = findViewById(R.id.ic1);
        bt1 = findViewById(R.id.bt1);
        ic2 = findViewById(R.id.ic2);
        eName = findViewById(R.id.cv);
        eNgay = findViewById(R.id.d1);
        btNgay = findViewById(R.id.btDate);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    @Override
    public void OnItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr = position;
        CongViec cat = adapter.getItem(position);
        System.out.println("--------------------" + cat.getName());
        int img = cat.getImg();
        if(img == R.drawable.cat1){
            bt1.check(R.id.ic1);
        }else bt1.check(R.id.ic2);
        eName.setText(cat.getName());
        eNgay.setText(cat.getNgay());
    }

    private void filter(String s) {
        List<CongViec> filterlist = new ArrayList<>();
        for(CongViec i: adapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterlist);
        }
    }
}

/*

    isChecked() để kiểm tra trạng thái là checked (true) hay unchecked (false)
        setOnCheckedChangeListener bắt sự kiện khi trạng thái (checked/unchecked) chuyển đổi:
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //b - trạng thái
        //code làm gì khi chuyển trạng thái ở đây
        }
        });
        CheckBox
        Lưu ý khi sử dụng CheckBox nó có các thuộc tính tương tự TextView, Button

        Triển khai CheckBox trong XML
<CheckBox
    android:id="@+id/checkbox_id"
            android:text="Java - Android"
            android:checked="true"  <!--Hoặc "false" -->
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
        Code Java
final CheckBox checkBox = findViewById(R.id.checkbox_id);
//Kiểm tra checked
        if (checkBox.isChecked())
        {
        //Checked
        }
        else
        {
        //Unchecked
        }

//Thiết lập trạng thái check
        boolean toi_chon = true;
        checkBox.setChecked(toi_chon);

//Bắt sự kiện thay đổi trạng thái
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //Code khi trạng thái check thay đổi
        Toast.makeText(
        compoundButton.getContext(),
        compoundButton.getText()+"|"+b,
        Toast.LENGTH_SHORT).show();
        }
        });

//Bắt sự kiện Click
        checkBox.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Toast.makeText(
        view.getContext(), "Click!",
        Toast.LENGTH_SHORT).show();
        }
        });*/
