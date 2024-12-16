package ec.ary.uce.util;


import ec.ary.uce.interfaces.Payment;
import ec.ary.uce.annotations.QualifierNotification;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierNotification("creditcard")
public class CreditCardPayment implements Payment {

    @Override
    public String sendPayment(Record record) {
        return "Credit card payments" + record.toString();
    }

    @Override
    public String sendPayment(String from, String to, String message) {
        return "";
    }
}