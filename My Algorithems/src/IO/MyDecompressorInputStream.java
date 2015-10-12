package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> MyDeCompressorInputStream </h1>
 * This class extends InputStream, the class instances job is to read from files that contains a compressed 3D mazes. 
 */
public class MyDecompressorInputStream extends InputStream {
	InputStream in;
//Constructors
	/**
	 * Instantiates a new MyDecompressorInputStream with InputStream as data member
	 *@param in InputStream represent an InputStream to work with
	 *@return new MyDecompressorInputStream with InputStream as data member with instance.
	 */
	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;		
	}

	@Override
	/**
	 * This method implements abstract method of InputStream.
	 * The method job is to Read integers from InputStream.
	 */
	public int read() throws IOException {return in.read();}
	
	@Override
	/**
	 * This method overrides method of InputStream.
	 * The method job is to read a compressed byte[] in to InputStream.
	 * The difference from the original function is the way of reading.
	 * The reading is with simple de-compressed algorithm that read the byte value and the number of his "shows"
	 *@param arr byte[], represent the resource array to write
	 */
	public int read(byte[] arr) throws IOException {
		in.read(arr, 0, 36);
		int lastIndexInArray=36;
		int amount=0;
		int current;
		for (int i = 37; i < arr.length+36; i+=2)
		{
			current = in.read();
			amount = in.read();
			for (int j = lastIndexInArray; j < lastIndexInArray+amount; j++) {
				arr[j] = (byte) current;
			}
			lastIndexInArray+=amount;
		}
		return 0;	
	}
}