public interface PointCPInterface{
	double getX();
	double getY();
	double getRho();
	double getTheta();
	void convertStorageToPolar();
	void convertStorageToCartesian();
	String toString();
}