import Spring.AppConfig;
import game.SetteMezzoApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SetteMezzoService;
import service.SetteMezzoServiceImpl;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SetteMezzoApp setteMezzoApp =context.getBean(SetteMezzoApp.class);

        setteMezzoApp.game();

        context.close();
    }
}