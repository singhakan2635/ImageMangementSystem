package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;

/**
 * Controller class implements all the operations which is performed on the Application.
 * We have to initialise all the FXML elements before we can use them in the method.
 */
public class Controller implements EditingProperties {
    @FXML
    private BorderPane borderPane;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField textWidth;
    @FXML
    private TextField textHeight;
    @FXML
    private GridPane gridpane;
    @FXML
    private Label label_Header;
    @FXML
    private Label imageSelected;
    @FXML
    private Button ButtonConvert;
    @FXML
    private Button ButtonUpload;
    @FXML
    private ImageView imageview;

    /**
     * All the variables which is required by the class has been initialize here
     * to have their scope for the entire class. SOme of these variables are being used by
     * more than one method. In some cases, some of these variables are getting their value
     * changed by different methods based on their requirements.
     */
    private Image image;
    private Image imageOrg;
    private List<File> files;
    private boolean IsUploadDone;
    private boolean uploadCheck;
    private double newImageHeight;
    private double newImageWidth;
    private FileInputStream input;

    /**
     * Once the Choose the image button is clicked, imageProcessing method is called.
     * This method first clear the gridpane to make sure it has no previous data.
     * After that, once the image file is selected from the user computer, it checks for the
     * file validation by using FileChooser library.
     * Now based on our requirement, it sets the imageView and textArea to display the
     * image and the properties of the image.
     * For displaying the properties of the image it calls the class ExtractMetaData.
     * If file selection is cancelled then it will show "You have Cancelled the Selection of Image".
     */

    @FXML
    public void imageProcessing() {
        String text;
        String sourcePath;
        String imageName;
        gridpane.getChildren().clear();
        label_Header.setVisible(true);
        List<String> properties;
        int count = 0;
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files Types", "*.jpeg", "*.png", "*.gif", "*.jpg", "*.mp4")
        );
        files = chooser.showOpenMultipleDialog(borderPane.getScene().getWindow());

        if (files != null) {
            for (int i = 0; i < files.size(); i++) {
                try {
                    Label textArea = new Label();
                    File file = files.get(i);
                    int indexX = i % 5;
                    int indexY = i / 5;
                    count++;

                    sourcePath = files.get(i).getPath();

                    System.out.println("SourcePath is " + sourcePath);
                    imageName = file.getName();

                    ExtractMetaData extractMetaData = new ExtractMetaData();
                    properties = extractMetaData.getMataData(file);


                    image = new Image(file.toURI().toString());
                    input = new FileInputStream(file);
                    imageOrg = image;
                    image = new Image(input);


                    /**
                    *One of the project requirement is to show the thumbnail of the image which has
                    *a dimensions of 100*100 .
                      */
                    newImageHeight = 100;
                    newImageWidth = 100;
                    imageview = new ImageView(image);
                    imageview.setFitHeight(newImageHeight);
                    imageview.setFitWidth(newImageWidth);

                    /**
                    * Setting up the TextArea with the Image Properties
                     */
                    if (properties.size() > 0) {
                        text = "  Name: " + imageName + "\n" +
                                "  Height: " + image.getHeight() + "\n" +
                                "  Width: " + image.getWidth() + "\n" +
                                "  Longitude: " + properties.get(0) + "\n" +
                                "  Latitude:  " + properties.get(1) + "\n" +
                                "  Date:  " + properties.get(2);
                    } else {
                        text = "   Name: " + imageName + "\n" +
                                "  Height: " + image.getHeight() + "\n" +
                                "  Width: " + image.getWidth();
                    }

                    /**
                     * Setting up the textarea properties of font and style
                     */
                    textArea.setFont(Font.font("Serif", FontWeight.BOLD, 15));
                    textArea.setStyle("-fx-text-fill: white ;");
                    textArea.setText(text);
                    if (count > 5) {
                        indexY += 4;
                    }
                    if (count > 10) {
                        break;
                    }
                    gridpane.add(imageview, indexX, indexY);
                    gridpane.add(textArea, indexX, indexY + 1);
                    uploadCheck = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("You have Cancelled the Selection of Image");
        }
    }

    /**
     * After the user clicks on the convert & save button - this method is invoked.
     * ConvertImages method take all the new width and new height specified by the
     * user, including the source path and destination path and convert the image
     * in the specified format.
     * @throws IOException
     */
    @FXML
    public void ConvertImages() throws IOException {
        /**
         * Initializing properties i.e new height and new width and implementing convert operation
         * */
        if (uploadCheck) {
            int width = 0;
            int height = 0;
            int newWidth;
            int newHeight;
            FileInputStream input;

            String filePath;
            String sourcePath;
            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(borderPane.getScene().getWindow());
            for (int i = 0; i < files.size(); i++) {
                chooser.setInitialFileName(file.getName());
                chooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*." + choiceBox.getValue())
                );
                String destinationPath = "";
                File imageFile = files.get(i);
                input = new FileInputStream(imageFile);
                image = new Image(input);
                sourcePath = files.get(i).getPath();
                try {
                    filePath = file.getPath();
                    if (files.size() > 1) {
                        destinationPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
                        destinationPath += imageFile.getName().substring(0, imageFile.getName().lastIndexOf("."));
                        destinationPath += ("." + choiceBox.getValue().toString());
                    } else {
                        destinationPath = filePath;
                        destinationPath += ("." + choiceBox.getValue());
                    }
                    /**
                     * For Converting the Image with new height and new width , also into new format -
                     * We have called the class ConvertImage. We have Instantiated the class with
                     * label and gridpane. Then we will call the method "convert" by passing the parameters
                     * sourcePath, new Width, new Height and the destination path in the user system
                     * to convert the image and save it in the system.
                     * In case the User doesn't specify the new height and width, we will
                     * use the original image width and height to convert the image.
                     */

                    ConvertImage convertImg = new ConvertImage(label_Header, gridpane);

                    if (textHeight.getText().equals("") && textWidth.getText().equals("")) {
                        width = (int) image.getWidth();
                        height = (int) image.getWidth();
                        System.out.println("width: " + width);
                        System.out.println("height: " + height);
                        System.out.println("sourcePath: " + sourcePath);
                        System.out.println("destinationPath: " + destinationPath);

                        convertImg.convert(sourcePath, width, height, destinationPath);
                    } else if ((textHeight.getText().equals("") && !textWidth.getText().equals(""))
                            || (!textHeight.getText().equals("") && textWidth.getText().equals(""))) {
                        IsUploadDone = true;
                        break;
                    } else if (Integer.parseInt(textHeight.getText()) > 10000 || Integer.parseInt(textWidth.getText()) > 10000) {
                        IsUploadDone = true;
                        break;
                    } else {
                        newWidth = Integer.parseInt(textWidth.getText());
                        newHeight = Integer.parseInt(textHeight.getText());

                        width = newWidth;
                        height = newHeight;
                        convertImg.convert(sourcePath, width, height, destinationPath);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            uploadCheck = false;
            textWidth.clear();
            textHeight.clear();

        } else {
            imageSelected.setText("Please Select an Image to Convert");
            imageSelected.setVisible(true);
        }
    }

    /**
     * ZoomIn Image is implemented from the Interface Editing Properties.
     * This method increase the image width and image height by 10%
     * everytime this method is invoked.
     */
    @Override
    public void zoomInImage() {
        newImageWidth = newImageHeight * (1.1);
        newImageHeight = newImageHeight * (1.1);
        displayImage();

    }

    /**
     * ZoomOut Image is implemented from the Interface Editing Properties.
     * This method decreases the image width and image height by 10%
     * everytime this method is invoked.
     */
    @Override
    public void zoomOutImage() {
        newImageWidth = newImageHeight * (0.9);
        newImageHeight = newImageHeight * (0.9);
        displayImage();
    }

    /**
     * Display image Method set the imageview with the new image width and height
     * based on the changes.
     */
    private void displayImage() {
        //imageview = new imageview(image);
        imageview.setFitHeight(newImageWidth);
        imageview.setFitWidth(newImageHeight);
    }

    /**
     * This is FXML implementation of the method for the ZoomIn button. It calls the method
     * ZoominImnage, everytime its clicked.
     */
    @FXML
    public void ZoomIn() {
        zoomInImage();
    }

    /**
     * This is FXML implementation of the method for the ZoomOut button. It calls the method
     * zoomOutImage, everytime its clicked.
     */
    @FXML
    public void ZoomOut() {
        zoomOutImage();
    }

    /**
     * This is FXML implementation of the method for the Rotate button. It calls the method
     * rotateImage, everytime its clicked.
     */
    public void Rotate(ActionEvent actionEvent) {
        rotateImage();
    }

    /**
     * Rotate Image is implemented from the Interface Editing Properties.
     * This method method will rotate the image to 90 degree
     * everytime this method is invoked.
     */
    @Override
    public void rotateImage() {
        imageview.setRotate(imageview.getRotate() + 90);
    }

    /**
     * Rotate Image is implemented from the Interface Editing Properties.
     * This method method will rotate the image to 90 degree
     * everytime this method is invoked.
     */
    @Override
    public void changeToGrayScale() {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        imageview.setEffect(colorAdjust);
    }

    /**
     * This is FXML implementation of the method for the GrayScale button. It calls the method
     * changeToGrayScale, once its clicked.
     * @param actionEvent
     */
    public void GreyScale(ActionEvent actionEvent) {
        changeToGrayScale();
    }

    /**
     * This is FXML implementation of the method for the Revert button. It calls the method
     * revertChanges, once its clicked.
     * @param actionEvent
     */
    public void Revert(ActionEvent actionEvent)
    {
        revertChanges();
    }

    /**
     * Revert Changes is implemented from the Interface Editing Properties.
     * This method method will revert any changes done to the image
     * everytime this method is invoked.
     */
    @Override
    public void revertChanges()
    {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(0);
        imageview.setImage(imageOrg);
        imageview.setEffect(colorAdjust);
        imageview.setFitWidth(100);
        imageview.setFitHeight(100);
    }
}
