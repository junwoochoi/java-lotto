package handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.cache.GuavaTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CustomHandlebarsTemplateEngine extends TemplateEngine {

    protected Handlebars handlebars;

    public CustomHandlebarsTemplateEngine() {
        this("/templates");
    }

    public CustomHandlebarsTemplateEngine(String resourceRoot) {
        final TemplateLoader templateLoader = new ClassPathTemplateLoader();
        templateLoader.setPrefix(resourceRoot);
        templateLoader.setSuffix(null);

        this.handlebars = new Handlebars(templateLoader);
        this.handlebars.registerHelper("length", new LengthHelper());
        this.handlebars.registerHelper("plusOne", new PlusOneHelper());

        // Set Guava cache.
        Cache<TemplateSource, Template> cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000).build();

        handlebars = handlebars.with(new GuavaTemplateCache(cache));
    }

    @Override
    public String render(ModelAndView modelAndView) {
        try {
            String viewName = modelAndView.getViewName();
            Template template = handlebars.compile(viewName);
            return template.apply(modelAndView.getModel());
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
    }
}
