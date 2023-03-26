package com.itnexusglobal.itnexusglobal.laguage;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LaguageService {

    private final LaguageRepository laguageRepository;
    private final ProfileRepository profileRepository;

    public LaguageService(final LaguageRepository laguageRepository,
            final ProfileRepository profileRepository) {
        this.laguageRepository = laguageRepository;
        this.profileRepository = profileRepository;
    }

    public List<LaguageDTO> findAll() {
        final List<Laguage> laguages = laguageRepository.findAll(Sort.by("laguageId"));
        return laguages.stream()
                .map((laguage) -> mapToDTO(laguage, new LaguageDTO()))
                .toList();
    }

    public LaguageDTO get(final Long laguageId) {
        return laguageRepository.findById(laguageId)
                .map(laguage -> mapToDTO(laguage, new LaguageDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LaguageDTO laguageDTO) {
        final Laguage laguage = new Laguage();
        mapToEntity(laguageDTO, laguage);
        return laguageRepository.save(laguage).getLaguageId();
    }

    public void update(final Long laguageId, final LaguageDTO laguageDTO) {
        final Laguage laguage = laguageRepository.findById(laguageId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(laguageDTO, laguage);
        laguageRepository.save(laguage);
    }

    public void delete(final Long laguageId) {
        laguageRepository.deleteById(laguageId);
    }

    private LaguageDTO mapToDTO(final Laguage laguage, final LaguageDTO laguageDTO) {
        laguageDTO.setLaguageId(laguage.getLaguageId());
        laguageDTO.setLanguageName(laguage.getLanguageName());
        laguageDTO.setLanguageLevel(laguage.getLanguageLevel());
        laguageDTO.setLaguageProfileId(laguage.getLaguageProfileId() == null ? null : laguage.getLaguageProfileId().getProfileId());
        return laguageDTO;
    }

    private Laguage mapToEntity(final LaguageDTO laguageDTO, final Laguage laguage) {
        laguage.setLanguageName(laguageDTO.getLanguageName());
        laguage.setLanguageLevel(laguageDTO.getLanguageLevel());
        final Profile laguageProfileId = laguageDTO.getLaguageProfileId() == null ? null : profileRepository.findById(laguageDTO.getLaguageProfileId())
                .orElseThrow(() -> new NotFoundException("laguageProfileId not found"));
        laguage.setLaguageProfileId(laguageProfileId);
        return laguage;
    }

}
