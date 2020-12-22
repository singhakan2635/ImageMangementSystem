package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

/**
 * ConvertImage class
 */
public class ConvertImage {
    @FXML
    private Label label_Header;
    @FXML
    private GridPane gridpane;

    private int width;
    private int height;
    private String destinationPath;

    /**
     * Constructor to initialise the value of label and gridpane.
     * @param label_header
     * @param gridpane
     */
    public ConvertImage(Label label_header, GridPane gridpane) {
        this.gridpane = gridpane;
        this.label_Header = label_header;
    }

    /**
     * In this method, the ProcessStarter is specified the local path for the ImageMagick
     * Library to avoid any conflict in processing the image. Instantiated the IMOperation
     * library to add new image into it. Now based on the user input we will resize the
     * image. Once the image is converted by the ImageMagick , we will save the image to
     * the destination path selected by the user.
     * @param sourcePath
     * @param width
     * @param height
     * @param destinationPath
     */
    public void convert(String sourcePath, int width, int height, String destinationPath)
    {
        this.width = width;
        this.height = height;
        this.destinationPath = destinationPath;

        ProcessStarter.setGlobalSearchPath("/usr/local/bin/");
        ConvertCmd cmd = new ConvertCmd();

        IMOperation imOperation = new IMOperation();
        imOperation.addImage();

        imOperation.resize(width, height);
        System.out.println("Properties: \n \t New Width: " + width + "\n \t New Height: " + height);
        imOperation.addImage();

        System.out.println("Converted Image Saved to Your Laptop at  ---> " + destinationPath + " . You can access your converted Image now!");
        Object[] listOfFiles = {sourcePath, destinationPath};
        try {
            cmd.run(imOperation, listOfFiles);
            displayOutputMsg();

        }
        /**Catching the exceptions */
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Display Output Message method set the label to visible ane set the diplay message after
     * the conversion of the image. The message contains the new dimension and the destionation
     * path of the converted image.
     */
    public void displayOutputMsg() {
        label_Header.setVisible(false);
        gridpane.getChildren().clear();

        /**Printing the message on the display screen
         */
        String s = "Congratulations! \n Your Image has \n been Converted!. \n New Dimension : \n" +
                "New Height : "+height+" \nNew Width : "+width+"\n"+"Destination: "+destinationPath;
        Label con2 = new Label(s);
        gridpane.add(con2, 0, 3);
    }
}
