package com.example.sec03;

import com.example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class Lec10FluxMono {

    public static void main(String[] args) {

        fluxToMono();



    }


    private static void monoToFlux(int userId){
        var mono = getUsername(userId);
        save(Flux.from(mono));
    }

    private static void fluxToMono(){
        var flux = Flux.range(1, 10);

        Mono.from(flux)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("same");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
