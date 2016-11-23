package com.example.thiago.pesquisacinetose;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    Draw our;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        our = new Draw(this);
        setContentView(our);
        Intent ourIntent = new Intent(this,Movie.class);
        startActivity(ourIntent);

    }
}
