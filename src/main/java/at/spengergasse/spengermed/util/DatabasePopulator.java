package at.spengergasse.spengermed.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


@Component
public class DatabasePopulator implements CommandLineRunner {


    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080/api/";
    private static final String BASE_PATH = "src/main/resources/demodata/";

    @Override
    public void run(String... args) {
        try {
            processDirectories(Paths.get(BASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processDirectories(Path basePath) throws IOException {
        try (Stream<Path> paths = Files.walk(basePath, 1)) {
            paths.filter(Files::isDirectory)
                    .filter(path -> !path.equals(basePath))
                    .forEach(this::processEntityDirectory);
        }
    }

    private void processEntityDirectory(Path entityDir) {
        String entity = entityDir.getFileName().toString();
        try (Stream<Path> files = Files.list(entityDir)) {
            files.filter(file -> file.toString().endsWith(".json"))
                    .forEach(file -> postJsonFile(file, entity));
        } catch (IOException e) {
            System.err.println("Error processing directory: " + entityDir + "; " + e.getMessage());
        }
    }

    private void postJsonFile(Path jsonFilePath, String entity) {
        try {
            String content = Files.readString(jsonFilePath);
            String endpoint = baseUrl + entity + "/";
            postContent(content, endpoint, jsonFilePath);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + jsonFilePath + "; " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to post to endpoint: " + entity + "; " + e.getMessage());
        }
    }

    private void postContent(String content, String endpoint, Path jsonFilePath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(content, headers);
        restTemplate.postForObject(endpoint, requestEntity, String.class);
        System.out.println(jsonFilePath.toString().split("demodata") + " Sucessfully posted to " + endpoint);
    }
}

