package org.augustoocc.exceptions;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class NotWritableEx extends RuntimeException {

    @Inject
    DateTimeFormatter logtimestamp;

    public NotWritableEx() {
    }

    public NotWritableEx(String message) {
        super(message);
    }

    public NotWritableEx nullValues(String info) {
        return new NotWritableEx("The object has null values, " + info + LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));
    }

    public NotWritableEx panacheFailure(String info) {
        return new NotWritableEx("Panache failed, " + info + LocalDateTime.now(ZoneOffset.UTC).format(logtimestamp));
    }
}
