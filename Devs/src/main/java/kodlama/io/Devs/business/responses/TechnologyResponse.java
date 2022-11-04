package kodlama.io.Devs.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyResponse {

    private Long id;
    private Long languageId;
    private String name;
    private String languageName;
}
