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
        String cep = "85901270";
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
                        endereco.setCep(valor);
                        break;
                    case "logradouro" :
                        endereco.setLogradouro(valor);
                        break;
                    case "complemento":
                        endereco.setComplemento(valor);
                        break;
                    case "bairro":
                        endereco.setBairro(valor);
                        break;
                    case "uf":
                        endereco.setUf(valor);
                        break;
                    case "estado":
                        endereco.setUf(valor);
                        break;
                    case "ddd":
                        endereco.setUf(valor);
                        break;
                    case "localidade":
                        endereco.setLocalidade(valor);
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
        System.out.println(endereco);
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
