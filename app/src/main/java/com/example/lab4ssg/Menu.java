package com.example.lab4ssg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Menu extends AppCompatActivity {

    /**
     * Declaración de los checkbox y campos a usar
     */
    CheckBox pozole_rojo;
    CheckBox pozole_verde;
    CheckBox tam_chico;
    CheckBox tam_med;
    CheckBox tam_grande;
    CheckBox ing_repollo;
    CheckBox ing_crema;
    CheckBox ing_rabanos;
    CheckBox ing_limones;
    CheckBox ing_salsa;
    CheckBox ing_toast;

    Button ordenar;

    String platilloPedido = "";
    String tamanoPedido = "";
    String ingredientesPedido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //asiganamos la view de botone_ordenar del activity_menu al boton ordenar
        ordenar = findViewById(R.id.boton_ordenar);

        //asignamos los checkbox de la activity_menu a los checkbox de la clase Menu para poder ser utilizados
        pozole_rojo = findViewById(R.id.checkBox_pozole_rojo);
        pozole_verde = findViewById(R.id.checkBox_pozole_verde);
        tam_chico = findViewById(R.id.checkBox_tamano_chico);
        tam_med = findViewById(R.id.checkBox_tamano_mediano);
        tam_grande = findViewById(R.id.checkBox_tamano_grande);
        ing_salsa = findViewById(R.id.checkBox_ing_salsa);
        ing_crema = findViewById(R.id.checkBox_ing_crema);
        ing_limones = findViewById(R.id.checkBox_ing_limones);
        ing_rabanos = findViewById(R.id.checkBox_ing_rabanos);
        ing_repollo = findViewById(R.id.checkBox_ing_repollo);
        ing_toast = findViewById(R.id.checkBox_ing_tostadas);

        /*
          los siguientes listeners nos permiten limpiar las casillas de elegir
          platillo y tamaño del mismo, cuando seleccionamos una casilla, la otra se limpia
         */
        pozole_rojo.setOnClickListener(view -> pozole_verde.setChecked(false));
        pozole_verde.setOnClickListener(view -> pozole_rojo.setChecked(false));
        tam_chico.setOnClickListener(view -> {
            tam_med.setChecked(false);
            tam_grande.setChecked(false);
        });
        tam_med.setOnClickListener(view -> {
            tam_chico.setChecked(false);
            tam_grande.setChecked(false);
        });
        tam_grande.setOnClickListener(view -> {
            tam_chico.setChecked(false);
            tam_med.setChecked(false);
        });

        /*
          listener en el boton ordenar
          si no hay nigun checkbox seleccionado de platillo y/o tamaño, envia un toast para avisar
          si se cumplen los requisitos se manda a llamaar el metodo que nos permite enviar el pedido por mensaje
         */
        ordenar.setOnClickListener(view -> {
            if (!pozole_rojo.isChecked() && !pozole_verde.isChecked()) {
                displayToast(getString(R.string.selecciona_platillo));
            } else if (!tam_chico.isChecked() && !tam_med.isChecked() && !tam_grande.isChecked()) {
                displayToast(getString(R.string.selecciona_tamaño));
            } else {
                mandarPedidoWhats(elegirPlatillo(), elegirTamano(), cadenaIngredientes());
            }
        });
    }

    /**
     * @param message mensaje en toast (contenido depende de la situacion, los mensajes están en values-strings
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * @param platillo     string con la informacion del platillo elegido
     * @param tamano       string con la informacion del tamaño del platillo elegido
     * @param ingredientes string con las informacion de los ingredientes elegidos
     */
    private void mandarPedidoWhats(String platillo, String tamano, String ingredientes) {
        //String telefonoRestaurante = "524951075008";
        Intent intentWhats = new Intent(Intent.ACTION_SEND);
        //intentWhats.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker"));
        intentWhats.setType("text/plain");
        intentWhats.setPackage("com.whatsapp");
        //intentWhats.putExtra("jid", PhoneNumberUtils.stripSeparators(telefonoRestaurante) + "@s.whatsapp.net");
        intentWhats.putExtra(Intent.EXTRA_TEXT, "Platillo: " + platillo + "\n" +
                "Tamaño del platillo: " + tamano + "\n" +
                "Ingredientes: " + ingredientes);
        try {
            startActivity(intentWhats);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Whatsapp no está instalado", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * metodo para elegir platillo, dependiendo el checkbos seleccionado
     *
     * @return retorna el platillo elegido
     */
    private String elegirPlatillo() {
        if (pozole_rojo.isChecked()) {
            platilloPedido = "Pozole Rojo";
        } else {
            platilloPedido = "Pozole verde";
        }
        return platilloPedido;
    }

    /**
     * metodo para elegir el tamaño del platillo, dependiendo el checkbox seleccionado
     *
     * @return retorna el tamaño del platillo elegido
     */
    private String elegirTamano() {
        if (tam_chico.isChecked()) {
            tamanoPedido = "Chico";
        } else if (tam_med.isChecked()) {
            tamanoPedido = "Mediano";
        } else {
            tamanoPedido = "Grande";
        }
        return tamanoPedido;
    }

    /**
     * metoodo para obtener los ingredientes de los checkbox
     *
     * @return retorna la cadena de ingredientes seleccionados
     */
    private String cadenaIngredientes() {
        if (ing_repollo.isChecked()) {
            ingredientesPedido = ingredientesPedido + "repollo, ";
        }
        if (ing_crema.isChecked()) {
            ingredientesPedido = ingredientesPedido + "crema, ";
        }
        if (ing_rabanos.isChecked()) {
            ingredientesPedido = ingredientesPedido + "rabanos, ";
        }
        if (ing_limones.isChecked()) {
            ingredientesPedido = ingredientesPedido + "limones, ";
        }
        if (ing_salsa.isChecked()) {
            ingredientesPedido = ingredientesPedido + "salsa borracha, ";
        }
        if (ing_toast.isChecked()) {
            ingredientesPedido = ingredientesPedido + "tostadas,";
        }
        return ingredientesPedido;
    }
}