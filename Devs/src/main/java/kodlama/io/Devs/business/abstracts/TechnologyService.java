package kodlama.io.Devs.business.abstracts;

import kodlama.io.Devs.business.requests.TechnologyRequest;
import kodlama.io.Devs.business.responses.TechnologyResponse;

import java.util.List;

public interface TechnologyService {

    List<TechnologyResponse> getAll();
    void add(TechnologyRequest technologyRequest);
    void delete(Long id);
    void update(TechnologyRequest technologyRequest, Long id);
}
