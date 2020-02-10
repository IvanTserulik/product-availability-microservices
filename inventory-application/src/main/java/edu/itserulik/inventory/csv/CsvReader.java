package edu.itserulik.inventory.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

@Slf4j
@Component
public class CsvReader {

    public <T> Iterator<T> getIteratorFromCsv(String fileName, Class<T> type) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            return mapper.readerFor(type).with(bootstrapSchema).readValues(file);
        } catch (IOException e) {
            log.error("File {} not  found", fileName, e);
            return Collections.emptyIterator();
        }
    }

    public <T> Flux<T> getObjectsFromCsv(String fileName, Class<T> type) {
        return Flux.fromIterable(() -> getIteratorFromCsv(fileName, type));
    }
}
