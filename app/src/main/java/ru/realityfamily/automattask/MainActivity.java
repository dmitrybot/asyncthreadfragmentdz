package ru.realityfamily.automattask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MainActivity instance;

    TextView autoName1;
    TextView autoName2;
    TextView autoName3;
    TextView autoName4;

    TextView autoStatus1;
    TextView autoStatus2;
    TextView autoStatus3;
    TextView autoStatus4;

    TextView clientId1;
    TextView clientId2;
    TextView clientId3;
    TextView clientId4;

    TextView autoCart1;
    TextView autoCart2;
    TextView autoCart3;
    TextView autoCart4;

    TextView autoQueue11;
    TextView autoQueue12;

    TextView autoQueue21;
    TextView autoQueue22;

    TextView autoQueue31;
    TextView autoQueue32;

    TextView autoQueue41;
    TextView autoQueue42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        autoCart1 = findViewById(R.id.autoCart1);
        autoCart2 = findViewById(R.id.autoCart2);
        autoCart3 = findViewById(R.id.autoCart3);
        autoCart4 = findViewById(R.id.autoCart4);

        autoName1 = findViewById(R.id.autoName1);
        autoName2 = findViewById(R.id.autoName2);
        autoName3 = findViewById(R.id.autoName3);
        autoName4 = findViewById(R.id.autoName4);

        autoStatus1 = findViewById(R.id.autoStatus1);
        autoStatus2 = findViewById(R.id.autoStatus2);
        autoStatus3 = findViewById(R.id.autoStatus3);
        autoStatus4 = findViewById(R.id.autoStatus4);

        clientId1 = findViewById(R.id.clientId1);
        clientId2 = findViewById(R.id.clientId2);
        clientId3 = findViewById(R.id.clientId3);
        clientId4 = findViewById(R.id.clientId4);

        autoQueue11 = findViewById(R.id.autoQueue11);
        autoQueue12 = findViewById(R.id.autoQueue12);

        autoQueue21 = findViewById(R.id.autoQueue21);
        autoQueue22 = findViewById(R.id.autoQueue22);

        autoQueue31 = findViewById(R.id.autoQueue31);
        autoQueue32 = findViewById(R.id.autoQueue32);

        autoQueue41 = findViewById(R.id.autoQueue41);
        autoQueue42 = findViewById(R.id.autoQueue42);
    }

    public MainActivity getInstance() {
        return instance;
    }
}