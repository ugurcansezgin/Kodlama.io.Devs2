package kodlama.io.Devs.business.concretes;

import kodlama.io.Devs.business.abstracts.LanguageService;
import kodlama.io.Devs.business.requests.LanguageRequest;
import kodlama.io.Devs.business.responses.LanguageResponse;
import kodlama.io.Devs.dataAccess.abstracts.LanguageRepository;
import kodlama.io.Devs.entities.concretes.Language;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
    public LanguageResponse getResponseById(Long id){
        Optional<Language > language = languageRepository.findById(id);
        if(language.isPresent() ){
            LanguageResponse languageResponse = new LanguageResponse();
            languageResponse.setName(language.get().getName());
            languageResponse.setId(language.get().getId());
            return languageResponse;
        }
        return null;
    }

    @Override
    public Language getById(Long id){
    Optional<Language> language = languageRepository.findById(id);

        return language.get();
    }

    @Override
    public void add(LanguageRequest languageRequest) throws Exception {
        checkNameValid(languageRequest.getName());
        Language language = new Language();
        language.setName(languageRequest.getName());
        languageRepository.save(language);
    }

    @Override
    public void delete(Long id){

        languageRepository.deleteById(id);
    }

    @Override
    public void update(LanguageRequest languageRequest, Long id) throws Exception {
        checkNameValid(languageRequest.getName());

        Language language = getById(id);
        if (Objects.nonNull(language)){
            language.setName(languageRequest.getName());
            languageRepository.save(language);
        }

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
