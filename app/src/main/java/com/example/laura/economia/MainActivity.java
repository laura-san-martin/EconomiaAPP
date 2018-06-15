package com.example.laura.economia;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final List<Obj_Aparelho> aparelhosObjs =  new ArrayList<Obj_Aparelho>();
    public static final List<Obj_Perfil> perdisObjs =  new ArrayList<Obj_Perfil>();

    DadosUsuario alterarUsuario;
    AparelhoView novoAparelho;
    PerfisList perfis;
    AparelhosMenu aparelhos;
    MenuList menuList;

    ImageButton perfis_btn;
    ImageButton aparelhos_btn;
    ImageButton menu_btn;
    ImageButton calculos_btn;

    ImageView perfis_btn_bg;
    ImageView aparelhos_btn_bg;
    ImageView calculos_btn_bg;

    ConstraintLayout popup_layout;
    Button popup_button;
    TextView popup_text;

    ConstraintLayout popup2_layout;
    Button popup2_button1;
    Button popup2_button2;
    TextView popup2_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiCalls api = new ApiCalls();

        try {
            api.getAparelhos();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        aparelhos = new AparelhosMenu();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, aparelhos, "aparelhosTag").commit();
        ShowHideBottomUP(aparelhos, aparelhos_btn, aparelhos_btn_bg);

        perfis = new PerfisList();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, perfis, "perfisTag").commit();
        ShowHideBottomUP(perfis, perfis_btn, perfis_btn_bg);

        menuList = new MenuList();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, menuList, "menuListTag").commit();
        ShowHideTopDown(menuList, menu_btn);

        perfis_btn = (ImageButton) findViewById(R.id.perfis_menu_button);
        aparelhos_btn = (ImageButton) findViewById(R.id.aparelhos_menu_button);
        menu_btn = (ImageButton) findViewById(R.id.main_menu_button);
        calculos_btn = (ImageButton) findViewById(R.id.calculo_menu_button);

        perfis_btn_bg = (ImageView) findViewById(R.id.perfis_menu_bg);
        aparelhos_btn_bg = (ImageView) findViewById(R.id.aparelhos_menu_bg);
        calculos_btn_bg = (ImageView) findViewById(R.id.calculo_menu_bg);

        popup_layout = (ConstraintLayout) findViewById(R.id.main_popup_single_btn);
        popup_text = (TextView) findViewById(R.id.popup_text);
        popup_button = (Button) findViewById(R.id.popup_btn);

        popup2_layout = (ConstraintLayout) findViewById(R.id.main_popup_duo_btn);
        popup2_text = (TextView) findViewById(R.id.popup_duo_text);
        popup2_button1 = (Button) findViewById(R.id.popup_fisrt_btn);
        popup2_button2 = (Button) findViewById(R.id.popup_sec_btn);

    }


    public void openAparelhos(View view)
    {
        if(aparelhos_btn == null) {
            aparelhos_btn_bg = (ImageView) findViewById(R.id.aparelhos_menu_bg);
            aparelhos_btn = (ImageButton) findViewById(R.id.aparelhos_menu_button);
        }

        if(!perfis.isHidden())
            ShowHideBottomUP(perfis, perfis_btn, perfis_btn_bg);
        if(!menuList.isHidden())
            ShowHideTopDown(menuList, menu_btn);

        ShowHideBottomUP(aparelhos, aparelhos_btn, aparelhos_btn_bg);
    }

    public void openPerfis(View view)
    {
        if(perfis_btn == null) {
            perfis_btn_bg = (ImageView) findViewById(R.id.perfis_menu_bg);
            perfis_btn = (ImageButton) findViewById(R.id.perfis_menu_button);
        }

        if(!aparelhos.isHidden())
            ShowHideBottomUP(aparelhos, aparelhos_btn, aparelhos_btn_bg);
        if(!menuList.isHidden())
            ShowHideTopDown(menuList, menu_btn);

        ShowHideBottomUP(perfis, perfis_btn, perfis_btn_bg);
    }

    public void openMenu(View view)
    {
        if(menu_btn == null)
            menu_btn = (ImageButton) findViewById(R.id.main_menu_button);

        if(!aparelhos.isHidden())
            ShowHideBottomUP(aparelhos, aparelhos_btn, aparelhos_btn_bg);
        if(!perfis.isHidden())
            ShowHideBottomUP(perfis, perfis_btn, perfis_btn_bg);

        ShowHideTopDown(menuList, menu_btn);
    }

    public void AbrirAdicionarAparelho(View view)
    {
        novoAparelho = new AparelhoView();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_full, novoAparelho, "novoAparelhoTag").commit();
    }


    public void FecharAdicionarAparelho(View view)
    {
        if(novoAparelho != null) {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            getSupportFragmentManager().beginTransaction().remove(novoAparelho).commit();
        }
    }


    public void FecharAdicionarAparelho()
    {
        View view = (View) (ImageButton) findViewById(R.id.main_menu_button);
        if(novoAparelho != null) {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            getSupportFragmentManager().beginTransaction().remove(novoAparelho).commit();
        }
        popup_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FecharPopup();
            }
        });

    }
    public void AdicionarAparelho(View view) {

        String nome = ((TextView) findViewById(R.id.aparelhoNome)).getText().toString();
        String categoria = ((TextView) findViewById(R.id.aparelhoTipo)).getText().toString();
        String potencia = ((TextView) findViewById(R.id.aparelhoPotencia)).getText().toString();
        String voltagem = ((TextView) findViewById(R.id.aparelhoVoltagem)).getText().toString();

        ApiCalls api = new ApiCalls();

        try {
            api.postAparelho(categoria, nome, "https://www.dasdas.com/example.jpeg", potencia, voltagem, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void AbrirAlterarUsuario(View view)
    {
        novoAparelho = new AparelhoView();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, novoAparelho, "novoAparelhoTag").commit();
    }

    public void FecharAlterarUsuario(View view)
    {
        if(novoAparelho != null) {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            getSupportFragmentManager().beginTransaction().remove(novoAparelho).commit();
        }

    }

    public void  AlterarUsuario(View view)
    {
        boolean sucesso = false;

        String nome = (String) ((TextView) view.findViewById(R.id.userNome)).getText();
        String dosumento = (String) ((TextView) view.findViewById(R.id.userCpf)).getText();
        String endereco = (String) ((TextView) view.findViewById(R.id.userEndereco)).getText();

        if(sucesso)
        {
           AbrirPopup("Dados alterados com sucesso!", "Ok!");

        }else{
            AbrirPopup("Houve um erro ao alterar os dados!", "Fechar!");
        }

        popup2_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FecharPopup();
            }
        });
    }

    public void DeletarUsuario(View view)
    {
        AbrirPopupDuo("Tem certeza que deseja deletar seu usuário?", "Sim", "Não");

        popup2_button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfirmaDeletarUsuario();
            }
        });

        popup2_button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FecharPopupDuo();
            }
        });
    }

    public void ConfirmaDeletarUsuario()
    {
        FecharPopupDuo();
    }

    public void AbrirPopupDuo(String text, String btn1_text, String btn2text)
    {
        popup2_text.setText(text);
        popup2_button1.setText(btn1_text);
        popup2_button2.setText(btn2text);
        popup2_layout.setVisibility(View.VISIBLE);
    }

    public void  FecharPopupDuo()
    {
        popup2_layout.setVisibility(View.INVISIBLE);
    }

    public void AbrirPopup(String text, String btn_text)
    {
        popup_text.setText(text);
        popup_button.setText(btn_text);
        popup_layout.setVisibility(View.VISIBLE);
    }

    public void  FecharPopup()
    {
        popup_layout.setVisibility(View.INVISIBLE);
    }

    private void ShowHideBottomUP(Fragment frag, ImageButton btn, ImageView bg)
    {
        if(frag.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_bottom_up_in, R.anim.slide_bottom_up_out)
                    .show(frag)
                    .commit();

            if(btn != null) {
                btn.getBackground().setColorFilter(getResources().getColor(R.color.verde2), PorterDuff.Mode.SRC_ATOP);
                bg.setBackgroundColor(getResources().getColor(R.color.verde3));
            }
        }else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_bottom_up_in, R.anim.slide_bottom_up_out)
                    .hide(frag)
                    .commit();
            if(btn != null) {
                btn.getBackground().setColorFilter(getResources().getColor(R.color.verde1), PorterDuff.Mode.SRC_ATOP);
                bg.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }
    }

    private void ShowHideTopDown(Fragment frag, ImageButton btn)
    {
        if(frag.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_up_bottom_in, R.anim.slide_up_bottom_out)
                    .show(frag)
                    .commit();
            if(btn != null)
                btn.getBackground().setColorFilter(getResources().getColor(R.color.verde1), PorterDuff.Mode.SRC_ATOP);
        }else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_up_bottom_in, R.anim.slide_up_bottom_out)
                    .hide(frag)
                    .commit();

            if(btn != null)
                btn.getBackground().setColorFilter(getResources().getColor(R.color.verde3), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void ShowHideRightLeft(Fragment frag)
    {
        if(frag.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_right_left_in, R.anim.slide_right_left_out)
                    .show(frag)
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_right_left_in, R.anim.slide_right_left_out)
                    .hide(frag)
                    .commit();
        }
    }

    private void ShowHideLeftRight(Fragment frag)
    {
        if(frag.isHidden()) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_left_right_in, R.anim.slide_left_right_out)
                    .show(frag)
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_left_right_in, R.anim.slide_left_right_out)
                    .hide(frag)
                    .commit();
        }
    }
}
