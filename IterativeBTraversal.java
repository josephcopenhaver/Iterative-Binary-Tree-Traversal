import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class BinaryTreeNode
{
   
   private static int numInstances=0;
   private int instanceID;
   private BinaryTreeNode left = null;
   private BinaryTreeNode right = null;
   
   public BinaryTreeNode() 
   {
      instanceID = numInstances++;
      //System.out.println("BinaryTreeNode " + instanceID + " created!");
   }
   
   public BinaryTreeNode getLeft()
   {
      return left;
   }
      
   public void setLeft(BinaryTreeNode n)
   {
      left = n;
   }
   
   public void setRight(BinaryTreeNode n)
   {
      right = n;
   }
   
   public BinaryTreeNode getRight()
   {
      return right;
   }
   
   public String toString()
   {
      return "BinaryTreeNode: " + instanceID;
   }
   
}

class BinaryTreePrinter
{
   
   public static void main(String[] args)
   {
      
      /**/
      BinaryTreeNode n = new BinaryTreeNode();
      BinaryTreeNode l = new BinaryTreeNode();
      BinaryTreeNode r = new BinaryTreeNode();
      l.setLeft(new BinaryTreeNode());
      l.setRight(new BinaryTreeNode());
      r.setLeft(new BinaryTreeNode());
      r.setRight(new BinaryTreeNode());
      n.setLeft(l);
      n.setRight(r);
      /**/
      
      /*/
      BinaryTreeNode n = new BinaryTreeNode();
      BinaryTreeNode l = new BinaryTreeNode();
      BinaryTreeNode r = new BinaryTreeNode();
      BinaryTreeNode ll = new BinaryTreeNode();
      BinaryTreeNode lr = new BinaryTreeNode();
      BinaryTreeNode rl = new BinaryTreeNode();
      BinaryTreeNode rr = new BinaryTreeNode();
      ll.setLeft(new BinaryTreeNode());
      ll.setRight(new BinaryTreeNode());
      lr.setLeft(new BinaryTreeNode());
      lr.setRight(new BinaryTreeNode());
      rl.setLeft(new BinaryTreeNode());
      rl.setRight(new BinaryTreeNode());
      rr.setLeft(new BinaryTreeNode());
      rr.setRight(new BinaryTreeNode());
      l.setLeft(ll);
      l.setRight(lr);
      r.setLeft(rl);
      r.setRight(rr);
      n.setLeft(l);
      n.setRight(r);
      /**/
      
      
      BinaryTreePrinter printer = new BinaryTreePrinter();
      
      printer.printTreePostfix(n);
      
      System.out.println();
      System.out.println();
      System.out.println();
      
      printer.printTreePrefix(n);
      
      System.out.println();
      System.out.println();
      System.out.println();
      
      printer.printTreeInfix(n);
      
   }
   
   public BinaryTreePrinter()
   {
      // Just to make this an object over a function
      // methods can be overwritten when inherited
   }
   
   private void printBinaryTreeNode(BinaryTreeNode n)
   {
      System.out.println(n);
   }
   
   public void printTreeInfix(BinaryTreeNode n)
   {
      System.out.println("INFIX");
      
      // Let n be the place holder for tree traversal paths
      
      if (n == null)
         return;
      
      Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
      
      // begin infix traversal
      // First iteration will never have a null root node
      // So it will be impossible to ever pop from an empty stack
      
      do
      {
         if (n == null)
         {
            // No right sub tree from previous iteration
            // Continue processing the stack
            n = stack.pop();
         }
         else
         {
            // There exists a right sub tree from previous iteration
            while (n.getLeft() != null)
            {
               stack.push(n);
               n = n.getLeft();
            }
         }
         printBinaryTreeNode(n);
         
         // Check for a right sub tree
         n = n.getRight();
         
      } while (!stack.isEmpty() || n != null);
   }
   
   public void printTreePrefix(BinaryTreeNode n)
   {
      System.out.println("PREFIX");
      
      // Let n be the place holder for tree traversal paths
      
      if (n == null)
         return;
      
      Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
      
      // begin Prefix traversal
      // First iteration will never have a null root node
      // So it will be impossible to ever pop from an empty stack
      
      do
      {
         if (n == null)
         {
            n = stack.pop();
         }
         printBinaryTreeNode(n);
         
         // Check for a right sub tree
         if (n.getRight() != null)
         {
            stack.push(n.getRight());
         }
         n = n.getLeft();
         
      } while (!stack.isEmpty() || n != null);
   }
   
   public void printTreePostfix(BinaryTreeNode n)
   {
      System.out.println("POSTFIX:");
      
      // Let n be the place holder for tree traversal paths
      
      if (n == null)
         return;
      
      Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
      
      BinaryTreeNode last = null;
      do
      {
         
         BinaryTreeNode currentRight = null;
         
         //System.out.println("Checking if ascending");
         while ((currentRight = n.getRight()) != null && currentRight == last)
         {
            //System.out.println("pop'n right side up");
            printBinaryTreeNode(n);
            last = n;
            if (stack.isEmpty())
            {
               n = null;
               break;
            }
            else
            {
               n = stack.pop();
            }
         }
         
         //System.out.println("Performing Depth first search");
         if (n != null)
         {
            while (true)
            {
               BinaryTreeNode currentLeft = null;
               //System.out.println("searchin Left");
               if ((currentLeft = n.getLeft()) != null && currentLeft != last)
               {
                  do
                  {
                     //System.out.println("stacking left");
                     stack.push(n);
                     n = currentLeft;
                  } while ((currentLeft = n.getLeft()) != null);
               }
               if (n.getRight() == null)
               {
                  //System.out.println("Leaf Node Found");
                  printBinaryTreeNode(n);
                  last = n;
                  n = stack.isEmpty() ? null : stack.pop();
                  break;
               }
               else
               {
                  //System.out.println("stacking right");
                  stack.push(n);
                  n = n.getRight();
               }
            }
         }
         
      } while (n != null);
   }
   
}
