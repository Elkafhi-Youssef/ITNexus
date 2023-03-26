package com.itnexusglobal.itnexusglobal.skills;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SkillsService {

    private final SkillsRepository skillsRepository;
    private final ProfileRepository profileRepository;

    public SkillsService(final SkillsRepository skillsRepository,
            final ProfileRepository profileRepository) {
        this.skillsRepository = skillsRepository;
        this.profileRepository = profileRepository;
    }

    public List<SkillsDTO> findAll() {
        final List<Skills> skillss = skillsRepository.findAll(Sort.by("skillId"));
        return skillss.stream()
                .map((skills) -> mapToDTO(skills, new SkillsDTO()))
                .toList();
    }

    public SkillsDTO get(final Long skillId) {
        return skillsRepository.findById(skillId)
                .map(skills -> mapToDTO(skills, new SkillsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SkillsDTO skillsDTO) {
        final Skills skills = new Skills();
        mapToEntity(skillsDTO, skills);
        return skillsRepository.save(skills).getSkillId();
    }

    public void update(final Long skillId, final SkillsDTO skillsDTO) {
        final Skills skills = skillsRepository.findById(skillId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(skillsDTO, skills);
        skillsRepository.save(skills);
    }

    public void delete(final Long skillId) {
        skillsRepository.deleteById(skillId);
    }

    private SkillsDTO mapToDTO(final Skills skills, final SkillsDTO skillsDTO) {
        skillsDTO.setSkillId(skills.getSkillId());
        skillsDTO.setSkillType(skills.getSkillType());
        skillsDTO.setSkillName(skills.getSkillName());
        skillsDTO.setSkillProfileId(skills.getSkillProfileId() == null ? null : skills.getSkillProfileId().getProfileId());
        return skillsDTO;
    }

    private Skills mapToEntity(final SkillsDTO skillsDTO, final Skills skills) {
        skills.setSkillType(skillsDTO.getSkillType());
        skills.setSkillName(skillsDTO.getSkillName());
        final Profile skillProfileId = skillsDTO.getSkillProfileId() == null ? null : profileRepository.findById(skillsDTO.getSkillProfileId())
                .orElseThrow(() -> new NotFoundException("skillProfileId not found"));
        skills.setSkillProfileId(skillProfileId);
        return skills;
    }

}
