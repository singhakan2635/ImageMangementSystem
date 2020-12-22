package sample;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.*;
import com.drew.metadata.iptc.*;
import com.drew.metadata.jpeg.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ExtractMetaData class is called by the Controller class to give the parse the Metadata
 * of the selected image and provide its location properties if available
 */
public class ExtractMetaData
{
    /**
     * This method return the Controller class with the properties of the image.
     * This method has been implemented by using the Java Library MetaData Extractor
     * This library helps in extracting all the location information available in
     * the image. In this case, it will return three properties if available.
     * @param file - Take the image file as input
     * @return List<String> , this list contains all the properties like longitude, latitude and date
     * of the image in case the location information is available for the image.
     */
    public List<String> getMataData(File file) {
        List<String> properties = new ArrayList<>();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            if (metadata.containsDirectoryOfType(GpsDirectory.class)) {

                GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);

                //Check if Directory contains the specific Tag
                if (gpsDirectory.containsTag(GpsDirectory.TAG_LATITUDE) && gpsDirectory.containsTag(GpsDirectory.TAG_LONGITUDE)) {
                    properties.add(String.valueOf(gpsDirectory.getGeoLocation().getLongitude()));
                    properties.add(String.valueOf(gpsDirectory.getGeoLocation().getLatitude()));
                    properties.add(String.valueOf(gpsDirectory.getGpsDate()));

                    String metaDataString = "[Longtitude]: " + String.valueOf(gpsDirectory.getGeoLocation().getLongitude()) + "\n " +
                            "[Latitude]: " + String.valueOf(gpsDirectory.getGeoLocation().getLatitude()) + ", Date : " + String.valueOf(gpsDirectory.getGpsDate());
                    System.out.println(metaDataString);

                }
                else
                    {
                    System.out.println("Image File doesn't contain the location information");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return properties;
    }
}