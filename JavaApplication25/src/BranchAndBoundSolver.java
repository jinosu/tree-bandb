import java.util.*;

class Item {

   public static Comparator<Item> byLabel() {
      return new Comparator<Item>() {
         public int compare(Item i1, Item i2) {
            return i1.label - i2.label;
         }
      };
   }
   
   public static Comparator<Item> byRatio() {
      return new Comparator<Item>() {
         public int compare(Item i1, Item i2) {
            return Double.compare(i2.getRatio(), i1.getRatio());
         }
      };
   }
@Override
   public String toString(){
       return ("Last Index "+ label+ " --->  WEIGHR: "+weight + " , VALUE: "+value);
   }
   public int label;
   public double value;
   public double weight;
   
   public double getRatio() {
      return value / weight;
   }
}

abstract class KnapsackSolver {

   protected List<Item> items;
   protected int capacity;
   
   protected KnapsackSolver(List<Item> items, int capacity) {
      this.items = items;
      this.capacity = capacity;
   }
   
   public abstract KnapsackSolution solve();
   
   public double getWeight(List<Item> items) {
      double weight = 0;
      for (Item item : items) {
         weight += item.weight;
      }
      return weight;
   }
   
   public double getValue(List<Item> items) {
      double value = 0;
      for (Item item : items) {
         value += item.value;
      }
      return value;
   }
} 

class KnapsackSolution {
   
   String approach;
   public List<Item> items;
   public double weight;
   public double value;
   
   @Override
   public String toString() {
       System.out.println("kkkkk");
      StringBuilder builder = new StringBuilder();
      builder.append(approach);
      builder.append(": ");
      builder.append(value);
      builder.append(" ");
      builder.append(weight);
      builder.append("\n");
      
      Collections.sort(items, Item.byLabel());
      
      for (Item item : items) {
         builder.append(item.label);
         builder.append(" ");
      }
      
      return builder.toString();
   }
}

public class BranchAndBoundSolver extends KnapsackSolver {
   public static ArrayList<resultbb> nodetree = new ArrayList<>();
   static public int c ;
   public static resultbb save;
   int count =0;
    static int ccc= 0;
   private class Node implements Comparable<Node> {
      public int index = 1;
      public int h;
      List<Item> taken;
      public double bound;
      public double value;
      public double weight;
      
      public Node() {
         taken = new ArrayList<Item>();
      }
      public Node(Node parent) {
         h = parent.h + 1;
         taken = new ArrayList<Item>(parent.taken);
         bound = parent.bound;
         value = parent.value;
         weight = parent.weight;
         
      }
      
      // Sort by bound
      public int compareTo(Node other) {
         return (int) (other.bound - bound);
      }
      
      public void computeBound(resultbb nodet) {
          //   System.out.println("_____________");  
             nodet.setY(h);
           //  System.out.println(h);
             nodet.setX(index);
           //  System.out.println(index);
         int i = h;
         double w = weight;
         bound = value;
         Item item;
         do {
            item = items.get(i);
            if (w + item.weight > capacity) break;
            w += item.weight;
            bound += item.value;
            i++;
          
         } while (i < items.size());
         bound += (capacity - w) * (item.value / item.weight);
         //System.out.println(bound); 
        nodet.setSum( bound);
      }
   }
   
   public BranchAndBoundSolver(List<Item> items, int capacity) {
      super(items, capacity);
   }
   
   @Override
   public KnapsackSolution solve() {
      count++;
      Collections.sort(items, Item.byRatio());
        
      Node best = new Node();
      Node root = new Node();
      resultbb nodest = new resultbb();
      root.computeBound(nodest);
       System.out.println(nodest.getX()+"   "+nodest.getY());
      PriorityQueue<Node> q = new PriorityQueue<Node>();
      PriorityQueue<resultbb> qq = new PriorityQueue<resultbb>();
      nodetree.add(nodest);
      q.offer(root);
      qq.offer(nodest);
      while (!q.isEmpty()) {
         resultbb nodet = qq.poll();
         Node node = q.poll();
        
         if (node.bound > best.value && node.h < items.size() - 1) { 
           
            Node with = new Node(node);
            Item item = items.get(node.h);
            with.weight += item.weight;
                
            if (with.weight <= capacity) {
               with.taken.add(items.get(node.h));
        
               with.value += item.value;
                with.index = node.index*2-1;
               nodet.setR(true);
               resultbb nodewith = new resultbb();
               with.computeBound(nodewith);
               nodetree.add(nodewith);
               
               if (with.value > best.value) {
                  best = with;  
                  save = nodewith;
                   for (Item item1 : best.taken) {
                       System.out.println(save.getX()+ "   "+save.getY());
                   }
                   System.out.println("lllllllllllllllllllllllllllllll");
                
               }
               if (with.bound > best.value) {
                        
                  qq.offer(nodewith);
                  q.offer(with);
               }
            }
        
              ccc++;
            Node without = new Node(node);
            resultbb nodewithout = new resultbb();
            without.index = node.index*2; 
            without.computeBound(nodewithout);
               nodetree.add(nodewithout);  
               nodet.setL(true);
            if (without.bound > best.value) {
            
               q.offer(without);
               qq.offer(nodewithout);
            }
         }
          // System.out.println("///////////////////////");
      }
       for (resultbb object : nodetree) {
           System.out.println(object.toString());
       }
 
      KnapsackSolution solution = new KnapsackSolution();
      solution.value = best.value;
      solution.weight = best.weight;
      solution.items = best.taken;
       System.out.println("//////////////////////////////////////////////////");
       for (Item item : best.taken) {
           System.out.println(item.label);
       }
{
          
      }
      solution.approach = "Using Branch and Bound the best feasible solution found";
      
      return solution;
   }

     public static void main(String[] args) {
       System.out.println("<n>\n1 value1 weight1\n2 value2 weight2\n.   .   .\n.   .   .\n.   .   .\nn valuen weightn\n<Capacity>");
      Scanner scanner = new Scanner(System.in);
      int count = scanner.nextInt();
      String Answer = "";
      List<Item> items = new LinkedList<Item>();
      for (int i = 0; i < count; i++) {
         Item item = new Item();
         item.label = scanner.nextInt();
         item.value = scanner.nextDouble();
         item.weight = scanner.nextDouble();
         items.add(item);
      }
      c=count;
      int capacity = scanner.nextInt();
      
      List<KnapsackSolver> solvers = new ArrayList<KnapsackSolver>();
      
      GUIPage g = new GUIPage();
      g.setText(items);
      solvers.add(new BranchAndBoundSolver(items, capacity));
      for (KnapsackSolver solver : solvers) {
         Answer += solver.solve()+"\n";
      }
       System.out.println(Answer);
      ANSWERGui a = new ANSWERGui();
      a.setText(Answer);
       System.out.println(Answer);
      Knapsack_Window window = new Knapsack_Window(g,a);
      window.setVisible(true);
       System.out.println("Times: "+count);
       System.out.println(ccc);
   }
}
		