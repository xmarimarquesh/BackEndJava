package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CuritibaDto;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/curitiba")
public class Curitiba {

    @GetMapping
    public ResponseEntity<CuritibaDto> curitiba(String cep, String cpf){
        String[] cpf_str = cpf.split("");

        Integer[] cpf_int = new Integer[cpf_str.length - 2]; 

        for (int i = 0; i < cpf_str.length - 2; i++) {
            cpf_int[i] = Integer.parseInt(cpf_str[i]);
        }

        int soma = 0;
        for (int i = 1; i < cpf_int.length; i++) {
            soma += cpf_int[i-1] * i;
        }

        int d1 = soma % 11;

        if(d1 == 10){
            d1 = 0;
        }

        soma = 0;
        for (int i = 0; i < cpf_int.length; i++) {
            soma += cpf_int[i] * i;
        }

        int d2 = soma % 11;

        if(d2 == 10){
            d2 = 0;
        }

        // ArrayList<String> list = new ArrayList<>();
        String mss = new String();
        
        if(cpf_str[10].equals(String.valueOf(d1)) && cpf_str[11].equals(String.valueOf(d2))){
            // list.add("CPF CORRETO!");
            mss = "CPF CORRETO!";
        }else{
            // list.add("CPF INCORRETO!");
            mss = "CPF INCORRETO!";
        }
        
        // list.add("Ta");
        
        return ResponseEntity.ok(new CuritibaDto(mss));
        
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

    }
}
