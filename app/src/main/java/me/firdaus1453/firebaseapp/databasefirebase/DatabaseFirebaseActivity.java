package me.firdaus1453.firebaseapp.databasefirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.firebaseapp.R;

public class DatabaseFirebaseActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;

    DatabaseReference reference;
    String DB_URL = "https://fir-android-11a2f.firebaseio.com/";
    FirebaseClient firebaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_firebase);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reference = FirebaseDatabase.getInstance().getReference("hewan");

        firebaseClient = new FirebaseClient(this, DB_URL,listview, reference);
        firebaseClient.refreshdata();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.customdialog_layout);
        dialog.setTitle("save data hewan");
        final EditText edtnama = (EditText) dialog.findViewById(R.id.nameEditText);
        final EditText edturl = (EditText) dialog.findViewById(R.id.urlEditText);
        final EditText edtinfo = (EditText) dialog.findViewById(R.id.infoEditText);
        Button btnsave = (Button) dialog.findViewById(R.id.saveBtn);
        Button btncancel = (Button) dialog.findViewById(R.id.cancelBtn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseClient.insertdata(edtnama.getText().toString(),edtinfo.getText().toString(),edturl.getText().toString());
                dialog.dismiss();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
