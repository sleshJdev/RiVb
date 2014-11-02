package by.slesh.ri.cp.victoriabrel.app;

import by.slesh.ri.cp.victoriabrel.app.controller.ActionController;
import by.slesh.ri.cp.victoriabrel.app.controller.BinController;
import by.slesh.ri.cp.victoriabrel.app.controller.FileController;
import by.slesh.ri.cp.victoriabrel.app.model.Model;
import by.slesh.ri.cp.victoriabrel.app.view.MainFrameView;
import by.slesh.ri.cp.victoriabrel.app.view.service.MainViewInterface;

public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Model model = new Model();
        MainViewInterface view = new MainFrameView();
        FileController fileController = new FileController(view, model);
        ActionController actionController = new ActionController(view, model);
        BinController binController = new BinController(view, model);
    }

}
