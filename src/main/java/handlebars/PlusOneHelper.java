package handlebars;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class PlusOneHelper implements Helper<Integer> {

    @Override
    public CharSequence apply(Integer context, Options options) {
        final int plusOne = context + 1;
        return String.valueOf(plusOne);
    }
}
