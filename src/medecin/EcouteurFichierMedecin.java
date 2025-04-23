package medecin;

import java.io.IOException;
import java.nio.file.*;

public class EcouteurFichierMedecin {
    public void surveillerFichier(String cheminFichier) throws IOException, InterruptedException {
        Path path = Paths.get("send_by_doctor_expert.txt");
        WatchService watcher = FileSystems.getDefault().newWatchService();
        path.getParent().register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Surveillance de " + cheminFichier + " activée.");

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                Path changed = (Path) event.context();
                if (changed.endsWith(path.getFileName())) {
                    System.out.println("Fichier modifié : " + cheminFichier);
                }
            }
            boolean valid = key.reset();
            if (!valid) break;
        }

        watcher.close();
    }
}
