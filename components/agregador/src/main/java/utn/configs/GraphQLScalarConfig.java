package utn.configs;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.scalars.ExtendedScalars;

import java.time.LocalDateTime;

@Configuration
public class GraphQLScalarConfig {

    @Bean
    public RuntimeWiringConfigurer localDateTimeScalar() {
        GraphQLScalarType localDateTimeScalar = GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Scalar para java.time.LocalDateTime")
                .coercing(new Coercing<LocalDateTime, String>() {

                    @Override
                    public String serialize(Object dataFetcherResult) {
                        return ((LocalDateTime) dataFetcherResult).toString();
                    }

                    @Override
                    public LocalDateTime parseValue(Object input) {
                        return LocalDateTime.parse(input.toString());
                    }

                    @Override
                    public LocalDateTime parseLiteral(Object input) {
                        if (input instanceof StringValue s) {
                            return LocalDateTime.parse(s.getValue());
                        }
                        return null;
                    }
                })
                .build();

        return wiring -> wiring.scalar(localDateTimeScalar);
    }
}
