package repository;

import java.util.Currency;
import java.util.List;

public interface Converter {
    Double convert(String from, String to, Double value);
    List<Currency> getAllCurrencies();
    List<String> getAllSymbols(List<Currency> list);

}
