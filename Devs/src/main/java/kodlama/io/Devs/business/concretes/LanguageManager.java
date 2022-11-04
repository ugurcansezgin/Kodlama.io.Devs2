package kodlama.io.Devs.business.concretes;

import kodlama.io.Devs.business.abstracts.LanguageService;
import kodlama.io.Devs.business.requests.LanguageRequest;
import kodlama.io.Devs.business.responses.LanguageResponse;
import kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.Devs.entities.concretes.Language;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LanguageManager implements LanguageService {

    LanguageRepository languageRepository;

    public LanguageManager(LanguageRepository languageRepository){

        this.languageRepository = languageRepository;
    }

    @Override
    public List<LanguageResponse> getALL(){

        List<Language> languages = languageRepository.findAll();
        List<LanguageResponse> responses = new ArrayList<>();
        for (Language language : languages){
            LanguageResponse responseItem = new LanguageResponse();
            responseItem.setId(language.getId());
            responseItem.setName(language.getName());
            responses.add(responseItem);
        }
        return responses;
    }

    @Override
    public LanguageResponse getResponseById(int id){
        Language language = languageRepository.findById(id);
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setName(language.getName());
        languageResponse.setId(language.getId());
        return languageResponse;
    }

    @Override
    public Language getById(int id){

        return languageRepository.findById(id);
    }

    @Override
    public void add(LanguageRequest languageRequest) throws Exception {
        checkNameValid(languageRequest.getName());
        Language language = new Language();
        language.setName(languageRequest.getName());
        languageRepository.save(language);
    }

    @Override
    public void delete(int id){

        languageRepository.deleteById(id);
    }

    @Override
    public void update(LanguageRequest languageRequest, int id) throws Exception {
        checkNameValid(languageRequest.getName());
        Language language = languageRepository.findById(id);
        language.setName(languageRequest.getName());
        languageRepository.save(language);
    }

    private void checkNameValid(String name) throws Exception {

        Language isExist = languageRepository.findByName(name);
        if (isExist != null){
            throw new Exception("Bu dil mevcut, tekrar girilemez!");
        }
        if (name.isBlank()){
            throw new Exception("İsim boş olamaz!");
        }
    }

}
