package com.example.laura.economia;

import android.app.Activity;

import org.json.*;

import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

class ApiCalls {

    public void postUsuario(String nome, String doc, String email, String senha, String endereco, String data, final LoginActivity view) throws JSONException {

        JSONObject jsonobj = new JSONObject();

        RequestParams param = new RequestParams();

        param.add("name", nome);
        param.add("cpf", doc);
        param.add("mail", email);
        param.add("password", senha);
        param.add("pass_conf", senha);
        param.add("address", endereco);
        param.add("birth_date", data);

        ApiConnection.post("users/create", param, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                view.ShowPopUp("Usuário inserdido com sucesso!");
                System.out.println(response);
            }
        });
    }

    public void postAparelho(String tipo, String nome, String foto, String voltagem, String potencia, final MainActivity view) throws JSONException {

        JSONObject jsonobj = new JSONObject();

        RequestParams param = new RequestParams();

        param.add("name", nome);
        param.add("description", tipo);
        param.add("voltage", voltagem);
        param.add("photo_url", foto);
        param.add("power_in_use", String.valueOf(Integer.parseInt(potencia)));
        param.add("power_in_standby", String.valueOf(Integer.parseInt(potencia)));

        ApiConnection.post("appliances", param, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                view.AbrirPopup("Aparelho inserdido com sucesso!", "OK");
                view.FecharAdicionarAparelho();
                System.out.println(response);
            }
        });
    }

    public void getAparelhos() throws JSONException {

        ApiConnection.get("appliances", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray jsonAuthorsArray = (JSONArray) response.get("appliances");
                    System.out.println(jsonAuthorsArray);

                    if(jsonAuthorsArray.length() > 0) {
                        List<Obj_Aparelho> aparelhos = new ArrayList<Obj_Aparelho>();

                        for (int i = 0; i < jsonAuthorsArray.length(); i++) {

                            JSONArray atributos = (JSONArray) jsonAuthorsArray.get(i);
                            aparelhos.add(new Obj_Aparelho(atributos.getInt(0), atributos.getString(1), atributos.getString(2),atributos.getString(3), atributos.getString(4), atributos.getInt(5),atributos.getInt(6)));
                        }

                         MainActivity.aparelhosObjs.clear();
                        MainActivity.aparelhosObjs.addAll(aparelhos);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getUser(String usuario, String senha, final LoginActivity view) throws JSONException {

        ApiConnection.get("users/login/" + usuario + "/" + senha, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                view.ShowPopUp("Bem Vindo!");
            }
        });
    }
}