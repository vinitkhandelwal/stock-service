package com.microservice.stock.stockservice.resource;



import com.microservice.stock.stockservice.model.StockValue;
import com.microservice.stock.stockservice.model.UserStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stockService")
public class StockController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value ="/getStock/{user}",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<StockValue> getStock(@PathVariable("user") final String user){
        HashMap<Class,ParameterizedTypeReference> paramTypeRefMap = new HashMap() ;

        paramTypeRefMap.put(UserStock.class, new ParameterizedTypeReference<List<UserStock>>(){} );
        ParameterizedTypeReference parameterizedTypeReference = paramTypeRefMap.get(UserStock.class);
        ResponseEntity<List<UserStock>> userStocks = restTemplate.exchange("http://MONGO-SERVICE/userService/getUserStock/" + user, HttpMethod.GET, null, parameterizedTypeReference);

    List<StockValue> stocs =  userStocks.getBody()
                .stream()
                .map(this::getStock)
                .collect(Collectors.toList());


    return stocs;

    }

    @RequestMapping(value = "/getUserStock/{userName}", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean getAllUserStock(@PathVariable("userName") String userName){
        return true;
    }

    private StockValue getStock(UserStock userStock){

            return new StockValue(userStock.getStock());

    }

}
