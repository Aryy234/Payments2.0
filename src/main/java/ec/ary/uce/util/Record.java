package ec.ary.uce.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Record {

    private String from;
    private String to;
    private String message;

    public Record() {
    }

    @PostConstruct
    public void init() {
        this.from = "from@example.com";
        this.to = "to@example.com";
        this.message = "message";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Record{from='%s', to='%s', message='%s'}", from, to, message);
    }
}