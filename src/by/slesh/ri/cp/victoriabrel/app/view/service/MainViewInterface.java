package by.slesh.ri.cp.victoriabrel.app.view.service;


public interface MainViewInterface {
    
    ActionViewInterface getActionViewInterfaceImpl();
    
    BinViewInterface getBinViewInterfaceImpl();
    
    FileViewInterface getFileViewInterfaceImpl();
    
    ImageBoxesViewInterface getImageBoxesViewInterfaceImpl();

    void showBinSettings();
}
