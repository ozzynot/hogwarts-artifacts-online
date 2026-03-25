package edu.tcu.cs.hogwartsartifactsonline.system;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.artifact.ArtifactRepository;
import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import edu.tcu.cs.hogwartsartifactsonline.wizard.WizardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {



    private final ArtifactRepository artifactRepository;

    private final WizardRepository wizardRepository;

    public DBDataInitializer(ArtifactRepository artifactRepository, WizardRepository wizardRepository) {
        this.artifactRepository = artifactRepository;
        this.wizardRepository = wizardRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        Artifact a1 = new Artifact();
        a1.setName("artifact1");
        a1.setDescription("artifact1 description");
        a1.setImgUrl("artifact1 imgUrl");
        a1.setId("98765");

        Artifact a2 = new Artifact();
        a2.setName("artifact2");
        a2.setDescription("artifact2 description");
        a2.setImgUrl("artifact2 imgUrl");
        a2.setId("87654");

        Artifact a3 = new Artifact();
        a3.setName("artifact3");
        a3.setId("3453");
        a3.setDescription("artifact3 description");
        a3.setImgUrl("artifact3 imgUrl");
//        this.artifacts.add(a3);

        Artifact a4 = new Artifact();
        a4.setName("artifact4");
        a4.setId("45678");
        a4.setDescription("artifact4 description");
        a4.setImgUrl("artifact4 imgUrl");
//        this.artifacts.add(a4);

        Artifact a5 = new Artifact();
        a5.setName("artifact5");
        a5.setId("2345");
        a5.setDescription("artifact5 description");
        a5.setImgUrl("artifact5 imgUrl");
//        this.artifacts.add(a5);

        Artifact a6 = new Artifact();
        a6.setName("artifact6");
        a6.setId("53143");
        a6.setDescription("artifact6 description");
        a6.setImgUrl("artifact6 imgUrl");
//        this.artifacts.add(a6);

        Wizard w1 = new Wizard();
        w1.setName("wizard1");
        w1.setId(1);
        w1.addArtifact(a1);
        w1.addArtifact(a3);

        Wizard w2 = new Wizard();
        w2.setName("wizard2");
        w2.setId(2);
        w2.addArtifact(a2);
        w2.addArtifact(a4);

        Wizard w3 = new Wizard();
        w3.setName("wizard3");
        w3.setId(3);
        w3.addArtifact(a5);

        wizardRepository.save(w1);
        wizardRepository.save(w2);
        wizardRepository.save(w3);

        artifactRepository.save(a6);

    }
}