package com.example.prct009_recuperacaodedadossharedpreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MostraDados extends Activity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apresenta);

        // Instanciando os obejtos do tipo Textview que irão receber as informações dos dias faltantes e da data.
        TextView aniv = (TextView)findViewById(R.id.dataAniv);
        TextView dias = (TextView)findViewById(R.id.diasFaltantes);

        // Recuperando os recursos, para isso, usamos as chaves de acesso que informamos no SharedPreferences na classe MainActivity
        SharedPreferences dados = getSharedPreferences("info",0);
        int dia = dados.getInt("dia",0);
        int mes = dados.getInt("mes",0)+1;
        int ano = dados.getInt("ano",0);

        // Passando a data para a variável aniv, a mesma será responsável por exibir na tela.
        aniv.setText(dia + "/" + mes + "/" + ano);

        // Realizar a conta para determinar quantos dias faltam para o aniversário .
        // Criaremos duas instâncias no calendário.
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Abaixo iremos obter a data atual do sistema operacional.
        int anoatual = cal2.get(Calendar.YEAR);
        int mesatual = cal2.get(Calendar.MONTH);
        int diaatual = cal2.get(Calendar.DAY_OF_MONTH);

        // Para evitar que a data fique negativa, caso o aniversário já tenha passado, precisamos
        // realizar a verificação abaixo:

        if(mesatual > mes - 1){
            anoatual = anoatual + 1;
        }if (mesatual == mes -1 & diaatual > dia){
            anoatual = anoatual + 1 ;
        }

        // Definir a próxima data de aniversário, tendo com base os dados recuperados.
        cal1.set(anoatual,mes-1,dia);

        // Para realizarmos a comparação das datas, precisamos recuperar as mesmas em miliseconds.
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        // Calcular a diferença entre as datas.
        long diff = (milis1 - milis2);

        // Cálculo para converter a diferença de milisegundos para dia.
        long diffDays = diff / (24 * 60 * 60 * 1000);

        // Testar
        Toast.makeText(getApplicationContext(),"Número de dias até o seu aniversário é de " + diffDays,Toast.LENGTH_LONG).show();
        dias.setText("" + diffDays);

    }
}
