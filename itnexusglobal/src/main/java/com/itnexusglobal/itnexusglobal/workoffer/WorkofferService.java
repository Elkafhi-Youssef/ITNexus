package com.itnexusglobal.itnexusglobal.workoffer;
import com.itnexusglobal.itnexusglobal.company.Company;
import com.itnexusglobal.itnexusglobal.company.CompanyRepository;
import com.itnexusglobal.itnexusglobal.person.Person;
import com.itnexusglobal.itnexusglobal.person.PersonRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class WorkofferService {

    private final WorkofferRepository workofferRepository;
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;

    public WorkofferService(final WorkofferRepository workofferRepository,
            final CompanyRepository companyRepository, final PersonRepository personRepository) {
        this.workofferRepository = workofferRepository;
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
    }

    public List<WorkofferDTO> findAll() {
        final List<Workoffer> workoffers = workofferRepository.findAll(Sort.by("workofferId"));
        return workoffers.stream()
                .map((workoffer) -> mapToDTO(workoffer, new WorkofferDTO()))
                .toList();
    }

    public WorkofferDTO get(final Long workofferId) {
        return workofferRepository.findById(workofferId)
                .map(workoffer -> mapToDTO(workoffer, new WorkofferDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final WorkofferDTO workofferDTO, Principal principal) {
        final Workoffer workoffer = new Workoffer();
        mapToEntity(workofferDTO, workoffer);
        final Person person = personRepository.findByEmail(principal.getName());
        workoffer.setRHworkofferId(person);
        workoffer.setCompanyworkofferid(person.getCompany());
        return workofferRepository.save(workoffer).getWorkofferId();
    }

    public void update(final Long workofferId, final WorkofferDTO workofferDTO) {
        final Workoffer workoffer = workofferRepository.findById(workofferId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(workofferDTO, workoffer);
        workofferRepository.save(workoffer);
    }

    public void delete(final Long workofferId) {
        workofferRepository.deleteById(workofferId);
    }

    private WorkofferDTO mapToDTO(final Workoffer workoffer, final WorkofferDTO workofferDTO) {
        workofferDTO.setWorkofferId(workoffer.getWorkofferId());
        workofferDTO.setOfferTitle(workoffer.getOfferTitle());
        workofferDTO.setOfferDescription(workoffer.getOfferDescription());
        workofferDTO.setCreationOfferDate(workoffer.getCreationOfferDate());
        workofferDTO.setCompanyworkofferid(workoffer.getCompanyworkofferid() == null ? null : workoffer.getCompanyworkofferid().getCompanyId());
        workofferDTO.setRHworkofferId(workoffer.getRHworkofferId() == null ? null : workoffer.getRHworkofferId().getId());
        return workofferDTO;
    }

    private Workoffer mapToEntity(final WorkofferDTO workofferDTO, final Workoffer workoffer) {
        workoffer.setOfferTitle(workofferDTO.getOfferTitle());
        workoffer.setOfferDescription(workofferDTO.getOfferDescription());
        workoffer.setCreationOfferDate(workofferDTO.getCreationOfferDate());
        final Company companyworkofferid = workofferDTO.getCompanyworkofferid() == null ? null : companyRepository.findById(workofferDTO.getCompanyworkofferid())
                .orElseThrow(() -> new NotFoundException("companyworkofferid not found"));
        workoffer.setCompanyworkofferid(companyworkofferid);
        final Person rHworkofferId = workofferDTO.getRHworkofferId() == null ? null : personRepository.findById(workofferDTO.getRHworkofferId())
                .orElseThrow(() -> new NotFoundException("rHworkofferId not found"));
        workoffer.setRHworkofferId(rHworkofferId);
        return workoffer;
    }

}
