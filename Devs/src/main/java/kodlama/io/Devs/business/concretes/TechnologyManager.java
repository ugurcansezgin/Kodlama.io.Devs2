package kodlama.io.Devs.business.concretes;

import kodlama.io.Devs.business.abstracts.LanguageService;
import kodlama.io.Devs.business.abstracts.TechnologyService;
import kodlama.io.Devs.business.requests.TechnologyRequest;
import kodlama.io.Devs.business.responses.TechnologyResponse;
import kodlama.io.Devs.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.Devs.entities.concretes.Language;
import kodlama.io.Devs.entities.concretes.Technology;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {

    private TechnologyRepository technologyRepository;
    private LanguageService languageService;

    public TechnologyManager(TechnologyRepository technologyRepository, LanguageService languageService){

        this.technologyRepository = technologyRepository;
        this.languageService = languageService;
    }

    @Override
    public List<TechnologyResponse> getAll(){
        List<Technology> technologies = technologyRepository.findAll();
        List<TechnologyResponse> responses = new ArrayList<>();
        for (Technology technology : technologies){
            TechnologyResponse technologyResponse = new TechnologyResponse();
            technologyResponse.setName(technology.getName());
            technologyResponse.setLanguageId(technology.getLanguage().getId());
            technologyResponse.setId(technology.getId());
            technologyResponse.setLanguageName(technology.getLanguage().getName());
            responses.add(technologyResponse);
        }
        return responses;
    }

    @Override
    public void add(TechnologyRequest technologyRequest){

        Technology technology = new Technology();
        Language language = languageService.getById(technologyRequest.getLanguageId());
        technology.setName(technologyRequest.getName());
        technology.setLanguage(language);
        technologyRepository.save(technology);
    }

    @Override
    public void update(TechnologyRequest technologyRequest, int id){

        Technology updateTechnology = technologyRepository.findById(id);
        Language updateLanguageId = languageService.getById(technologyRequest.getLanguageId());
        updateTechnology.setName(technologyRequest.getName());
        updateTechnology.setLanguage(updateLanguageId);
        technologyRepository.save(updateTechnology);
    }

    @Override
    public void delete(int id){
        technologyRepository.deleteById(id);
    }
}
