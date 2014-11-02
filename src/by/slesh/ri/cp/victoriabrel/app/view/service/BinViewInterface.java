package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface BinViewInterface {

    String MENU_BIN = "ахмюпхгюжхъ";
    String ACTION_BIN_STEP = "лернд 120";
    String ACTION_BIN_PERCENT = "лернд 40%";
    String ACTION_BIN_THRESHOLD = "он онпнцс";

    void addBinByStepClickListener(ActionListener l);

    void addBinByPercentClickListener(ActionListener l);

    void addBinByThresholdClickListener(ActionListener l);
}
