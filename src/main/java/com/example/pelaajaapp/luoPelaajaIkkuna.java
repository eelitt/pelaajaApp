package com.example.pelaajaapp;

        import androidx.appcompat.app.AppCompatActivity;

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
        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.widget.ToggleButton;


        import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity implements Serializable {
    private static final String FILE_NAME = "pelaajalista.srl";
   ToggleButton mmrToggleButton;
    EditText asetammrLaatikko;
    EditText nimiLaatikko;
    EditText temploadlaatikko;


    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luo_pelaaja_ikkuna);
    }

    public void showTextField(View view) {

        mmrToggleButton = findViewById(R.id.MmrToggleButton);
        asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);
        ;
        asetammrLaatikko.setVisibility(VISIBLE);
        counter++;
        if (counter == 2) {
            asetammrLaatikko.setVisibility(View.INVISIBLE);
            counter = 0;
        }
    }
public void save(Pelaaja player)
{
    ObjectOutput out = null;
try{
    out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+FILE_NAME));
    out.writeObject(player);
    out.flush();
    Toast.makeText(this, "Saved to "+ getFilesDir()+ "/" + FILE_NAME, Toast.LENGTH_LONG).show();

    nimiLaatikko.getText().clear();
    out.close();

} catch (IOException e) {
    e.printStackTrace();
}

}

public void load(View view) {
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    Pelaaja player = null;

    try {

        fis = new FileInputStream(getFileStreamPath(FILE_NAME));
        ois = new ObjectInputStream(fis);
        player = (Pelaaja) ois.readObject();
        temploadlaatikko = (EditText) findViewById(R.id.loadLaatikko);
        StringBuilder sb = new StringBuilder();


            sb.append("Nimi: ");
            sb.append(player.getNimi());
            sb.append(" Rating: ");
            sb.append(player.getMmr());
            //String pelaajaArvot = player.getNimi();
            temploadlaatikko.setText(sb.toString());

        } catch(IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }finally{
            if (ois != null || fis != null) {
                try {

                    ois.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}
    public void luoPelaaja(View view) {

        mmrToggleButton = findViewById(R.id.MmrToggleButton);
        nimiLaatikko = findViewById(R.id.pelaajaNimiLaatikko);
        asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);

        Pelaaja player = new Pelaaja();
        String pelaajaNimi = nimiLaatikko.getText().toString();
        player.setNimi(pelaajaNimi);

        if (mmrToggleButton.isChecked()) {
            String value = asetammrLaatikko.getText().toString();
            int finalValue = Integer.parseInt(value);
            player.setMmr(finalValue);

        } else{
        player.setMmr(1000);
        }
        save(player);



    }

}


