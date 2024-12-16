package ec.ary.uce.interfaces;

import ec.ary.uce.util.Record;

public interface Payment {

    String sendPayment(Record record);
    String sendPayment(String from, String to, String message);
}
