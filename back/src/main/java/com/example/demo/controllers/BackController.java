package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import java.util.ArrayList;

import com.example.demo.dto.Calculo;
import com.example.demo.dto.Curitiba;
import com.example.demo.dto.Current;
import com.example.demo.dto.Reverse;
import com.example.demo.services.ReverseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.atomic.AtomicBoolean;

@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
public class BackController {

    @Autowired
    ReverseService reverseService;

    @GetMapping("/reverse/{word}")
    public Reverse reverse(@PathVariable String word){
        var palavraInvertida = reverseService.reverse(word);
        if(palavraInvertida.equals(word))
            return new Reverse(palavraInvertida, true);
        return new Reverse(palavraInvertida, false);
    }

    @GetMapping("/imaexp")
    public Calculo calculo(Double A, Double b){
        var re = A * Math.sin(b);
        var im = A * Math.cos(b);

        return new Calculo(re, im);
    }

    @GetMapping("/collatz")
    public ResponseEntity<Current> current(Integer current, Integer step){
        if (current < 0 || step < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        for(int i = 0; i < step; i++){
            current = collatz(current);
        }

        return ResponseEntity.ok(new Current(current));
    }

    public Integer collatz(Integer curr){
        if(curr % 2 == 0)
            return curr / 2;
        return 3 * curr + 1;
    }

    @GetMapping("/curitiba")
    public Curitiba curitiba(String cep, String cpf){
        String[] cpf_str = new String[12];

        cpf_str = cpf.split("");

        for(int i = 0; i< cpf_str.length; i++){
            System.out.println(cpf_str[i]);
        }

        ArrayList<String> list = new ArrayList<>();
        list.add(cpf);

        // ------------------ REQUISIÇÃO PARA VALIDAR SE CEP É DE CWB
        // String url = "https://viacep.com.br/ws/" + cep + "/json/";
        // AtomicBoolean isCwb = new AtomicBoolean(false);

        // HttpClient client = HttpClient.newHttpClient();
        // java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
        //     .uri(URI.create(url))
        //     .GET()
        //     .build();

        // client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        //     .thenApply(HttpResponse::body)
        //     .thenApply(responseBody -> {
        //         JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        //         return jsonObject.get("localidade").getAsString();
        //     })
        //     .thenAccept(cidade -> {
        //         if(cidade.equals("Curitiba"))
        //             isCwb.set(true);
        //     })
        //     .join();

        // if(!isCwb.get())
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new Curitiba(list);
    }

}
