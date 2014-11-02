package by.slesh.ri.cp.victoriabrel.app.view.service;

import java.awt.event.ActionListener;

public interface FileViewInterface {

    String MENU_ITEM_OPEN = "Œ“ –€“‹";
    String MENU_FILE = "‘¿…À";
    String ACTION_SETTINGS = "Õ¿…—“–Œ… »";

    void addOpenClickListener(OpenListener l);

    void addSettingClickListener(ActionListener l);
}
