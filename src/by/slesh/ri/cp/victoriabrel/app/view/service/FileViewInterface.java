package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface FileViewInterface {

    String MENU_ITEM_OPEN = "�������";
    String MENU_FILE = "����";
    String ACTION_SETTINGS = "����������";

    void addOpenClickListener(OpenListener l);

    void addSettingClickListener(ActionListener l);
}
