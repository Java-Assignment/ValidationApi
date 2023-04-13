package com.abhi.Validation.externalsvc.formuladatasvc;

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
public class FormulaRefDataSvc {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private URI uri;


    public FormulaRefDataSvc() {
        uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9034/formula/").build().toUri();

    }

    public FormulaDataDTO getByFileNumber(String fileNumber){
        WebClient webClient=webClientBuilder.build();

        FormulaDataDTO formulaDataDTO =webClient.get()
                .uri(uri+fileNumber)
                .exchangeToMono(
                        response->{
                            if(response.statusCode().is2xxSuccessful()){
                                return  response.bodyToMono(FormulaDataDTO.class);
                            }else if(response.statusCode().equals(HttpStatus.NOT_FOUND)){
                                return Mono.empty();
                            }
                            else {
                                return  response.createException().flatMap(Mono::error);
                            }

                        }).block();
        log.info("Get FileData by fileNumber"+ formulaDataDTO);
        return formulaDataDTO;
    }

}
