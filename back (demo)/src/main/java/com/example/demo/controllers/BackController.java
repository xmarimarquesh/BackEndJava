package com.example.demo.controllers;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Calculo;
import com.example.demo.dto.Curitiba;
import com.example.demo.dto.Current;
import com.example.demo.dto.PersonDto;
import com.example.demo.dto.Reverse;
import com.example.demo.model.Person;
import com.example.demo.dto.City;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.PersonService;
import com.example.demo.services.ReverseService;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;

// import java.util.concurrent.atomic.AtomicBoolean;
// import org.yaml.snakeyaml.util.ArrayUtils;

// import java.net.URI;
// import java.net.http.HttpClient;
// import java.net.http.HttpResponse;

@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
public class BackController {

    @Autowired
    ReverseService reverseService;
    
    @Autowired
    CityRepository repo_city;

    @Autowired
    PersonService person_service;

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
    public ResponseEntity<Curitiba> curitiba(String cep, String cpf){
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
        
        return ResponseEntity.ok(new Curitiba(mss));
        
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

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCity(){
        List<City> list = new ArrayList<>();

        list = repo_city.findAll()
        .stream()
        .map(city -> new City(city.getCidade(), city.getEstado(), city.getPais()))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/cities/{query}")
    public ResponseEntity<List<City>> getAllCity(@PathVariable String query){
        List<City> list = new ArrayList<>();

        list = repo_city.findByCidadeOrEstadoOrPais(query, query, query)
        .stream()
        .map(city -> new City(city.getCidade(), city.getEstado(), city.getPais()))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public String createAccount(@RequestBody PersonDto person){

        if(person.email() == null || person.password() == null || person.username() == null){
            return "Preencha todos os campos!";
        }

        Boolean temMinuscula = person.password().chars().anyMatch(Character::isLowerCase);
        Boolean temMaiuscula = person.password().chars().anyMatch(Character::isUpperCase);
        Boolean temNumero = person.password().chars().anyMatch(Character::isDigit);
        Boolean temCaractereEspecial = person.password().chars().anyMatch(c -> "!@#$%^&*()_+{}[]|:;,.<>?".indexOf(c) >= 0);

        if(!temMinuscula || !temMaiuscula || !temNumero || !temCaractereEspecial || person.password().length() < 4){
            return "Sua senha deve conter ao menos 4 caracteres entre eles numeros, letras maiusculas, minusculas e caracteres especiais.";
        }

        Person person_model = new Person();
        person_model.setEmail(person.email());
        person_model.setName(person.username());
        person_model.setPass(person.password());

        person_service.savePerson(person_model);

        return "Conta criada com sucesso!";
    }

}
