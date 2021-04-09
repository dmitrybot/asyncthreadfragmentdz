package ru.realityfamily.automattask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import ru.realityfamily.automattask.Models.Automat;
import ru.realityfamily.automattask.Models.Student;

public class MainFragment extends Fragment {
    private MainFragment instance;
    private String name;
    TextView autoName1;

    TextView autoStatus1;

    TextView clientId1;

    TextView autoCart1;

    TextView autoQueue11;
    TextView autoQueue12;
    
    public MainFragment getInstance() {
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        autoCart1 = view.findViewById(R.id.autoCart1);

        autoName1 = view.findViewById(R.id.autoName1);

        autoStatus1 = view.findViewById(R.id.autoStatus1);

        clientId1 = view.findViewById(R.id.clientId1);

        autoQueue11 = view.findViewById(R.id.autoQueue11);
        autoQueue12 = view.findViewById(R.id.autoQueue12);

        autoName1.setText(name);
        return view;

    }

    public void UpdateData(Automat automat, Student student) {
        if (automat.getStatus() == Automat.AutomatStatus.Waiting) {
            autoStatus1.setText(automat.getStatus().toString());
            clientId1.setText("");
            autoCart1.setText("");
            autoQueue11.setText("");
            //autoQueue12.setText(CalculateQueue(1));
        } else {
            autoStatus1.setText(automat.getStatus().toString());
            clientId1.setText(student.getName());
            autoCart1.setText(student.getCart().toString());
            autoQueue11.setText("= " + student.CartCost() + " у.е.");
            //autoQueue12.setText(CalculateQueue(1));
        }
    }
}
