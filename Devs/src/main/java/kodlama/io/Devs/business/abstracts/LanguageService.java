package kodlama.io.Devs.business.abstracts;

import kodlama.io.Devs.business.requests.LanguageRequest;
import kodlama.io.Devs.business.responses.LanguageResponse;
import kodlama.io.Devs.entities.concretes.Language;

import java.util.List;

public interface LanguageService {

    List<LanguageResponse> getALL();
    LanguageResponse getResponseById(int id);
    Language getById(int id);
    void add(LanguageRequest languageRequest) throws Exception;
    void delete(int id);
    void update(LanguageRequest languageRequest, int id) throws Exception;
}
