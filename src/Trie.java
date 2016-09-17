
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trie
{
    Setwords s;
    void setListener(Setwords s)
    {
        this.s=s;
    }
  Node root;
  
  Trie()
  {
      root=createnode();
  }
  
  Node createnode()
  {
      Node temp=new Node();
      temp.isEOW=false;
      return temp;
  }
  
    /**
     *
     * @param root
     * @param str
     */
   void insertWordInTrie(Node root,String word)
	{
            
	  int len=word.length();
	 
	  for(int i=0;i<len;i++)
	  {
	  if(root.next[word.charAt(i)-'a']==null)
	    root.next[word.charAt(i)-'a']=createnode();
          
	    root=root.next[word.charAt(i)-'a'];
          }
          root.isEOW=true;


	}
  
  
   
  StringBuilder str1=new StringBuilder();
  public void PrintTrie(Node root,String str)
  {
      if(root==null)
          return;
      
      if(root.isEOW)
      {
          str=str.concat(str1.toString());
          str1=new StringBuilder();
          s.setText(str);
           
          
      }
      
      for(int i=0;i<26;i++)
      {
          if(root.next[i]!=null && s!=null)
          {
              str1.append((char)(i+'a'));
              PrintTrie(root.next[i],str);
              str1.substring(0,str1.length());
          }
      }
      
  }
  
  public void printprefix(Node root,String str)
  {
      if(root==null)
          return;
      for(int i=0;i<str.length();i++)
      {
          if(root.next[str.charAt(i)-'a']!=null)
              root=root.next[str.charAt(i)-'a'];
      }
      
      PrintTrie(root,str);
      
  }
 
  

Node initializeTrie() throws IOException
{
    try {
        FileReader fin = new FileReader("countries.txt");
        Scanner sc = new Scanner(fin);
        String name, word;

        while (sc.hasNext()) {
            name = sc.next();
            word = name.toLowerCase();
            insertWordInTrie(root, word);
        }
        fin.close();
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    

  return root;
}

}