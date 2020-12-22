# group-project-imagemanagementtool2020
group-project-imagemanagementtool2020 created by GitHub Classroom

# Image Management Tool
This JavaFx application takes the image from the user and convert the image using ImageMagick into different file format.

![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.37.52%20PM.png?raw=true)

  -  Once the user clicks on the "Choose Your Image" - Pop up to select the image from the desktop
  -  User can select any file format for the image
  -  Once the image file is selected, the selected image is diplayed on the canvas in 100x100 thumbnail size for the reference
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.38.03%20PM.png?raw=true)
  
  -  User has five type of Filter Options to edit the image in the canvas - Zoom In, Zoom Out, Rotate the Image 90 Degree , GrayScale the Image and Revert the changes
  
  -  Zoomin Option - Whenever the user clicks on zoomIn, it increases the dimension by 10% of image.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.38.24%20PM.png?raw=true)
  
  -  ZoomOut Option - Whenever the user clicks on zoomOut, it decreases the dimension by 10% of image.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.38.32%20PM.png?raw=true)
  
  -  Rotate Option - Whenever the user clicks on rotate, it will rotate the image by 90 degree everytime.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.38.40%20PM.png?raw=true)
  
  -  GrayScale Option - Whenever the user clicks on grayscale, it will convert the image into black and white once.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.39.00%20PM.png?raw=true)
  
  -  Revert Changes Option - When the user clicks on this button, it revert the image into the original thumbnail dimension of 100x100 with color.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%2010.28.14%20PM.png?raw=true)
  
  -  Then User has choices of image format to convert the uploaded image into - jpg, png, gif
  -  User also have to speciy the dimension - Width and Height of the converted image to save and download the image into their system.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%204.39.19%20PM.png?raw=true)
  
  -  Once clicked on the "Convert & Save the Image" Button, pop up will ask the user to specify the path for the converted image to be downloaded.
  
  ![alt text](https://github.com/NEU-GradStudents/group-project-imagemanagementtool2020/blob/master/ScreenShots/Screenshot%202020-12-16%20at%209.28.51%20PM.png?raw=true)
  
  
  # Metadata Extractor
  Metadata Extractor is a Java library for reading metadata from media files. This library is developed by Drew Noakes.
  
  The easiest way is to install the library via its Maven package.
  
  ```xml
    <dependency>
      <groupId>com.drewnoakes</groupId>
      <artifactId>metadata-extractor</artifactId>
     <version>2.15.0</version>
    </dependency>
  ```
  
  This library helped in extracting the latitude, longitude and date of the image from metadata.
  
  # Project Structure
  -   This Project implements the State design pattern
  -    The Interface - EditingProperties helps in implemeting all the filters. This interface is implemeted by the Controller Class.
  
