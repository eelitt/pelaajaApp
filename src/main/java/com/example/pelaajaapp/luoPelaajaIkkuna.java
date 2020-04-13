package com.example.pelaajaapp;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import java.io.BufferedReader;
        import java.io.EOFException;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutput;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.List;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;

        import android.widget.EditText;
        import android.widget.Toast;
        import android.widget.ToggleButton;


        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity {
    //private static final String FILE_NAME = "pelaajalista.srl";
    ArrayList<Pelaaja> pelaajalista = new ArrayList<Pelaaja>();
   ToggleButton mmrToggleButton;
    EditText asetammrLaatikko;

    EditText temploadlaatikko;
    RecyclerView listalaatikko;
    private RecyclerView.Adapter adapteri;
    private RecyclerView.LayoutManager layoutManager;


    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_pelaaja_ikkuna);

        listalaatikko = findViewById(R.id.listaLaatikko);
        listalaatikko.setHasFixedSize(true);
       layoutManager = new LinearLayoutManager(this);
        adapteri = new MyAdapter(pelaajalista);
        listalaatikko.setLayoutManager(layoutManager);
        listalaatikko.setAdapter(adapteri);

    }

    public void showTextField(View view) {


        asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);
        asetammrLaatikko.setVisibility(VISIBLE);
        counter++;
        if (counter == 2) {
            asetammrLaatikko.setVisibility(View.INVISIBLE);
            counter = 0;
        }
    }
    public void save(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pelaajalista);
        editor.putString("task lista", json);
        editor.apply();
    }
    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pelaajalista", null);
        Type type = new TypeToken<ArrayList<Pelaaja>>() {}.getType();
        pelaajalista = gson.fromJson(json, type);

        if(pelaajalista == null){
            pelaajalista = new ArrayList<>();
        }
    }

/*public void save(Pelaaja player)
{

    //ObjectOutput out = null;
try{
    FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE | MODE_APPEND);

    ObjectOutput out = new ObjectOutputStream(fos);
    out.writeObject(player);
    out.flush();
    Toast.makeText(this, "Saved to "+ getFilesDir()+ "/" + FILE_NAME, Toast.LENGTH_LONG).show();

    nimiLaatikko.getText().clear();


} catch (IOException e) {
    e.printStackTrace();
}

}

public void load(View view) {

    Pelaaja player;

    try {
        FileInputStream fis = openFileInput(FILE_NAME);
        //ObjectInputStream ois = new ObjectInputStream(fis);
        StringBuilder sb = new StringBuilder();
        Log.i("avail",String.valueOf(fis.available()));
        while(fis.available() >= 0){
            ObjectInputStream ois = new ObjectInputStream(fis);
            player = (Pelaaja) ois.readObject();
            sb.append("nimi: ");
            sb.append(player.getNimi());
            sb.append(" rating: ");
            sb.append(player.getMmr());
            sb.append(" ");

            temploadlaatikko = (EditText) findViewById(R.id.loadLaatikko);
            temploadlaatikko.setText(sb.toString());
            Log.i("while read file", String.valueOf(sb));

        }
        fis.close();

        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

}*/

    public void luoPelaaja(View view) {

        Pelaaja ApuOlio = new Pelaaja();
        int mmr;
        mmrToggleButton = findViewById(R.id.MmrToggleButton);

        EditText nimiLaatikko = findViewById(R.id.pelaajaNimiLaatikko);
        String nimi = nimiLaatikko.getText().toString();

        if (mmrToggleButton.isChecked()) {
            String apu = asetammrLaatikko.getText().toString();
            mmr = Integer.parseInt(apu);
        } else{
            mmr = ApuOlio.getMmr();
        }

        lisaaPelaaja(nimi, mmr);

        nimiLaatikko.getText().clear();
        if(mmrToggleButton.isChecked()) {
            asetammrLaatikko.getText().clear();
            mmrToggleButton.setChecked(false);
            asetammrLaatikko.setVisibility(View.INVISIBLE);
        }
    }
    private void lisaaPelaaja(String name, int ratinki){

        pelaajalista.add(new Pelaaja(name, ratinki));
        Log.i("maara", String.valueOf(pelaajalista.size()));
        adapteri.notifyItemInserted(pelaajalista.size());
    }


}


