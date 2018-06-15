package com.example.laura.economia;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    ConstraintLayout popup_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        popup_layout = (ConstraintLayout) findViewById(R.id.main_popup_single_btn);

    }

    public  void AdicionarUsuario(View view)
    {
        ApiCalls api = new ApiCalls();

        try {
            String nome = ((TextView) findViewById(R.id.userNome)).getText().toString();
            String cpf = ((TextView) findViewById(R.id.userDoc)).getText().toString();
            String email = ((TextView) findViewById(R.id.userEmail)).getText().toString();
            String senha = ((TextView) findViewById(R.id.senha)).getText().toString();
            String end = ((TextView) findViewById(R.id.userEnde)).getText().toString();

            api.postUsuario(nome, cpf, email, senha, end, "2000/01/01", this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void ShowPopUp(String message)
    {
        Button btn = (Button) findViewById(R.id.popup_btn);
        TextView text = (TextView) findViewById(R.id.popup_text);
        popup_layout = (ConstraintLayout) findViewById(R.id.main_popup_single_btn);



        text.setText(message);
        btn.setText("OK!");
        popup_layout.setVisibility(View.VISIBLE);

            btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FecharPopUp(true);
            }
        });
    }

    public void FecharPopUp(Boolean passou)
    {
        if(passou)
        {
            popup_layout.setVisibility(View.INVISIBLE);
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }else {
            popup_layout.setVisibility(View.INVISIBLE);
        }
    }

    public void Logar(View view)
    {
        Veriricar();
    }

    public void MostrarNovoUsuario(View view)
    {
        ((ConstraintLayout)findViewById(R.id.pop_up_novo_usuario)).setVisibility(View.VISIBLE);
    }

    public void FecharNovoUsuario(View view)
    {
        ((ConstraintLayout)findViewById(R.id.pop_up_novo_usuario)).setVisibility(View.INVISIBLE);
    }

    private void Veriricar()
    {
        ApiCalls api = new ApiCalls();

        try {
            api.getUser( ((TextView) findViewById(R.id.email)).getText().toString(), ((TextView) findViewById(R.id.senha)).getText().toString(), this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
