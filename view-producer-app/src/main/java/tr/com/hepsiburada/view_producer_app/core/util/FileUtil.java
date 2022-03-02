package tr.com.hepsiburada.view_producer_app.core.util;

import org.springframework.core.io.Resource;

import java.io.IOException;

public class FileUtil {

    public static String getAbsolutePathFromResource(Resource resource) {
        try {
            return resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
