package Algorithms;
import java.util.HashMap;
import java.util.ArrayList;

public class PatternMatching 
{
	//O(mn time complexity)
	public static ArrayList<Integer> bruteForceMatch(String text, String pattern)
	{
		ArrayList<Integer> matches = new ArrayList<>();
		int i = 0;
		while(i <= text.length() - pattern.length()) 
		{
			int j = 0;
			while(j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) 
			{
				j++;
			}
			if(j == pattern.length()) 
			{
				matches.add(i);
			}
			
			i++;
		}
		return matches;
	}
	
	//at worst case time complexity degenerates to O(mn) m == length of pattern
	//n == length of text	
	public static ArrayList<Integer> booyerMooreMatch(String text, String pattern)
	{
		ArrayList<Integer> matches = new ArrayList<>();
		HashMap<Character,Integer> lastIndex = new HashMap<>();
		for(int i = 0; i < pattern.length(); i++) 
		{
			lastIndex.put(pattern.charAt(i), i);
		}
		int i = 0;
		while(i <= text.length() - pattern.length())
		{
			int j = pattern.length() - 1;
			while(j >= 0 && text.charAt(i + j) == pattern.charAt(j)) 
			{
				j--;
			}
			if(j == -1) 
			{
				matches.add(i);
				i = i + pattern.length();
			}
			else 
			{
				int shift = lastIndex.getOrDefault(text.charAt(i + j), -1);
				if(shift < j) 
				{
					i = i + j - shift;
				}
				else 
				{
					i = i + 1;
				}
			}
		}
		return matches;
	}
	
	public static ArrayList<Integer> kmpMatch(String text,String pattern)
	{
		ArrayList<Integer> matches = new ArrayList<>();
		int[] failureTable = buildFailureTable(pattern);
		int j = 0;
		int k = 0;
		while(k < text.length()) 
		{
			if(pattern.charAt(j) == text.charAt(k)) 
			{
				if(j == pattern.length() - 1) 
				{
					matches.add(k - j);
					j = failureTable[j - 1];
				}
				else 
				{
					j++;
					k++;
				}
			}
			else if(pattern.charAt(j) != text.charAt(k) && j == 0) 
			{
				k++;
			}
			else 
			{
				j = failureTable[j - 1];
			}
		}
		return matches;
	}
	
	public static int[] buildFailureTable(String pattern) 
	{
		int[] failureTable = new int[pattern.length()];
		int i = 0;
		int j = 1;
		while(j < pattern.length()) 
		{
			if(pattern.charAt(i) == pattern.charAt(j))
			{
				failureTable[j] = i + 1;
				i++;
				j++;
			}
			else if(pattern.charAt(i) != pattern.charAt(j) && i == 0) 
			{
				failureTable[j] = 0;
				j++;
			}
			else 
			{
				i = failureTable[i - 1];
			}
		}
		return failureTable;
	}

	/**
	 * This is the same algorithm as above but it is the implementation that comes from the 
	 * CLRS textbook. pi is the prefix function and returns the length of the longest prefix that 
	 * is also a suffix of the prefix P[1...Q] where P is the prefix text and Q are the number of characters that have
	 * matched
	 * Note that q represents how many characters matched in the pattern and also what state that finite state machine
	 * is in. If 0 characters match then in the text we should look for the character P[0] in 
	 * order to move to the next state. Furthermore, if 1 character matched then we should
	 * be looking for the character P[1] in the text to move onto the next state. Thus, if we start at q at zero 
	 * we can use it as the index into pattern text unlike the computePrefixFunction. 
	 * 
	 */
	public static ArrayList<Integer> KMPMatcher(String text, String pattern)
	{
		ArrayList<Integer> matches = new ArrayList<>();
		int n = text.length();
		int m = pattern.length();
		int[] pi = computePrefixFunction(pattern);
		int q = 0; // q = how many characters have matched
		for(int i = 0; i < n; i++) 
		{
			while(q > 0 && pattern.charAt(q) != text.charAt(i)) 
			{
				q = pi[q];
			}
			if(pattern.charAt(q) == text.charAt(i)) 
			{
				q = q + 1;
			}
			if(q == m) 
			{
				matches.add(i - m);
				q = pi[q];
			}
		}
		return matches;
	}
	
	
	public static int[] computePrefixFunction(String pattern) 
	{
		int m = pattern.length();
		//pi[i] = next longest prefix that is suffix of P[1..p[i]]
		int[] pi = new int[pattern.length() + 1];
		pi[1] = 0;
		int k = 0;
		for(int q = 2; q < m + 1; q++) 
		{
			while(k > 0 && pattern.charAt(k) != pattern.charAt(q - 1))
			{
				k = pi[k];
			}
			
			if(pattern.charAt(k) == pattern.charAt(q - 1)) 
			{
				k = k + 1;
			}
			pi[q] = k;
		}
		return pi;
	}
	
	/**
	 * This is the finite automaton matcher as described in the CLRS textbook.
	 * The transition function is implemented using a two-d array. In the textbook the function 
	 * is described as delta(state,next character) = next state.
	 * The same is true for the two-d array in this function. To see what state you should go 
	 * to given a state and next character you use them as indexes. If you are in state q and 
	 * have the character c the next state is transitionFunction[q][c] which returns and integer 
	 * which corresponds to the next state.
	 */
	public static ArrayList<Integer> finiteAutomatonMatcher(String text, String pattern)
	{
		ArrayList<Integer> shifts = new ArrayList<>();
		int[][] transitionFunction = computeTransitionFunction(pattern);
		int q = 0;
		for(int i = 0; i < text.length(); i++) 
		{
			q = transitionFunction[q][text.charAt(i)];
			if(q == pattern.length()) 
			{
				shifts.add(i - pattern.length());
			}
		}
		return shifts;
	}
	
	/**
	 * The alphabet for this function is the first 128 characters in the ASCII table.
	 *  The transition function has pattern.length() + 1 rows because we need to calculate the 
	 *  the states {0,1,...m} thus pattern.length() + 1 ensures the amount of rows are {1,2,...m}
	 *  
	 *  The logic behind the second line in the function is due to the fact that given you have matched 
	 *  0 characters the only way to move to state 1 is by matching the first character in the pattern.
	 *  
	 *  psubqPrefix holds the prefix P[1...q] which is used to compare with all prefixes.
	 *  
	 *  The for loop calculates the out put of the function delta(q,next character) where next character are 
	 *  all characters in the alphabet. The inner function loops through all characters in the alphabet and 
	 *  appends the character to the string P[1...Q] 
	 *  We then find the largest prefix that is a suffix of P[1..Q] + the current character in the alphabet.
	 *   
	 *  	 
	 *
	 */
	static final int ALPHABET_SIZE = 128;
	public static int[][] computeTransitionFunction(String pattern) 
	{
		int[][] transitionFunction = new int[pattern.length() + 1][ALPHABET_SIZE];
		transitionFunction[0][pattern.charAt(0)] = 1;
		StringBuilder psubqPrefix = new StringBuilder();
		String[] prefixes = getAllPrefixes(pattern);
		for(int q = 1; q <= pattern.length(); q++) 
		{
			psubqPrefix.append(pattern.charAt(q - 1));
			for(int i = 0; i < 128; i++) 
			{
				psubqPrefix.append((char) i);
				int k = q + 1 < pattern.length() ? q +  1 : pattern.length();
				String compareStr = psubqPrefix.toString();
				while(compareStr.endsWith(prefixes[k]) == false) 
				{
					k--;
				}
				transitionFunction[q][i] = k;
				psubqPrefix.deleteCharAt(psubqPrefix.length() - 1);
				
			}
		}
		return transitionFunction;
	}
	
	public static String[] getAllPrefixes(String pattern) 
	{
		String[] prefixes = new String[pattern.length() + 1];
		for(int i = 0; i < prefixes.length; i++) 
		{
			prefixes[i] = pattern.substring(0,i);
		}
		return prefixes;
	}
	
}
