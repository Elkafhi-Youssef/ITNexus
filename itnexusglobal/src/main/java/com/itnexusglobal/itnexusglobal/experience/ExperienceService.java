package com.itnexusglobal.itnexusglobal.experience;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ProfileRepository profileRepository;

    public ExperienceService(final ExperienceRepository experienceRepository,
            final ProfileRepository profileRepository) {
        this.experienceRepository = experienceRepository;
        this.profileRepository = profileRepository;
    }

    public List<ExperienceDTO> findAll() {
        final List<Experience> experiences = experienceRepository.findAll(Sort.by("experienceId"));
        return experiences.stream()
                .map((experience) -> mapToDTO(experience, new ExperienceDTO()))
                .toList();
    }

    public ExperienceDTO get(final Long experienceId) {
        return experienceRepository.findById(experienceId)
                .map(experience -> mapToDTO(experience, new ExperienceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ExperienceDTO experienceDTO) {
        final Experience experience = new Experience();
        mapToEntity(experienceDTO, experience);
        return experienceRepository.save(experience).getExperienceId();
    }

    public void update(final Long experienceId, final ExperienceDTO experienceDTO) {
        final Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(experienceDTO, experience);
        experienceRepository.save(experience);
    }

    public void delete(final Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }

    private ExperienceDTO mapToDTO(final Experience experience, final ExperienceDTO experienceDTO) {
        experienceDTO.setExperienceId(experience.getExperienceId());
        experienceDTO.setExpeName(experience.getExpeName());
        experienceDTO.setPosition(experience.getPosition());
        experienceDTO.setStartDate(experience.getStartDate());
        experienceDTO.setEndDate(experience.getEndDate());
        experienceDTO.setDescreption(experience.getDescreption());
        experienceDTO.setExperienceProfileId(experience.getExperienceProfileId() == null ? null : experience.getExperienceProfileId().getProfileId());
        return experienceDTO;
    }

    private Experience mapToEntity(final ExperienceDTO experienceDTO, final Experience experience) {
        experience.setExpeName(experienceDTO.getExpeName());
        experience.setPosition(experienceDTO.getPosition());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setDescreption(experienceDTO.getDescreption());
        final Profile experienceProfileId = experienceDTO.getExperienceProfileId() == null ? null : profileRepository.findById(experienceDTO.getExperienceProfileId())
                .orElseThrow(() -> new NotFoundException("experienceProfileId not found"));
        experience.setExperienceProfileId(experienceProfileId);
        return experience;
    }

}
