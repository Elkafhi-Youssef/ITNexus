package com.itnexusglobal.itnexusglobal.profile;

import com.itnexusglobal.itnexusglobal.person.Person;
import com.itnexusglobal.itnexusglobal.person.PersonRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final PersonRepository personRepository;

    public ProfileService(final ProfileRepository profileRepository,
            final PersonRepository personRepository) {
        this.profileRepository = profileRepository;
        this.personRepository = personRepository;
    }

    public List<ProfileDTO> findAll() {
        final List<Profile> profiles = profileRepository.findAll(Sort.by("profileId"));
        return profiles.stream()
                .map((profile) -> mapToDTO(profile, new ProfileDTO()))
                .toList();
    }

    public ProfileDTO get(final Long profileId) {
        return profileRepository.findById(profileId)
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProfileDTO profileDTO) {
        final Profile profile = new Profile();
        mapToEntity(profileDTO, profile);
        return profileRepository.save(profile).getProfileId();
    }

    public void update(final Long profileId, final ProfileDTO profileDTO) {
        final Profile profile = profileRepository.findById(profileId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(profileDTO, profile);
        profileRepository.save(profile);
    }

    public void delete(final Long profileId) {
        profileRepository.deleteById(profileId);
    }

    private ProfileDTO mapToDTO(final Profile profile, final ProfileDTO profileDTO) {
        profileDTO.setProfileId(profile.getProfileId());
        profileDTO.setPersonProfileId(profile.getPersonProfileId() == null ? null : profile.getPersonProfileId().getId());
        return profileDTO;
    }

    private Profile mapToEntity(final ProfileDTO profileDTO, final Profile profile) {
        final Person personProfileId = profileDTO.getPersonProfileId() == null ? null : personRepository.findById(profileDTO.getPersonProfileId())
                .orElseThrow(() -> new NotFoundException("personProfileId not found"));
        profile.setPersonProfileId(personProfileId);
        return profile;
    }

}
