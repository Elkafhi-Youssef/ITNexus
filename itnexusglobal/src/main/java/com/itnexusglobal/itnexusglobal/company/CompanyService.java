package com.itnexusglobal.itnexusglobal.company;

import com.itnexusglobal.itnexusglobal.person.Person;
import com.itnexusglobal.itnexusglobal.person.PersonRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;

import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;

    public CompanyService(final CompanyRepository companyRepository,
            final PersonRepository personRepository) {
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
    }

    public List<CompanyDTO> findAll() {
        final List<Company> companys = companyRepository.findAll(Sort.by("companyId"));
        return companys.stream()
                .map((company) -> mapToDTO(company, new CompanyDTO()))
                .toList();
    }

    public CompanyDTO get(final Long companyId) {
        return companyRepository.findById(companyId)
                .map(company -> mapToDTO(company, new CompanyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CompanyDTO companyDTO, Principal principal) {
        final Company company = new Company();
        mapToEntity(companyDTO, company);
        final Person person = personRepository.findByEmail(principal.getName());
        company.setCompanyPersonId(person);
        return companyRepository.save(company).getCompanyId();
    }

    public void update(final Long companyId, final CompanyDTO companyDTO) {
        final Company company = companyRepository.findById(companyId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(companyDTO, company);
        companyRepository.save(company);
    }

    public void delete(final Long companyId) {
        companyRepository.deleteById(companyId);
    }

    private CompanyDTO mapToDTO(final Company company, final CompanyDTO companyDTO) {
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setComName(company.getComName());
        companyDTO.setWebsite(company.getWebsite());
        companyDTO.setEmployiesNumber(company.getEmployiesNumber());
        companyDTO.setCreationDate(String.valueOf(company.getDateCreated()));
        companyDTO.setAddress(company.getAddress());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setLinkedIn(company.getLinkedIn());
        companyDTO.setCompanyPersonId(company.getCompanyPersonId() == null ? null : company.getCompanyPersonId().getId());
        return companyDTO;
    }

    private Company mapToEntity(final CompanyDTO companyDTO, final Company company) {
        company.setComName(companyDTO.getComName());
        company.setWebsite(companyDTO.getWebsite());
        company.setEmployiesNumber(companyDTO.getEmployiesNumber());
        company.setAddress(companyDTO.getAddress());
        company.setDescription(companyDTO.getDescription());
        company.setLinkedIn(companyDTO.getLinkedIn());
        final Person companyPersonId = companyDTO.getCompanyPersonId() == null ? null : personRepository.findById(companyDTO.getCompanyPersonId())
                .orElseThrow(() -> new NotFoundException("companyPersonId not found"));
        company.setCompanyPersonId(companyPersonId);
        return company;
    }

}
