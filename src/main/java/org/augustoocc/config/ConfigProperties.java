package org.augustoocc.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.Produces;
import java.time.format.DateTimeFormatter;

public class ConfigProperties {

    @Produces
    DateTimeFormatter dateTimeFormatter () {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    }


}
