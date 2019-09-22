package pl.sda.finalproject.validation;

public class ValidationApp {

    public static final String TAK = "tak";

    public static final String NIE = "nie";

    // sprawdza
    public static final String EMAIL_PATTERN = "^[a-zA-z0-9]+[\\._a-zA-Z0-9]*@[a-zA-Z0-9]+{2,}\\.[a-zA-Z]{2,}[\\.a-zA-Z0-9]*$";
    //sprawdza czy haslo sklada sie z malych i duzych liter ,czy ma specjalne znaki , czy nie ma spacji i czy ma od 8-12 znakow
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$";

}

