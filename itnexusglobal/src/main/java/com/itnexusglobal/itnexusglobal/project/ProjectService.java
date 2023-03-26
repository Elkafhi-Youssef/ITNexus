package com.itnexusglobal.itnexusglobal.project;

import com.itnexusglobal.itnexusglobal.profile.Profile;
import com.itnexusglobal.itnexusglobal.profile.ProfileRepository;
import com.itnexusglobal.itnexusglobal.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;

    public ProjectService(final ProjectRepository projectRepository,
            final ProfileRepository profileRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
    }

    public List<ProjectDTO> findAll() {
        final List<Project> projects = projectRepository.findAll(Sort.by("projectid"));
        return projects.stream()
                .map((project) -> mapToDTO(project, new ProjectDTO()))
                .toList();
    }

    public ProjectDTO get(final Long projectid) {
        return projectRepository.findById(projectid)
                .map(project -> mapToDTO(project, new ProjectDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProjectDTO projectDTO) {
        final Project project = new Project();
        mapToEntity(projectDTO, project);
        return projectRepository.save(project).getProjectid();
    }

    public void update(final Long projectid, final ProjectDTO projectDTO) {
        final Project project = projectRepository.findById(projectid)
                .orElseThrow(NotFoundException::new);
        mapToEntity(projectDTO, project);
        projectRepository.save(project);
    }

    public void delete(final Long projectid) {
        projectRepository.deleteById(projectid);
    }

    private ProjectDTO mapToDTO(final Project project, final ProjectDTO projectDTO) {
        projectDTO.setProjectid(project.getProjectid());
        projectDTO.setProjectTitle(project.getProjectTitle());
        projectDTO.setTechnologies(project.getTechnologies());
        projectDTO.setProjectRepo(project.getProjectRepo());
        projectDTO.setProjectPrfileId(project.getProjectPrfileId() == null ? null : project.getProjectPrfileId().getProfileId());
        return projectDTO;
    }

    private Project mapToEntity(final ProjectDTO projectDTO, final Project project) {
        project.setProjectTitle(projectDTO.getProjectTitle());
        project.setTechnologies(projectDTO.getTechnologies());
        project.setProjectRepo(projectDTO.getProjectRepo());
        final Profile projectPrfileId = projectDTO.getProjectPrfileId() == null ? null : profileRepository.findById(projectDTO.getProjectPrfileId())
                .orElseThrow(() -> new NotFoundException("projectPrfileId not found"));
        project.setProjectPrfileId(projectPrfileId);
        return project;
    }

}
