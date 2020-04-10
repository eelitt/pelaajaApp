package com.example.pelaajaapp;

        import androidx.appcompat.app.AppCompatActivity;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.ObjectOutput;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import static android.view.View.VISIBLE;

public class luoPelaajaIkkuna extends AppCompatActivity implements Serializable {
    private static final String FILE_NAME = "pelaajalista.srl";
    Button mmrToggleButton;
    EditText asetammrLaatikko;
    EditText nimiLaatikko;


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
    out.close();

} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}

}

    public void luoPelaaja(View view) {

        nimiLaatikko = findViewById(R.id.pelaajaNimiLaatikko);
        asetammrLaatikko = findViewById(R.id.asetaMmrLaatikko);

        Pelaaja player = new Pelaaja();
        String pelaajaNimi = nimiLaatikko.getText().toString();
        player.setNimi(pelaajaNimi);

        if (mmrToggleButton.isEnabled() == true) {
            String value = asetammrLaatikko.getText().toString();
            int finalValue = Integer.parseInt(value);
            player.setMmr(finalValue);

        } else{
        player.setMmr(1000);
        }


    }

}

