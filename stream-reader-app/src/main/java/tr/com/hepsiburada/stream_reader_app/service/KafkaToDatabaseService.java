package tr.com.hepsiburada.stream_reader_app.service;

public interface KafkaToDatabaseService<I> {
    void convertAndSave(I inputData);
}
