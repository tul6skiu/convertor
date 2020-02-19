package com.test.converteruploader.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.test.converteruploader.model.ValCurs;
import com.test.converteruploader.model.Valute;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;


import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCollectedValData {
    private String URL = "XML_daily.asp";
    private WebClient webClient = WebClient.builder()
            .baseUrl("http://www.cbr.ru/scripts/")
            .build();

    public Mono<String> gettingCurrencyData() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String currentDate = simpleDateFormat.format(new Date());

        Mono<String> res = Mono.just(currentDate).flatMap(e -> webClient.get()
                .uri(UriBuilder -> UriBuilder
                        .path(URL)
                        .queryParam("date_req", currentDate)
                        .build())
                .exchange()
                .flatMap(mono -> mono.bodyToMono(String.class)));
        return res;
    }

    public ValCurs whenJavaGotFromXmlStr(String xmlResult) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        ValCurs valCurs = xmlMapper.readValue(xmlResult, ValCurs.class);
        List<Valute> valutes = valCurs.getValute();
        for (Valute valute : valutes) {
            valute.setValCurs(valCurs);
        }
        valCurs.setValute(valutes);
        return valCurs;
    }
}
