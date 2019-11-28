package com.example.pistolero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.pistolero.common.Constantes;

public class GameActivity extends AppCompatActivity {
    TextView tvTimer, tvNick;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //initViewConponentes();
        //eventos();
        //initPantalla();
        initCuentaAtras();

        //Extras : obtener nick y setear en el Text View
        Bundle extras = getIntent().getExtras();
        String nick = extras.getString(Constantes.EXTRA_NICK);
        tvNick.setText(nick);
    }

    //Contador para que se inicie el juego
    private void initCuentaAtras() {
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long segundosRestantes = millisUntilFinished / 1000;
                tvTimer.setText(segundosRestantes +"s");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("START!");
                //poneer aqui el contador del tiempo y un evento para que el
                //dispositivo vibre o emita algun sonido

                //GAME OVER
                gameOver = true;
                mostrarMarcadorGameOver();

            }
        }.start();
    }

    //Este metodo es para crear un cuadro de dialogo donde se despliegue el Score del Jugador
    private void mostrarMarcadorGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //CAMBIAR LA VARIABLE 'COUNTER' POR LOS VALORES DEL ACELEROMETRO y/o CONTADOR
        builder.setMessage("Que reaccion, has logrado disparar en: + 'counter' + segundos")
                .setTitle("GAME OVER");
        builder.setCancelable(false);

        //eventos de los botones
        builder.setPositiveButton("Reinicar", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                //El usuario hace click en OK

                //reiniciar puntuacion
                gameOver =false;
                initCuentaAtras();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //El usuario hace click en SALIR
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
