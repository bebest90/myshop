package com.example.myshop.utils;

import com.example.myshop.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String query){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "SwN79E3EreYX9y0Yuatg");
        headers.add("X-Naver-Client-Secret", "j7jQFeyKFZ");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+ query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);
        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result){
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("items");
        List<ItemDto> ret = new ArrayList<>();
        for (int i=0; i<items.length(); i++){
            JSONObject jsonObject = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(jsonObject);
            ret.add(itemDto);
        }
        return ret;
    }

}