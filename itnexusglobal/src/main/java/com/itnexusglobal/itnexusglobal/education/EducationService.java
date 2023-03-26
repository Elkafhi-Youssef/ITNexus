package com.itnexusglobal.itnexusglobal.education;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;

    public EducationService(final EducationRepository educationRepository,
            final ProfileRepository profileRepository) {
        this.educationRepository = educationRepository;
        this.profileRepository = profileRepository;
    }

    public List<EducationDTO> findAll() {
        final List<Education> educations = educationRepository.findAll(Sort.by("educationId"));
        return educations.stream()
                .map((education) -> mapToDTO(education, new EducationDTO()))
                .toList();
    }

    public EducationDTO get(final Long educationId) {
        return educationRepository.findById(educationId)
                .map(education -> mapToDTO(education, new EducationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EducationDTO educationDTO) {
        final Education education = new Education();
        mapToEntity(educationDTO, education);
        return educationRepository.save(education).getEducationId();
    }

    public void update(final Long educationId, final EducationDTO educationDTO) {
        final Education education = educationRepository.findById(educationId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(educationDTO, education);
        educationRepository.save(education);
    }

    public void delete(final Long educationId) {
        educationRepository.deleteById(educationId);
    }

    private EducationDTO mapToDTO(final Education education, final EducationDTO educationDTO) {
        educationDTO.setEducationId(education.getEducationId());
        educationDTO.setEduName(education.getEduName());
        educationDTO.setStartDate(education.getStartDate());
        educationDTO.setEndDate(education.getEndDate());
        educationDTO.setEducationProfaileId(education.getEducationProfaileId() == null ? null : education.getEducationProfaileId().getProfileId());
        return educationDTO;
    }

    private Education mapToEntity(final EducationDTO educationDTO, final Education education) {
        education.setEduName(educationDTO.getEduName());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        final Profile educationProfaileId = educationDTO.getEducationProfaileId() == null ? null : profileRepository.findById(educationDTO.getEducationProfaileId())
                .orElseThrow(() -> new NotFoundException("educationProfaileId not found"));
        education.setEducationProfaileId(educationProfaileId);
        return education;
    }

}
