package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import edu.tcu.cs.hogwartsartifactsonline.system.Result;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    private final IdWorker idWorker;


    public ArtifactService(ArtifactRepository artifactRepository, IdWorker idWorker) {
        this.artifactRepository = artifactRepository;
        this.idWorker = idWorker;
    }

    public Artifact findById(String artifactId) {
        return this.artifactRepository.findById(artifactId)
                .orElseThrow(()->new ArtifactNotFoundException(artifactId));
    }

    public List<Artifact> findAll() {
        return this.artifactRepository.findAll();
    }

    public Artifact save(Artifact newArtifact){
        newArtifact.setId(idWorker.nextId() + "");

        return this.artifactRepository.save(newArtifact);
    }

    public Artifact update(String artifactId, Artifact updatedArtifact){
        return this.artifactRepository.findById(artifactId)
                .map(returnedOldArtifactInside ->{
                    returnedOldArtifactInside.setName(updatedArtifact.getName());
                    returnedOldArtifactInside.setDescription(updatedArtifact.getDescription());
                    returnedOldArtifactInside.setImgUrl(updatedArtifact.getImgUrl());

                    return this.artifactRepository.save(returnedOldArtifactInside);

                }).orElseThrow(()-> new ArtifactNotFoundException(artifactId));
    }

    public void delete(String artifactId){
        Artifact toBeDeletedArtifact = this.artifactRepository.findById(artifactId).orElseThrow(()-> new ArtifactNotFoundException(artifactId));
        this.artifactRepository.deleteById(toBeDeletedArtifact.getId());
    }

}