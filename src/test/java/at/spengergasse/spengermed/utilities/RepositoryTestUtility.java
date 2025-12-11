package at.spengergasse.spengermed.utilities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryTestUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T loadTestData(String path, Class<T> clazz) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));
            return objectMapper.readValue(jsonContent, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from path: " + path, e);
        }
    }

    public static <T> void assertEntityEquals(T actual, T expected) {
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsMatchingRegexes(".*id")
                .ignoringFields("id")
                .isEqualTo(expected);
    }
}
