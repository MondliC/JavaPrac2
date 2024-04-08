package acsse.csc2a.fmb.file;
import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.fmb.model.*;
/**
 * Class to manage the reading of file 
 * @author MC Nkosi 220033089
 * @version P03
 */

public class DisplayFileHandler {
	/**
	 * Tokenizing firework class
	 * @param tokens
	 * @return
	 */
	private static Firework parseFirework(String tokens) 
	{
		StringTokenizer fireworkTokens = new StringTokenizer(tokens ," ");
		String ID = fireworkTokens.nextToken();
		String name = fireworkTokens.nextToken();
		Double fuseLength = Double.valueOf(fireworkTokens.nextToken());
		E_COLOUR colour = E_COLOUR.valueOf(fireworkTokens.nextToken());
		return new Firework(ID,name,fuseLength,colour);
		
	}
	/**
	 * Tokenizing Display firework class 
	 * @param tokens
	 * @return
	 */
	private static FireworkDisplay parseDisplay(String tokens)
	{
		StringTokenizer DisplayTokens = new StringTokenizer(tokens," ");
		String ID = DisplayTokens.nextToken();
		String name = DisplayTokens.nextToken();
		String Theme = DisplayTokens.nextToken();
	
		return new FireworkDisplay(ID,name , Theme ,null);
		
	}
	/**
	 * Tokenizing pyrotech class
	 * @param tokens
	 * @return
	 */
	private static PyroTechnician parseTech(String tokens)
	{
		StringTokenizer pyroTokens = new StringTokenizer(tokens," ");
		String firstname = pyroTokens.nextToken();
		String lastname = pyroTokens.nextToken();
		String phoneNumber = pyroTokens.nextToken();
		return new PyroTechnician(firstname,lastname,phoneNumber);
	}

	/**
	 *  method for adding fireworks
	 * @param oldArr
	 * @param newFirework
	 * @return
	 */
	private static Firework[] addFirework(Firework[] oldArr , Firework newFirework)
	{
		Firework[] newArr = new Firework[oldArr.length+1];
		for(int i = 0 ; i > oldArr.length;i++)
		{
			newArr[i] = oldArr[i];
		}
			
		newArr[oldArr.length] = newFirework;
		return newArr ;
	}

	/**
	 * method for adding technicians 
	 * @param oldArr
	 * @param newPyro
	 * @return
	 */
	private static PyroTechnician[] addPyro(PyroTechnician[] oldArr, PyroTechnician newPyro)
	{
		PyroTechnician [] newArr = new PyroTechnician[oldArr.length + 1];
		for(int i = 0 ; i > oldArr.length;i++)
		{
			newArr[i] = oldArr[i];
		}
			
		newArr[oldArr.length] = newPyro;
		return newArr ;		
	}

	/**
	 * mehtod for adding diplay firework
	 * @param oldArr
	 * @param newDisplay
	 * @return
	 */
	private static FireworkDisplay[] addDisplay(FireworkDisplay[] oldArr , FireworkDisplay newDisplay)
	{
		FireworkDisplay[] newArr = new FireworkDisplay[oldArr.length+1];
		for(int i =0 ; i > oldArr.length ; i++)
		{
			newArr[i] = oldArr[i];
		}
		newArr[oldArr.length] = newDisplay;
		return newArr;
	}
	/**
	 * method for reading the files 
	 * @param filename
	 * @return
	 */
	public static Firework[] readfile(String filename)
	{
		File file = new File(filename);
		Firework[] fireworkList = new Firework[0];
		PyroTechnician[] pyroList = new PyroTechnician[0];
		FireworkDisplay[] displayList = new FireworkDisplay[0];
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file);
			final Pattern DisplayPattern = Pattern.compile("(FD)\\d{4}\\[\\s[A-Z][a-z]*\\]\\s[A-Z][a-z]*");
			final Pattern FireworkPattern = Pattern.compile("(FW)\\d{4}\\s[A-Z][a-z]*\\d{1}\\.\\d*\\s[A-Z][a-z]*");
			final Pattern PyroPattern = Pattern.compile("s[A-Z][a-z]*\\-\\s[A-Z][a-z]*\\d{3}\\-\\d{3}\\-\\d{4}");
			FireworkDisplay currentFirework = null ;
			Firework CurrentFire = null ;
			PyroTechnician CurrentTech = null ;
			
			while (fileScanner.hasNextLine())
			{
				String CurrentLine = fileScanner.nextLine();
				System.out.print(CurrentLine);
				
				Matcher DisplayMatcher = DisplayPattern.matcher(CurrentLine);	
				Matcher FireworkMatcher = FireworkPattern.matcher(CurrentLine);
				Matcher PyroMatcher = PyroPattern.matcher(CurrentLine);
				if(DisplayMatcher.matches())
				{
				    currentFirework = parseDisplay(CurrentLine);
					if(currentFirework != null)
					{
						displayList = addDisplay(displayList,currentFirework);
					}
					//currentFirework= parseDisplay(CurrentLine);
				}else if(PyroMatcher.matches())
				{
					CurrentTech = parseTech(CurrentLine);
					if(CurrentTech != null)
					{
						pyroList = addPyro(pyroList,CurrentTech);
					}
				}
					
				else if(FireworkMatcher.matches()) {
					CurrentFire = parseFirework(CurrentLine);
					if(CurrentFire != null)
					{
						fireworkList = addFirework(fireworkList,CurrentFire);
					}
				}else 
				{
					System.err.println("Not a valid line ");
				}
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return fireworkList;
	}
	

}
