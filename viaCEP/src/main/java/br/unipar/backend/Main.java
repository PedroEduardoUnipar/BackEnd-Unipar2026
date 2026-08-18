package br.unipar.backend;

import br.unipar.backend.model.Endereco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner s = new Scanner(System.in);
        System.out.println("Informe o CEP");
        String cep = s.next();
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        Endereco endereco = new Endereco();


        try {

            URL apiUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
            conn.setRequestMethod("GET");


            BufferedReader leitor = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String linha;

            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null){
                String[] dados = linha.split("\"");

                if(dados.length >=4)
                {String atributo = dados[1];
                String valor = dados[3];

                switch(atributo){
                    case "cep":
                        endereco.getCep();
                        break;
                    case "logradouro" :
                        endereco.getLogradouro();
                        break;
                    case "complemento":
                        endereco.getComplemento();
                        break;
                    case "bairro":
                        endereco.getBairro();
                        break;
                    case "uf":
                        endereco.getUf();
                        break;
                    case "erro":
                        System.out.println("Cep nao Encontrado");
                        break;
                }}
            }
            leitor.close();





        }catch (Exception e){
            System.out.println("Algo de errado nao deu certo"+e.getMessage());
        }

    }
    private static String extrairCampo(String json, String campo) {
        try {
            String chave = "\"" + campo + "\": \"";
            int inicio = json.indexOf(chave) + chave.length();
            int fim = json.indexOf("\"", inicio);
            return json.substring(inicio, fim);
        } catch (Exception e) {
            return "";
        }
    }

}
