package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface BinViewInterface {

    String MENU_BIN = "�����������";
    String ACTION_BIN_STEP = "����� 120";
    String ACTION_BIN_PERCENT = "����� 40%";
    String ACTION_BIN_THRESHOLD = "�� ������";

    void addBinByStepClickListener(ActionListener l);

    void addBinByPercentClickListener(ActionListener l);

    void addBinByThresholdClickListener(ActionListener l);
}
