package com.itnexusglobal.itnexusglobal.technology;

import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TechService {
private final TechRepository techRepository;

    public TechService(TechRepository techRepository) {
        this.techRepository = techRepository;
    }
    public List<TechDTO> findAll() {
        final List<Techs> techs = techRepository.findAll(Sort.by("techId"));
        return techs.stream()
                .map((tech) -> mapToDTO(tech, new TechDTO()))
                .toList();
    }

    public TechDTO get(final Long techId) {
        return techRepository.findById(techId)
                .map(tech -> mapToDTO(tech, new TechDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TechDTO techDTO) {
        System.out.println(techDTO.getTechnologyName());
        final Techs techs = new Techs();
        mapToEntity(techDTO, techs);
        System.out.println("checking the technologyName: " + techs.getTechnologyName());
        return techRepository.save(techs).getTechId();
    }

    private TechDTO mapToDTO(final Techs techs, final TechDTO techDTO) {
       techDTO.setTechnologyName(techs.getTechnologyName());
        return techDTO;
    }
    private Techs mapToEntity(final TechDTO techDTO, final Techs techs) {
        techs.setTechnologyName(techDTO.getTechnologyName());

        return techs;
    }

}
