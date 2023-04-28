package com.example.lab4ssg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    /**
     * declaracion buttons y radioButtons de la clase
     */
    Button llamada;
    RadioGroup radioGroup;
    String textNumeroUno = "";
    String textNumeroDos = "";
    String textNumeroTres = "";
    RadioButton numeroUno;
    RadioButton numeroDos;
    RadioButton numeroTres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos por medio del id los views correspondientes
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        llamada = findViewById(R.id.boton_llamar);
        numeroUno = (RadioButton) findViewById(R.id.numero_uno);
        numeroDos = (RadioButton) findViewById(R.id.numero_dos);
        numeroTres = (RadioButton) findViewById(R.id.numero_tres);
        textNumeroUno = (String) numeroUno.getText();
        textNumeroDos = (String) numeroDos.getText();
        textNumeroTres = (String) numeroTres.getText();


        /*
          listener en el boton de llamar
          según el radioButton seleccionado, se envia un numero distinto para marcar
         */
        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroUno = (RadioButton) findViewById(R.id.numero_uno);
                numeroDos = (RadioButton) findViewById(R.id.numero_dos);
                numeroTres = (RadioButton) findViewById(R.id.numero_tres);
                if (radioGroup.getCheckedRadioButtonId() == numeroUno.getId()) {
                    llamarANumero(textNumeroUno);
                } else if (radioGroup.getCheckedRadioButtonId() == numeroDos.getId()) {
                    llamarANumero(textNumeroDos);
                } else if (radioGroup.getCheckedRadioButtonId() == numeroTres.getId()) {
                    llamarANumero(textNumeroTres);
                } else {
                    displayToast(getString(R.string.selecciona_numero));
                }
            }
        });

    }

    public void launchMenuActivity(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    /**
     * @param numeroRecibido numero obtenido de los radio button
     */
    public void llamarANumero(String numeroRecibido) {
        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numeroRecibido));
        startActivity(intentCall);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}