package com.event.eventsync.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for EventSync test project",
                title = "EventSync OpenApi documentation",
                version = "1.0",
                contact = @Contact(
                        name = "Arturas",
                        email = "arpul97@gmail.com",
                        url = "https://github.com/ArturKa97/eventsync"

                )
        )
        ,
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Public ENV",
                        url = "http://eventsync-env.eba-zbnkv3xm.eu-central-1.elasticbeanstalk.com"
                ),
        }

)
public class OpenApiConfig {

}
