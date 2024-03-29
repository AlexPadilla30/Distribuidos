package com.example.pistolero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pistolero.common.Constantes;

public class LoginActivity extends AppCompatActivity {
    EditText etNick;
    Button btnStart;
    String nick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Eventos: evento click
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nick = etNick.getText().toString();

                if(nick.isEmpty()){
                    etNick.setError("El nombre de usuario es obligatorio");
                }else if(nick.length()< 3) {
                    etNick.setError(("Debe tener al menos 3 caracteres"));
                }else{
                    etNick.setText("");
                    Intent i = new Intent(LoginActivity.this, GameActivity.class);
                    i.putExtra(Constantes.EXTRA_NICK, nick);
                    startActivity(i);
                }
            }
        });
    }
}
