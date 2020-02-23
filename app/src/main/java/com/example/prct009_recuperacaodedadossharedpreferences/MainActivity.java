package com.example.prct009_recuperacaodedadossharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Precisa de instanciar o objeto datepicker para o podermos usar na classe MainActivity.
        final DatePicker data = (DatePicker) findViewById(R.id.datePicker);
        DatePicker.OnDateChangedListener onDateChangedListener = null;
        // Data de início que o nosso calendário será iniciado é em 01 de janeiro de 1985.
        // Os meses começam a ser contatos do 0, ou seja, janeiro será 0, fevereiro 1, etc..
        data.init(1985,0,1,onDateChangedListener);

        // Programando a ação do botão que será responsável por chamar o layout com as informações,
        // que foram salvas no date picker com o SharedPreferences.
        Button bt1 = (Button) findViewById(R.id.btnExibir);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Assim que o Botão for clicado, será guardado os valores que o utilizador
                //introduziu no datepicker nas sharedpreferences.

                                                                // info é a chave de acesso.
                SharedPreferences dados = getSharedPreferences("info",0);
                SharedPreferences.Editor editor = dados.edit(); // Criamos o editor para receber os dados.

                int dia = data.getDayOfMonth();
                editor.putInt("dia",dia); // Aqui armazenamos o dia que foi informado no sharedPreferences
                int mes = data.getMonth(); // Aqui armazenamos o mês que foi informado no sharedPreferences
                editor.putInt("mes",mes);
                int ano = data.getYear(); // Aqui armazenamos o ano que foi informado no sharedPreferences
                editor.putInt("ano",ano);
                editor.apply();

                // Após toda informação recebida e salva, teremos que invocamos a classe
                // Mostra_Dados, que irá recuperar os dados guardados nas sharedpreferences.
                // Para isso, temos que criar um objeto do tipo Intent e declarar a nova
                // classe no Android manifest.
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MostraDados.class);
                startActivity(intent);
            }
        });

    }
}
