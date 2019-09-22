package pl.sda.finalproject.utilities;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppUtils {
// sprawdza czy mail i haslo jest poprawne
// sprawdza pattern ktory napisalismy dla hasla i mailu i sprawdza z pStr tym ktory podamy przy wypelnianiu
    public static boolean checkEmailOrPassword(String pattern, String pStr) {

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(pStr);
        return m.matches();
    }
}
