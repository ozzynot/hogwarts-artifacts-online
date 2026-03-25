package edu.tcu.cs.hogwartsartifactsonline.wizard;

import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WizardService {


    private final WizardRepository wizardRepository;

    public WizardService(WizardRepository wizardRepository) {
        this.wizardRepository = wizardRepository;
    }


    public List<Wizard> findAllWizards() {
        return wizardRepository.findAll();
    }

    public Wizard findById(int wizardId) {
        return this.wizardRepository.findById(wizardId)
                .orElseThrow(()-> new WizardNotFoundException(wizardId) );
    }


    public Wizard save(Wizard wizard) {
        return this.wizardRepository.save(wizard);
    }

    public Wizard update(Integer wizardID, Wizard updatedWizard) {
        return this.wizardRepository.findById(wizardID)
                .map(oldWizard ->{
                    oldWizard.setName(updatedWizard.getName());
                    return this.wizardRepository.save(oldWizard);
                }).orElseThrow(()-> new WizardNotFoundException(wizardID));
    }

    public void delete(Integer wizardId) {
        Wizard wizardToBeDeleted = wizardRepository.findById(wizardId)
                .orElseThrow(()-> new WizardNotFoundException(wizardId));
        wizardToBeDeleted.removeAllArtifact();
        this.wizardRepository.deleteById(wizardId);
    }

}