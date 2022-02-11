package com.gui.ehealt_v2;

import UserManagement.HealthInformation;
import UserManagement.User;
import UserManagement.UserHolder;
import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
// import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * Class to control the elements for the main page
 * @author Viktor Benini; StudentID: 1298976
 */
public class MainPageController {

    @FXML
    MenuBar myMenuBar;
    @FXML
    private ImageView mainPageImageView;

    // used to switch scenes
    final private SceneController controller = new SceneController();

    /**
     * Switching to the login window if user logs out
     * @param event
     * @throws IOException
     */
    public void switchToLogin(ActionEvent event) throws IOException {
        controller.switchToLogin(event);
    }

    public void switchToLoginTwo(){}

    /**
     * Switch to appointment view stage, by calling method from SceneController
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onViewAppointmentButtonClick(ActionEvent event) throws IOException, SQLException {
        controller.switchToAppointmentView(event);
    }

    /**
     * Switch to make appointment, by calling method from SceneController
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    protected void onMakeAppointmentButtonClick(ActionEvent event) throws IOException, SQLException {
        controller.switchToMakeAppointment(event);
    }

    @FXML
    protected  void  switchToLoginTwo(ActionEvent event) throws IOException {
        controller.switchToLoginTwo(myMenuBar);
    }


    @FXML
    protected void onEditHealthInfoMenu(ActionEvent event) throws IOException {
        controller.switchToEditHealthInfoController(myMenuBar);
    }

    @FXML
    protected void onViewUserProfile(ActionEvent event) throws IOException{
        controller.switchToUserProfile(myMenuBar);
    }

    /**
     * Method that prints the health information into a PDF, by using the user from the user holder
     * and accessing his health info from the database
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    @FXML
    protected void onPrintMenuClick() throws FileNotFoundException, DocumentException {

        UserHolder userHolder = UserHolder.getInstance();
        User user = userHolder.getUser();
        HealthInformation hInfo = new HealthInformation();

        // healthInfo get queried out of db into hInfo
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Connection successful");
            PreparedStatement select = connection.prepareStatement("SELECT * FROM healthInfo WHERE userId = ?");
            select.setInt(1, user.getUserId());
            resultSet = select.executeQuery();
            System.out.println("Query successful");

            while(resultSet.next()){
                hInfo = new HealthInformation(resultSet.getString("socialSecurityNumber"), resultSet.getString("medicalRecordNumber"),
                        resultSet.getString("HealthInsuranceNumber"), resultSet.getString("height"), resultSet.getString("weight"),
                        resultSet.getString("medicineUse"), resultSet.getString("allergies"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


        // Print into PDF
        // TODO: adding images need an other structure of elements, if theres time i will implement this
   //     String dest = "E:/healthInfo.pdf";
   //     PdfWriter writer = new PdfWriter(dest);

        // reads path from ...Path.txt file
        Scanner scanner;
        String path = "C:\\";
        File file = new File("HealthInfoPath.txt");
        if(file.exists()){
            scanner = new Scanner(file);
            path = scanner.next();
            scanner.close();
        }


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path + "\\HealthInfo.pdf"));
        //PdfDocument pdfDocument = new PdfDocument(writer);
        document.open();
        System.out.println("Document was found and opened");

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 42, BaseColor.BLACK);
        Font textFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK);

        // add Title
        Chunk chunk = new Chunk("Health Information\n", titleFont);
        Paragraph title  = new Paragraph();
        title.add(chunk);
        document.add(title);

        // add image
        // ... functions in documentation not available ?!

        // adds user information to PDF
        Paragraph userContent = new Paragraph();
        userContent.setFont(textFont);
        userContent.add("\n\n\n" +
                user.getFirstname() + " " + user.getLastName() + "\n\n" +
                user.getStreet() + " " + user.getHousenumber() + "\n\n" +
                user.getZip() + " " + user.getTown());
        document.add(userContent);

        // adds health information to PDF
        Paragraph healthContent = new Paragraph();
        healthContent.setFont(textFont);
        healthContent.add("\n\n" +
                hInfo.getHeight() + " cm\n" +
                hInfo.getWeight() + " kg\n" );
        document.add(healthContent);



        document.close();
        System.out.println("Process successfully finished");
    }


    public void setImage(Image image){
        mainPageImageView.setImage(image);
    }

}
