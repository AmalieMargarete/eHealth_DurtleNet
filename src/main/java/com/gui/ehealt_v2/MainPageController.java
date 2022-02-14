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
 * Class to control the elements for the main page.
 * The mainPage has the about, appointment, profile and health info section. All of them are entered, by
 * opening a new window via the SceneController methods.
 * @author Viktor Benini; StudentID: 1298976
 */
public class MainPageController {

    @FXML
    MenuBar myMenuBar;
    @FXML
    private ImageView mainPageImageView;
    @FXML
    private Button logout_button;

    // used to switch scenes
    final private SceneController controller = new SceneController();

    /**
     * Switching to the login window if user logs out, by calling a method of the SceneController
     * @param event
     * @throws IOException
     */
    public void switchToLogin(ActionEvent event) throws IOException {
        controller.switchToLogin(event);
    }

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

    /**
     * The method is called by selecting the MenuItem edit under the writer healthInformation.
     * This opens a new window to edit the health information of the user, by calling a SceneController method.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    protected void onEditHealthInfoMenu(ActionEvent event) throws IOException, SQLException {
        controller.switchToEditHealthInfoController(myMenuBar);
    }

    /**
     * The method is called by pressing the MenuItem viewProfile under the writer Profile.
     * This opens a new window to view the users profile, by calling a SceneController method.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onViewUserProfile(ActionEvent event) throws IOException{
        controller.switchToUserProfile(myMenuBar);
    }

    /**
     * The method is called by pressing the menuItem about under the Help writer.
     * This opens a new window to show information about the team and the project, by calling the SceneController method.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onViewAbout(ActionEvent event) throws IOException{
        controller.switchToAbout(myMenuBar);
    }

    /**
     * The method is called by selecting the menuItem print under the writer HealthInformation.
     * This method creates a pdf with the health information provided by the database and the user
     * information provided by the user holder. The pdf is created by creating an object of the class document
     * made available by the iTextPDF API. We define the fonts used in the pdf as wall as the text segments called chunks
     * to specify the text, font and size. The file will be saved at the location stored in the HealthInfoPath.txt file.
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
        PdfWriter.getInstance(document, new FileOutputStream(path + "\\HealthInfo_"+user.getLastName()+ "_"+user.getFirstname()+".pdf"));
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


    /**
     * This method sets the provided image as the Image shown on the main page.
     * @param image
     */
    public void setImage(Image image){
        mainPageImageView.setImage(image);
    }

}
