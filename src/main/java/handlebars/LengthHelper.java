package handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.util.List;

public class LengthHelper implements Helper<List<String>> {

    @Override
    public Object apply(List<String> context, Options options) {
        if (context == null || context.isEmpty()) {
            return 0;
        }
        return context.size();
    }
}
