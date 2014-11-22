package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public interface ImageBoxesViewInterface {

    void updateSource(BufferedImage source);

    void updateTarget(BufferedImage target);

    void updateRegion(BufferedImage region);

    void addTargetImageBoxClickListener(MouseListener l);

    void getLocationOnImage(Point currentLocation);
}
