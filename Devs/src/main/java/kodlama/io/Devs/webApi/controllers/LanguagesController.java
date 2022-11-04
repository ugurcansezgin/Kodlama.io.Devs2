package kodlama.io.Devs.webApi.controllers;

import kodlama.io.Devs.business.abstracts.LanguageService;
import kodlama.io.Devs.business.requests.LanguageRequest;
import kodlama.io.Devs.business.responses.LanguageResponse;
import kodlama.io.Devs.entities.concretes.Language;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

    LanguageService languageService;

    public LanguagesController(LanguageService languageService) {

        this.languageService = languageService;
    }

    @GetMapping("/getall")
    public List<LanguageResponse> getAll(){

        return languageService.getALL();
    }

    @GetMapping("/getbyid")
    public LanguageResponse getById(@RequestParam Long id){

        return  languageService.getResponseById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody LanguageRequest languageRequest) throws Exception {
        languageService.add(languageRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){

        languageService.delete(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody LanguageRequest languageRequest, @PathVariable Long id) throws Exception {
        languageService.update(languageRequest,id);
    }
}
