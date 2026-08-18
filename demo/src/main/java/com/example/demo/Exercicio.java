package com.example.demo;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Exercicio {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Informe o seu Nome");
        String nome = s.nextLine();

        System.out.println("Informe a Sua Idade");
        Integer idade = s.nextInt();

        if (idade >= 18) {
            System.out.println("Seu Nome:" + nome + " Voce é maior de idade");
        } else {
            System.out.println("Seu Nome:" + nome + " Voce é menor de idade");
        }
    }
}
