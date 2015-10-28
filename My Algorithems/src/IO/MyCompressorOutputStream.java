package IO;

import java.io.IOException;
import java.io.OutputStream;


/**
 * @author Kobi Lemberg
 * @version 1.0
 * <h1> MyCompressorOutputStream </h1>
 * This class extends OutputStream, the class instances job is to write to files a compressed 3D mazes. 
 */
public class MyCompressorOutputStream extends OutputStream {
	OutputStream out;
//Constructors
	/**
	 * Instantiates a new MyCompressorOutputStream with OutputStream as data member
	 *@param out OutputStream represent an OutputStream to work with
	 */
	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;		
	}
//Overrides
	@Override
	/**
	 * This method implements abstract method of OutputStream.
	 * The method job is to write an integer in to OutputStream.
	 *@param arg0 int, represent the int to write to the OutputStream
	 */
	public void write(int arg0) throws IOException {out.write(arg0);}
	
	@Override
	/**
	 * This method overrides method of OutputStream.
	 * The method job is to write an byte[] in to OutputStream.
	 * The difference from the original function is the way of writing.
	 * The writing is with simple compressed algorithm that write the byte value and the number of his "shows"
	 *@param arg0 byte[], represent the array to compress and write to the OutputStream
	 */
	public void write(byte[] arg0) throws IOException {
		byte last = arg0[36];
		byte num=0;
		for (int i = 0; i < 36; i++) {
			write(arg0[i]);
			flush();
		}
		for (int i = 36; i < arg0.length; i++) {
			if (arg0[i] ==last)
				num++;
			else
			{
				write(last);
				flush();
				write(num);
				flush();
				last=arg0[i];
				num=1;
			}
		}
		write(last);
		flush();
		write(num);
		flush();
	}
}
