package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface ActionViewInterface {

    String MENU_ACTION = "��������";
    String ACTION_SKELETON = "��������������";
    String ACTION_SEGMENT_BUG = "����������� �����";
    String ACTION_SEGMENT_HISTOGRAM = "����������� �� ������������";

    void addSkeletizationClickListener(ActionListener l);

    void addHistogramSegmentClickListener(ActionListener l);

    void addBugSegmentClickListener(ActionListener l);
}
