package ru.realityfamily.automattask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.realityfamily.automattask.Models.Automat;
import ru.realityfamily.automattask.Models.Student;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    MainFragment automatView1;
    MainFragment automatView2;
    MainFragment automatView3;
    MainFragment automatView4;
    FragmentTransaction ft;
    boolean run = true;
    List<Automat> automatList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    LinearLayout gor1, gor2, gor3, gor4, ver1, ver2;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gor1 = findViewById(R.id.gor1);
        gor2 = findViewById(R.id.gor2);
        gor3 = findViewById(R.id.gor3);
        gor4 = findViewById(R.id.gor4);
        ver1 = findViewById(R.id.vert1);
        ver2 = findViewById(R.id.vert2);
        instance = this;
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        automatView1 = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", "Автомат №1");
        automatView1.setArguments(bundle);

        automatView2 = new MainFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", "Автомат №2");
        automatView2.setArguments(bundle2);

        automatView3 = new MainFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("name", "Автомат №3");
        automatView3.setArguments(bundle3);

        automatView4 = new MainFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("name", "Автомат №4");
        automatView4.setArguments(bundle4);

        ft.add(R.id.Framelayout1, automatView1);
        ft.add(R.id.Framelayout2, automatView2);
        ft.add(R.id.Framelayout3, automatView3);
        ft.add(R.id.Framelayout4, automatView4);
        ft.commit();

        for (int i = 1; i < 5; i++) {
            automatList.add(
                    new Automat(i)
            );
        }

        for (int i = 1; i < 21; i++) {
            studentList.add(
                    new Student(
                            i,
                            new Random().nextInt(5) + 3,
                            automatList.get(new Random().nextInt(automatList.size()))
                    )
            );
        }

        for(Student student : studentList) {
            student.StartTask2();
        }
    }

    public void UpdateData(Automat automat, Student student) {
        if (run) {
            switch (automat.getName()) {
                case 1:
                    automatView1.UpdateData(automat, student);
                    break;
                case 2:
                    automatView2.UpdateData(automat, student);
                    break;
                case 3:
                    automatView3.UpdateData(automat, student);
                    break;
                case 4:
                    automatView4.UpdateData(automat, student);
                    break;
            }
        }
    }
    /*
    public String CalculateQueue(int automatName) {
        int queue = 0;
        for (Student student : studentList) {
            if (student.getAutomatName() == automatName && student.getTaskStatus() == AsyncTask.Status.RUNNING) queue++;
        }
        return queue - 1 > 0 ? queue - 1 + " человек" : "Людей больше нет.";
    }*/

    public static MainActivity getInstance() {
        return instance;
    }

    public void swap(View v){
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.remove(automatView1);
        ft.remove(automatView2);
        ft.remove(automatView3);
        ft.remove(automatView4);
        ft.commit();
        fm.executePendingTransactions();
        ft = fm.beginTransaction();
        switch(v.getId()){
            case R.id.Framelayout1:
                if (ver2.getVisibility() == View.VISIBLE) {
                    ft.add(R.id.Framelayout1, automatView1);
                    gor2.setVisibility(View.GONE);
                    ver2.setVisibility(View.GONE);
                } else {
                    gor2.setVisibility(View.VISIBLE);
                    ver2.setVisibility(View.VISIBLE);
                    ft.add(R.id.Framelayout1, automatView1);
                    ft.add(R.id.Framelayout2, automatView2);
                    ft.add(R.id.Framelayout3, automatView3);
                    ft.add(R.id.Framelayout4, automatView4);
                }
                break;
            case R.id.Framelayout2:
                if (ver2.getVisibility() == View.VISIBLE) {
                    ft.add(R.id.Framelayout2, automatView2);
                    gor1.setVisibility(View.GONE);
                    ver2.setVisibility(View.GONE);
                } else {
                    gor1.setVisibility(View.VISIBLE);
                    ver2.setVisibility(View.VISIBLE);
                    ft.add(R.id.Framelayout1, automatView1);
                    ft.add(R.id.Framelayout2, automatView2);
                    ft.add(R.id.Framelayout3, automatView3);
                    ft.add(R.id.Framelayout4, automatView4);
                }
                break;
            case R.id.Framelayout3:
                if (ver1.getVisibility() == View.VISIBLE) {
                    ft.add(R.id.Framelayout3, automatView3);
                    gor4.setVisibility(View.GONE);
                    ver1.setVisibility(View.GONE);
                } else {
                    gor4.setVisibility(View.VISIBLE);
                    ver1.setVisibility(View.VISIBLE);
                    ft.add(R.id.Framelayout1, automatView1);
                    ft.add(R.id.Framelayout2, automatView2);
                    ft.add(R.id.Framelayout3, automatView3);
                    ft.add(R.id.Framelayout4, automatView4);
                }
                break;
            case R.id.Framelayout4:
                if (ver1.getVisibility() == View.VISIBLE) {
                    ft.add(R.id.Framelayout4, automatView4);
                    gor3.setVisibility(View.GONE);
                    ver1.setVisibility(View.GONE);
                } else {
                    gor3.setVisibility(View.VISIBLE);
                    ver1.setVisibility(View.VISIBLE);
                    ft.add(R.id.Framelayout1, automatView1);
                    ft.add(R.id.Framelayout2, automatView2);
                    ft.add(R.id.Framelayout3, automatView3);
                    ft.add(R.id.Framelayout4, automatView4);
                }
                break;
        }
        ft.commit();
    }
}