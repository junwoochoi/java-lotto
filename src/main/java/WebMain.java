import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebMain {
    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> {
            return render("/index.html");
        });
        post("/buyLotto", (req, res) -> {
            return render("/index.html");
        });
    }

    private static String render(String templatePath) {
        return render(null, templatePath);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
