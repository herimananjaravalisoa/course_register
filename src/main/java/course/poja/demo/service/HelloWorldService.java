package course.poja.demo.service;

import static java.io.File.createTempFile;

import course.poja.demo.file.bucket.BucketComponent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HelloWorldService {
  private final BucketComponent bucketComponent;

  @SneakyThrows
  public String uploadHelloWorldMessage(String name) {
    var fileSuffix = ".txt";
    var filePrefix = "Hello-world-" + name;
    var bucketKey = filePrefix + fileSuffix;
    var fileToUpload = createTempFile(filePrefix, fileSuffix);
    writeMessageIntoFile("Hello World " + name + " !", fileToUpload);
    bucketComponent.upload(fileToUpload, bucketKey);
    return bucketComponent.presign(bucketKey, Duration.ofMinutes(5)).toString();
  }

  private void writeMessageIntoFile(String message, File file) throws IOException {
    FileWriter writer = new FileWriter(file);
    writer.write(message);
    writer.close();
  }
}
