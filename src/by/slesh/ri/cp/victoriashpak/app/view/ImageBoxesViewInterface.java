package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.image.BufferedImage;

public interface ImageBoxesViewInterface {

    void updateAreaInterest(BufferedImage area);

    void updateSegmentFullName(BufferedImage[] digits);
    
    void updateUnrecognizeFullName(BufferedImage[] digits);

    void updateRecognize(BufferedImage[] recognizeNumber);
}
