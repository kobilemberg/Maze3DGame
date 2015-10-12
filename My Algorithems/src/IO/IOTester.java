package IO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;

public class IOTester {
	
	public static void main(String[] args) throws Exception
	{
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(3, 4, 5); //... generate it
		System.out.println("The original maze after generate:");
		System.out.println(maze);
		System.out.println("Byte array of the maze Before writing to the file:");
		byte[] mazeInByteArr = maze.toByteArray();
		System.out.print("[");
		for (int i = 0; i < mazeInByteArr.length; i++) {System.out.print(mazeInByteArr[i]+", ");}
		System.out.print("]");
		System.out.println("");
		
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		System.out.println("Writing to file:");
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		System.out.println("Finish the writing");
		InputStream in=new MyDecompressorInputStream(
		new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		System.out.println("Byte array of the maze After Reading to the file:");
		System.out.print("[");
		for (int i = 0; i < b.length; i++) {System.out.print(b[i]+", ");}
		System.out.print("]\n");
		Maze3d loaded=new Maze3d(b);
		System.out.println("The loaded maze:");
		System.out.println(loaded);
		System.out.println("Are the loaded maze and the original equals: "+loaded.equals(maze));
		File maz = new File("1.maz");
		System.out.println("The file size is: "+maz.length()+" bytes");
		
	}
}
