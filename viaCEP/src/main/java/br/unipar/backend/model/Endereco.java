package br.unipar.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Endereco {
    String cep;
    String logradouro;
    String complemento;
    String unidade;
    String bairro;
    String localidade;
    String uf;
    String estado;
    String regiao;
    String ibge;
    String gia;
    String ddd;
    String siafi;

    @Override
    public String toString() {
        return "Endereco {" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
