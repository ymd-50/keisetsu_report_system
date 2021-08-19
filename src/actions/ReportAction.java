package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import services.ReportService;

public class ReportAction extends ActionBase {
    private ReportService reportService;

    @Override
    public void process() throws ServletException, IOException {
        reportService = new ReportService();
        doCommand();
        reportService.close();
    }

    public void index() throws ServletException, IOException {

    }

    public void entryNew() throws ServletException, IOException {

    }

    public void create() throws ServletException, IOException {

    }

    public void show() throws ServletException, IOException {

    }

    public void edit() throws ServletException, IOException {

    }

    public void update() throws ServletException, IOException {

    }

    public void destroy() throws ServletException, IOException {

    }



}
