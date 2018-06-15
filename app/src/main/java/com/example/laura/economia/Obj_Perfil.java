package com.example.laura.economia;

public class Obj_Perfil {

    public Integer id;
    public Integer id_usuario;
    public String tipo;
    public String nome;

    public Obj_Perfil(Integer id,
                      Integer id_usuario,
                      String tipo,
                      String nome)
    {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.id_usuario = id_usuario;

    }
}
