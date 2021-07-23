import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Formater {
	public static LocalDateTime getLocalDateTimeFromString(String input) {
        return LocalDateTime.parse(input, Config.date_format);
    }

    public static long convertValuetoDoubleValue(String inputNumber){
        double interimVal = Double.parseDouble(inputNumber);
        interimVal *= 100;
        return (long) interimVal;
    }

    public static String convertCentValueToDollar(long centValue){
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(centValue / 100.0);
    }

}
