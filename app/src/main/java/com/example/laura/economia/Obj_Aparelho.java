package com.example.laura.economia;

public class Obj_Aparelho {

    public Integer id;
    public String tipo;
    public String nome;
    public String foto;
    public String voltagem;
    public Integer potencia;
    public Integer potencia_em_standby;


    public Obj_Aparelho(Integer id,
                        String tipo,
                        String nome,
                        String foto,
                        String voltagem,
                        Integer potencia,
                        Integer potencia_em_standby)
    {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.foto = foto;
        this.potencia = potencia;
        this.potencia_em_standby = potencia_em_standby;
        this.voltagem = voltagem;
    }
}
