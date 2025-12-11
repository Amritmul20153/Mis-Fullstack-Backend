# Anleitung

## Vorraussetzungen

Ressource bereits implementiert

## Schritte

### 1. Controller und Repository erstellen

<details>
  <summary>Erklärung zu Basiskontroller</summary>

Anstatt für jede Ressource einen eigenen Controller zu erstellen, kann ein Basiskontroller erstellt werden, der die
CRUD-Operationen für alle Ressourcen bereitstellt. Der Basiskontroller kann dann von den spezifischen Controllern geerbt
werden.
Das funktioniert, weil die Methoden in der Basisklasse generisch sind und die spezifischen Modelklassen in den
spezifischen Controllern angegeben werden.
</details>
<details>
    <summary>Integration in andere Projekte</summary>

Basecontroller von [hier](src/main/java/at/spengergasse/spengermed/controller/BaseController.java) kopieren und in das
Projekt einfügen

</details>

1. Wie gewohnt Repository erstellen

```java
package at.spengergasse.spengermed.repository;

import at.spengergasse.spengermed.model.Example;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

public interface ExampleRepository extends CrudRepository<Example, String> {
}

```

2. Controller erstellen

```java
package at.spengergasse.spengermed.controller;

import at.spengergasse.spengermed.model.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example") // Hier den Endpunkt anpassen
public class ExampleController extends BaseController<Example> {
    public ExampleController(ExampleRepository repository) {
        super(repository);
    }
}

```

### 2. Automatisierte SQL-Statements erstellen für Import.sql

<details>
    <summary>Erklärung</summary>
Da Spring auch nur mit SQL-Statements arbeitet können wir uns die Arbeit erleichtern und die SQL-Statements für import.sql automatisiert erstellen lassen. Jedoch sind die SQL-Statements nicht direkt aus den Beispieldaten zu entnehmen. Es handelt sich um sog. PreparedStatements, bei denen die Werte durch Platzhalter ersetzt werden.
Mithilfe des SqlParsers können die SQL-Statements aus den SQL-Logs generiert werden. Dieser befindet sich im Projekt unter `src/main/java/at/spengergasse/spengermed/util/SqlParser.java`.
Mit Hilfe der SQL-Logs können die SQL-Statements für die `import.sql` Datei erstellt werden. Dazu wird die Beispielsressource in den Demodaten erstellt und das Programm ausgeführt. Anschließend werden die SQL-Logs kopiert und mithilfe des SQL-Parsers in SQL-Statements umgewandelt.
</details>
<details>
    <summary>Integration in andere Projekte</summary>

- Order util von [hier](src/main/java/at/spengergasse/spengermed/util) kopieren und in das Projekt einfügen.

- SQL Logs in application.properties aktivieren

```properties
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.orm.jdbc.bind=trace
logging.level.org.springframework=debug
logging.level.org.springframework.jdbc.datasource.init.ScriptUtils=DEBUG
logging.log4j2.config.override=classpath:log4j2.xml
```

</details>

1. Im Ordner `src/main/resources/demodata` einen neuen Ordner erstellen der genauso heißt wie der API Endpunkt im
   Controller. (Das was nach `/api/` kommt im `RequestMapping`).
2. In diesem Ordner ein neues JSON-File erstellen
3. In diesem File die Beispiel Ressource einfügen (Hinweis: Bei einem langen div ist es ratsam das mit einem leeren
   String "" zu ersetzen, da es sonst zu Problemen kommen kann)
4. Programm ausführen
5. Nun können wir mithile der SQL Logs die in application.properties aktiviert wurden, die SQL Statements
   kopieren.
    1. Ganze Beispielsressource kopieren
    2. In der Konsole mit `Strg + F` nach dieser Ressource suchen
    3. Nach unten scrollen bis `Committing JPA transaction on EntityManager [SessionImpl]` kommt
    4. Den gesamten Bereich zwischen `Committing JPA transaction on EntityManager [SessionImpl]`
       und `Completed 201 CREATED` kopieren
6. Diese Logs werden nun mithilfe des `SQLParser` in SQL Statements umgewandelt und in die `import.sql` Datei kopiert.
    1. [SqlParser](src/main/java/at/spengergasse/spengermed/util/SqlParser.java) starten
    2. Die SQL Logs in die Konsole kopieren
    3. END eingeben und Enter drücken
    4. Die SQL Statements werden nun in der Konsole ausgegeben
    5. Diese Statements in die `import.sql` Datei kopieren
    6. Falls davor das div rausgelöscht wurde, muss das div wieder hinzugefügt werden
7. Ordner in demodata löschen

### 3. Test erstellen

<details>
    <summary>Erklärung</summary>
Um die Tests zu vereinfachen wird anstatt eine Beispielsressource selber zu insanzieren, durch den Objekt-Mapper ein Objekt mithilfe eines JSON File erstellt. Die Nötige Logik für die Tests wurde 
größtenteils in einer Abstrakten und Statischen Klasse ausgelagert. Diese Klasse ist unter `src/test/java/at/spengergasse/spengermed/utilities/` zu finden.
</details>

<details>
    <summary>Integration in andere Projekte</summary>

Ordner utilities von [hier](src/test/java/at/spengergasse/spengermed/utilities) kopieren und in das Projekt einfügen.


</details>

1. Im Ordner `src/test/resources` ein neues File erstellen `example_test_data.json`
2. In diesem File die Beispielsressource einfügen
3. Repository Test erstellen

```java
package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Example;
import at.spengergasse.spengermed.repository.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.assertEntityEquals;
import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ExampleRepositoryTest {

    @Autowired
    private ExampleRepository exampleRepository;

    private static final String TEST_DATA_PATH = "src/test/resources/example_test_data.json";
    private Example testExample;

    @BeforeEach
    public void setUp() {
        testExample = loadTestData(TEST_DATA_PATH, Example.class);
    }

    @Test
    @Transactional
    public void testSaveLoadOneExample() {
        Example savedExample = exampleRepository.save(testExample);
        String id = savedExample.getId();
        Example loadedExample = exampleRepository.findById(id).orElseThrow();
        assertEntityEquals(loadedExample, savedExample);
    }

    @Test
    @Transactional
    public void testDeleteOneExample() {
        Example savedExample = exampleRepository.save(testExample);
        exampleRepository.deleteById(savedExample.getId());
        boolean exists = exampleRepository.existsById(savedExample.getId());
        assertFalse(exists);
    }

    @Test
    @Transactional
    public void testUpdateOneExample() {
        Example savedEmptyExample = exampleRepository.save(new Example());
        String savedId = savedEmptyExample.getId();
        BeanUtils.copyProperties(testExample, savedEmptyExample);
        savedEmptyExample.setId(savedId);
        exampleRepository.save(savedEmptyExample);
        Example loadedExample = exampleRepository.findById(savedEmptyExample.getId()).orElseThrow();
        assertEntityEquals(loadedExample, testExample);
    }

}

```

4. Test ausführen
5. Controller Test erstellen

```java
package at.spengergasse.spengermed;

import at.spengergasse.spengermed.model.Example;
import at.spengergasse.spengermed.repository.ExampleRepository;
import at.spengergasse.spengermed.utilities.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static at.spengergasse.spengermed.utilities.RepositoryTestUtility.loadTestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class ExampleControllerTest extends AbstractControllerTest {
    @Autowired
    ExampleRepository exampleRepository;
    private static final String TEST_DATA_PATH = "src/test/resources/example_test_data.json"; // Hier den Pfad anpassen
    private final Example testExample = loadTestData(TEST_DATA_PATH, Example.class);
    private final String endpoint = "/api/example"; // Hier den Endpunkt anpassen

    @Test
    public void testGetAllExamples() throws Exception {
        performGetRequest(endpoint);
    }

    @Test
    public void testGetAExample() throws Exception {
        String id = exampleRepository.save(testExample).getId();
        performGetRequest(endpoint + "/" + id);
    }

    @Test
    @Transactional
    public void testPostAExample() throws Exception {
        String jsonContent = objectMapper.writeValueAsString(testExample);
        performPostRequest(endpoint, jsonContent);
    }

    @Test
    @Transactional
    public void testPutAExample() throws Exception {
        String id = exampleRepository.save(new Example()).getId();
        String jsonContent = objectMapper.writeValueAsString(testExample);
        String response = performPutRequest(endpoint + "/" + id, jsonContent);
        assertEquals(response, jsonContent);
    }

    @Test
    @Transactional
    public void testDeleteAExample() throws Exception {
        String id = testExample.getId();
        performDeleteRequest("/api/example/" + id);
    }
}

```

6. Test ausführen

