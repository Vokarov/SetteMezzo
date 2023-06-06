package Spring;

import game.SetteMezzoApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import service.*;
@PropertySource("config.properties")
@Configuration
public class AppConfig {

    @Bean
    DeckService deckService(){
        return new DeckServiceNapoletaneImpl();
    }
    @Bean
    SetteMezzoService setteMezzoService(DeckService deckService){
        return  new SetteMezzoServiceImpl(deckService);
    }
    /*
    * # SetteMezzoServiceImpl ->        Il banco NON conosce la tua carta coperta
    *                                   Attua un semplice algoritmo per decidere se pescare o meno
    *
    *
    * SetteMezzoServiceImplPerdente ->  Il banco conosce la tua carta coperta
    *                                   Il banco pesca fino a quando ti supera
    * */

    @Bean
    SetteMezzoApp setteMezzoApp(SetteMezzoService setteMezzoService){
        return new SetteMezzoApp(setteMezzoService);
    }


}
