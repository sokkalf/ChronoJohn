package no.opentech.chronojohn.filters;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by: Christian Lønaas
 * Date: 29.12.11
 * Time: 23:47
 */

/* lifted from here :
    http://tech.chitgoks.com/2011/06/27/android-set-min-max-value-an-edittext-accepts/
 */
public class InputFilterMinMax implements InputFilter {

    private int min, max;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}