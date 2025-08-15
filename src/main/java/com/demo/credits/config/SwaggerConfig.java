package com.demo.credits.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API CREDITS",
                description = """
                        This API manages credit applications. It allows creating a new credit application,
                         automatically evaluating its status based on credit score and income-to-loan ratio,
                          and retrieving existing applications by their unique ID.
                        """,
                version = "1.0.0",
                contact = @Contact(
                        name = "Jose Gregorio Perez Manosalva"
                )
        )
)
public class SwaggerConfig {
}
