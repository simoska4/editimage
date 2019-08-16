# EditImage

Edit-Image is a Java library implements methods to edit a BufferedImage.


## Sample

### Input Image
We get the following Image as input and then we show the results using the methods implemented.  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/colosseum.jpg)

### Gray-Scale method
This method converts the BufferedImage to a grayscale format.  
``BufferedImage grayScale = EditImage.convertGrayScale(image);``  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/gray_scale.png)

### Scaled method
This method resizes the BufferedImage. The target widht (200, in this example) and the height width (150, in this example) must be given as input.  
``BufferedImage scaled = EditImage.getScaled(image, 200, 150);``  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/scaled.png)

### Rotate method
This method rotate the BufferedImage. The rotation angle (180°, in this example) must be given as input.  
``BufferedImage rotated = EditImage.rotateByDegrees(image, 180);``  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/rotated.png)

### Threshold method (with default threshold)
This method converts the BufferedImage to a binary format (B/W). If we do not set the threshold, the default will be used.  
``BufferedImage thresholdImage = EditImage.thresholdImage(image);``  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/threshold.png)

### Threshold method (with a given threshold)
This method converts the BufferedImage to a binary format (B/W). In this example a value 100 of threshold is provided.  
``BufferedImage thresholdImage_100 = EditImage.thresholdImage(image, 100);``  
![Original BufferedImage](https://github.com/simoska4/editimage/blob/master/sample/threshold_100.png)



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


## License
[MIT](https://choosealicense.com/licenses/mit/)
