package com.abhi.Validation.externalsvc.ruledatasvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class RuleOneDataSvc {
    @Autowired
    private WebClient.Builder webClientBuilder;

    private URI uri;


    public RuleOneDataSvc() {
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9035/rule/ruleOne/").build().toUri();

    }

    public DataDTO RuleOneCheck(String fileNumber){
        WebClient webClient=webClientBuilder.build();

        DataDTO dataDTO =webClient.get()
                .uri(uri+fileNumber)
                .exchangeToMono(
                        response->{
                            if(response.statusCode().is2xxSuccessful()){
                                return  response.bodyToMono(DataDTO.class);
                            }else if(response.statusCode().equals(HttpStatus.NOT_FOUND)){
                                return Mono.empty();
                            }
                            else {
                                return  response.createException().flatMap(Mono::error);
                            }

                        }).block();
        log.info("The data fetched for the fileNumber is"+ dataDTO);
        return dataDTO;
    }
}
