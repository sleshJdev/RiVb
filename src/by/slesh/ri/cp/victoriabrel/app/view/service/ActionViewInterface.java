package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface ActionViewInterface {

    String MENU_ACTION = "ƒ≈…—“¬»ﬂ";
    String ACTION_SKELETON = "— ≈À≈“ŒÕ»«¿÷»ﬂ";
    String ACTION_SEGMENT_BUG = "—≈√Ã≈Õ“¿÷»ﬂ ∆” ŒÃ";
    String ACTION_SEGMENT_HISTOGRAM = "—≈√Ã≈Õ“¿÷»ﬂ œŒ √»—“–Œ√–¿ÃÃ≈";

    void addSkeletizationClickListener(ActionListener l);

    void addHistogramSegmentClickListener(ActionListener l);

    void addBugSegmentClickListener(ActionListener l);
}
