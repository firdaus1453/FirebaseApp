package me.firdaus1453.firebaseapp.databasefirebase;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseClient {
    Firebase firebase;
    Context c;
    String DB_URL;
    ListView listView;
    DatabaseReference reference;
    private CustomAdapter customAdapter;
    ArrayList<Hewan> HewanArrayList = new ArrayList<>();


    public FirebaseClient(DatabaseFirebaseActivity databaseFirebaseactivity, String db_url, ListView listview, DatabaseReference reference) {
    c=databaseFirebaseactivity;
    DB_URL=db_url;
    this.listView=listview;
    this.reference=reference;
    Firebase.setAndroidContext(c);
    firebase = new Firebase(DB_URL);
    }

    public void insertdata(String namaHewan,String detailHewan,String urlgambar){
        Hewan m = new Hewan();
        String id = reference.push().getKey();
        m.setIdhewan(id);
        m.setNama(namaHewan);
        m.setDetail(detailHewan);
        m.setUrlgambar(urlgambar);
        reference.child(id).setValue(m);
    }

    private void getUpdates(final com.firebase.client.DataSnapshot dataSnapshot) {
        HewanArrayList.clear();
        for (com.firebase.client.DataSnapshot ds : dataSnapshot.getChildren()) {
            Hewan h = new Hewan();

            h.setIdhewan(ds.getValue(Hewan.class).getIdhewan());
            h.setNama(ds.getValue(Hewan.class).getNama());
            h.setDetail(ds.getValue(Hewan.class).getDetail());
            h.setUrlgambar(ds.getValue(Hewan.class).getUrlgambar());
            HewanArrayList.add(h);

        }
        if (HewanArrayList.size() > 0) {
            customAdapter = new CustomAdapter(c,HewanArrayList,reference,firebase);

            listView.setAdapter((ListAdapter) customAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(c, "test aja", Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(c, "no data", Toast.LENGTH_SHORT).show();
        }

    }

    public void refreshdata() {
        firebase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        } );
    }


}
