package com.itnexusglobal.itnexusglobal.person;

import com.itnexusglobal.itnexusglobal.role.Role;
import com.itnexusglobal.itnexusglobal.role.RoleRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import com.itnexusglobal.itnexusglobal.workoffer.Workoffer;
import com.itnexusglobal.itnexusglobal.workoffer.WorkofferRepository;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final WorkofferRepository workofferRepository;
    private  final PasswordEncoder passwordEncoder;


    public PersonService(final PersonRepository personRepository,
            final RoleRepository roleRepository, final WorkofferRepository workofferRepository, final PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.workofferRepository = workofferRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public List<PersonDTO> findAll() {
        final List<Person> persons = personRepository.findAll(Sort.by("id"));
        return persons.stream()
                .map((person) -> mapToDTO(person, new PersonDTO()))
                .toList();
    }

    public PersonDTO get(final Long id) {
        return personRepository.findById(id)
                .map(person -> mapToDTO(person, new PersonDTO()))
                .orElseThrow(NotFoundException::new);
    }
    public Person findUserByEmail(final String email) {
        return personRepository.findByEmail(email) ;
    }

    public Long create(final PersonDTO personDTO) {
        personDTO.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        Role rhRole = null ;
        if (personDTO.getRole().equals("RH")) {
            rhRole = roleRepository.findRoleByRoleName("RH");
            System.out.println("RH");
        } else {
            rhRole = roleRepository.findRoleByRoleName("DEV");
        }
        final Person newPerson = new Person();
        mapToEntity(personDTO, newPerson);
        newPerson.setPersonRoleRoles(Collections.singleton(rhRole));
        return personRepository.save(newPerson).getId();
    }

    public void update(final Long id, final PersonDTO personDTO) {
        final Person person = personRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(personDTO, person);
        personRepository.save(person);
    }

    public void delete(final Long id) {
        personRepository.deleteById(id);
    }

    private PersonDTO mapToDTO(final Person person, final PersonDTO personDTO) {
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setEmail(person.getEmail());
        personDTO.setAddress(person.getAddress());
//        personDTO.setPassword(person.getPassword());
        personDTO.setImage(person.getImage());
        personDTO.setTel(person.getTel());
        personDTO.setLinkedIn(person.getLinkedIn());
        personDTO.setGithub(person.getGithub());
        personDTO.setPersonRoleRoles(person.getPersonRoleRoles() == null ? null : person.getPersonRoleRoles().stream()
                .map(role -> role.getId())
                .toList());
        personDTO.setWorkoffers(person.getWorkoffers() == null ? null : person.getWorkoffers().stream()
                .map(workoffer -> workoffer.getWorkofferId())
                .toList());
        return personDTO;
    }

    private Person mapToEntity(final PersonDTO personDTO, final Person person) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setEmail(personDTO.getEmail());
        person.setAddress(personDTO.getAddress());
        person.setPassword(personDTO.getPassword());
        person.setImage(personDTO.getImage());
        person.setTel(personDTO.getTel());
        person.setLinkedIn(personDTO.getLinkedIn());
        person.setGithub(personDTO.getGithub());
        final List<Role> personRoleRoles = roleRepository.findAllById(
                personDTO.getPersonRoleRoles() == null ? Collections.emptyList() : personDTO.getPersonRoleRoles());
        if (personRoleRoles.size() != (personDTO.getPersonRoleRoles() == null ? 0 : personDTO.getPersonRoleRoles().size())) {
            throw new NotFoundException("one of personRoleRoles not found");
        }
        person.setPersonRoleRoles(personRoleRoles.stream().collect(Collectors.toSet()));
        final List<Workoffer> workoffers = workofferRepository.findAllById(
                personDTO.getWorkoffers() == null ? Collections.emptyList() : personDTO.getWorkoffers());
        if (workoffers.size() != (personDTO.getWorkoffers() == null ? 0 : personDTO.getWorkoffers().size())) {
            throw new NotFoundException("one of workoffers not found");
        }
        person.setWorkoffers(workoffers.stream().collect(Collectors.toSet()));
        return person;
    }

    public boolean emailExists(final String email) {
        return personRepository.existsByEmailIgnoreCase(email);
    }

    public boolean telExists(final String tel) {
        return personRepository.existsByTelIgnoreCase(tel);
    }

}
