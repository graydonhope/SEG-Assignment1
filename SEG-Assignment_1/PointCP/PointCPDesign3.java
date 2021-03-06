// This file contains material supporting section 2.9 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

/**
 * This class contains instances of coordinates in either polar or
 * cartesian format.  It also provides the utilities to convert
 * them into the other type. It is not an optimal design, it is used
 * only to illustrate some design issues.
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @version July 2000
 */
public class PointCPDesign3 implements PointCPInterface
{
  //Instance variables ************************************************

  /**
   * Contains C(artesian) or P(olar) to identify the type of
   * coordinates that are being dealt with.
   */
  private char typeCoord;
  
  /**
   * Contains the current value of X or RHO depending on the type
   * of coordinates.
   */
  private double x;
  
  /**
   * Contains the current value of Y or THETA value depending on the
   * type of coordinates.
   */
  private double y;
	
  
  //Constructors ******************************************************

  /**
   * Constructs a coordinate object, with a type identifier.
   */
  public PointCPDesign3(char type, double xOrRho, double yOrTheta)
  {
    if(type != 'C' && type != 'P')
      throw new IllegalArgumentException();
    this.x = xOrRho;
    this.y = yOrTheta;
    typeCoord = type;
    if(type == 'P'){
      convertStorageToCartesian();
    }
  }
	
  
  //Instance methods **************************************************
 
 
  public double getX()
  {
    if(typeCoord == 'C') 
      return x;
    else 
      return (Math.cos(Math.toRadians(y)) * x);
  }
  
  public double getY()
  {
    if(typeCoord == 'C') 
      return y;
    else 
      return (Math.sin(Math.toRadians(y)) * x);
  }
  
  public double getRho()
  {
    if(typeCoord == 'P') 
      return x;
    else 
      return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
  }
  
  public double getTheta()
  {
    if(typeCoord == 'P')
      return y;
    else 
      return Math.toDegrees(Math.atan2(y, x));
  }
  
	
  /**
   * Converts Cartesian coordinates to Polar coordinates.
   */
  public void convertStorageToPolar()
  {
    if(typeCoord != 'P')
    {
      //Calculate RHO and THETA
      double temp = getRho();
      y = getTheta();
      x = temp;
      
      typeCoord = 'P';  //Change coord type identifier
    }
  }
	
  /**
   * Converts Polar coordinates to Cartesian coordinates.
   */
  public void convertStorageToCartesian()
  {
    if(typeCoord != 'C')
    {
      //Calculate X and Y
      double temp = getX();
      y = getY();
      x = temp;
   
      typeCoord = 'C';	//Change coord type identifier
    }
  }

  /**
   * Calculates the distance in between two points using the Pythagorean
   * theorem  (C ^ 2 = A ^ 2 + B ^ 2). Not needed until E2.30.
   *
   * @param pointA The first point.
   * @param pointB The second point.
   * @return The distance between the two points.
   */
  public double getDistance(PointCPDesign3 pointB)
  {
    // Obtain differences in X and Y, sign is not important as these values
    // will be squared later.
    double deltaX = getX() - pointB.getX();
    double deltaY = getY() - pointB.getY();
    
    return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  /**
   * Rotates the specified point by the specified number of degrees.
   * Not required until E2.30
   *
   * @param point The point to rotate
   * @param rotation The number of degrees to rotate the point.
   * @return The rotated image of the original point.
   */
  public PointCPDesign3 rotatePoint(double rotation)
  {
    double radRotation = Math.toRadians(rotation);
    double X = getX();
    double Y = getY();
        
    return new PointCPDesign3('C',
      (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
      (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
  }

  /**
   * Returns information about the coordinates.
   *
   * @return A String containing information about the coordinates.
   */
  public String toString()
  {
    return "Stored as " + (typeCoord == 'C' 
       ? "Cartesian  (" + getX() + "," + getY() + ")"
       : "Polar [" + getRho() + "," + getTheta() + "]") + "\n";
  }
}