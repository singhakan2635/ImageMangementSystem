package sample;

/**
 * This Interface is implemented by the Controller class which override its all methods
 * These methods are the filter options available in the application.
 * This State design helps in implementing these important features.
 */
public interface EditingProperties
{
    void zoomInImage();

    void zoomOutImage();

    void rotateImage();

    void changeToGrayScale();

    void revertChanges();

}
