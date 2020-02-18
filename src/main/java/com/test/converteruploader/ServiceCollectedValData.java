package com.test.converteruploader;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCollectedValData {
    private String URL = "XML_daily.asp";
    private WebClient webClient = WebClient.builder()
            .baseUrl("http://www.cbr.ru/scripts/")
            .build();

    public void gettingCurrencyData() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println(currentDate);
        Mono<String> res = Mono.just(currentDate).flatMap(e -> webClient.get()
                .uri(UriBuilder -> UriBuilder
                        .path(URL)
                        .queryParam("date_req", currentDate)
                        .build())
                .exchange()
                .flatMap(mono -> mono.bodyToMono(String.class)));
        System.out.println(res.block());
    }
}
