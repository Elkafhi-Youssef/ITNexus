package com.itnexusglobal.itnexusglobal.hobbie;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class HobbieService {

    private final HobbieRepository hobbieRepository;
    private final ProfileRepository profileRepository;

    public HobbieService(final HobbieRepository hobbieRepository,
            final ProfileRepository profileRepository) {
        this.hobbieRepository = hobbieRepository;
        this.profileRepository = profileRepository;
    }

    public List<HobbieDTO> findAll() {
        final List<Hobbie> hobbies = hobbieRepository.findAll(Sort.by("hobbieId"));
        return hobbies.stream()
                .map((hobbie) -> mapToDTO(hobbie, new HobbieDTO()))
                .toList();
    }

    public HobbieDTO get(final Long hobbieId) {
        return hobbieRepository.findById(hobbieId)
                .map(hobbie -> mapToDTO(hobbie, new HobbieDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = new Hobbie();
        mapToEntity(hobbieDTO, hobbie);
        return hobbieRepository.save(hobbie).getHobbieId();
    }

    public void update(final Long hobbieId, final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = hobbieRepository.findById(hobbieId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(hobbieDTO, hobbie);
        hobbieRepository.save(hobbie);
    }

    public void delete(final Long hobbieId) {
        hobbieRepository.deleteById(hobbieId);
    }

    private HobbieDTO mapToDTO(final Hobbie hobbie, final HobbieDTO hobbieDTO) {
        hobbieDTO.setHobbieId(hobbie.getHobbieId());
        hobbieDTO.setHobbieName(hobbie.getHobbieName());
        hobbieDTO.setHobbieProfileId(hobbie.getHobbieProfileId() == null ? null : hobbie.getHobbieProfileId().getProfileId());
        return hobbieDTO;
    }

    private Hobbie mapToEntity(final HobbieDTO hobbieDTO, final Hobbie hobbie) {
        hobbie.setHobbieName(hobbieDTO.getHobbieName());
        final Profile hobbieProfileId = hobbieDTO.getHobbieProfileId() == null ? null : profileRepository.findById(hobbieDTO.getHobbieProfileId())
                .orElseThrow(() -> new NotFoundException("hobbieProfileId not found"));
        hobbie.setHobbieProfileId(hobbieProfileId);
        return hobbie;
    }

}
