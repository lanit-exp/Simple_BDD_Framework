package utils;

import authorization.Authorization;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Deserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Deserializer.class);

    public Authorization yamlDeserialize(String path) {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Authorization auth = null;
        try {
            auth = mapper.readValue(new File(path), Authorization.class);
        } catch (IOException e) {
            LOGGER.info("По ссылке: '{}' файл YAML не найден", path);
            e.printStackTrace();
        }
        return auth;
    }

    public Authorization jsonDeserialize(String path) {

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.findAndRegisterModules();
        Authorization auth = null;
        try {
            auth = mapper.readValue(new File(path), Authorization.class);
        } catch (IOException e) {
            LOGGER.info("По ссылке: '{}' файл JSON не найден", path);
            e.printStackTrace();
        }
        return auth;
    }
}
