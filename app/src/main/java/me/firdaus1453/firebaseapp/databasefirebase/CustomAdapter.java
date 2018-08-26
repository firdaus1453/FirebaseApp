package me.firdaus1453.firebaseapp.databasefirebase;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import me.firdaus1453.firebaseapp.R;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Hewan> HewanArrayList;
    DatabaseReference reference;
    Firebase firebase;
    private LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Hewan> HewanArrayList, DatabaseReference reference, Firebase firebase) {
        this.c = c;
        this.reference = reference;
        this.firebase = firebase;
        this.HewanArrayList = HewanArrayList;
    }

    @Override
    public int getCount() {
        return HewanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
        }
        MyHolder holder = new MyHolder(convertView);
        holder.nameText.setText(HewanArrayList.get(position).getNama());
        holder.infoText.setText(HewanArrayList.get(position).getDetail());
        PicassoClient.downloading(c, HewanArrayList.get(position).getUrlgambar(), holder.img);
        holder.infoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedelete(position);
            }
        });

        return convertView;
    }

    private void updatedelete(int position) {
        final Dialog dialog = new Dialog(c);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.updatedelete_layout, null);
        dialog.setContentView(view);
        final EditText nameEditext = (EditText) dialog.findViewById(R.id.nameEditText);
        final EditText urlEditext = (EditText) dialog.findViewById(R.id.urlEditText);
        final EditText infoEditext = (EditText) dialog.findViewById(R.id.infoEditText);
        Button btnupdate = (Button) dialog.findViewById(R.id.updateBtn);
        Button btndelete = (Button) dialog.findViewById(R.id.deleteBtn);
        final String name = HewanArrayList.get(position).getNama();
        final String info = HewanArrayList.get(position).getDetail();
        final String url = HewanArrayList.get(position).getUrlgambar();

        final String id = HewanArrayList.get(position).getIdhewan();
        nameEditext.setText(name);
        urlEditext.setText(url);
        infoEditext.setText(info);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hewan m = new Hewan(id, nameEditext.getText().toString(),infoEditext.getText().toString(), urlEditext.getText().toString());
                Toast.makeText(c, "idhewan" + id, Toast.LENGTH_SHORT).show();
//                FirebaseClient.updatedata(nameEditext.getText().toString(),infoEditext.getText().toString(),urlEditext.getText().toString());
                reference.child(id).setValue(m);
                Toast.makeText(c, "Updated Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference idd = FirebaseDatabase.getInstance().getReference("hewan");
                idd.child(id).removeValue();
                Toast.makeText(c, "deleted successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();   
    }

    private class MyHolder {
        public TextView nameText,infoText;
        public ImageView img;
        public MyHolder(View view){
            nameText = (TextView)view.findViewById(R.id.nameTxt);
            infoText = (TextView)view.findViewById(R.id.infoTxt);
            img = (ImageView)view.findViewById(R.id.beachimage);

        }
    }
}
