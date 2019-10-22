import java.util.ArrayList;
import processing.core.*;

public class ProcessingTest extends PApplet{
    static int x,y;
    static int xx,yy;
    int c = Integer.MIN_VALUE;
    int max;
    int sum;
    int[] selectx,selecty;
       int k=0;
    ArrayList<resultbb> all = new ArrayList<>();

        Double space =1.;
          double size = 50;
            int por ;
            int pol;
    @Override
    public void settings(){
     
       xx=0;yy=0;
     
        size(1600,1000);
        x=1600-200;
        y=900;
 selectx = new int[ BranchAndBoundSolver.c+1];
 selecty = new int[ BranchAndBoundSolver.c+1];
 int t = BranchAndBoundSolver.save.getX();
 
 
  selectx[BranchAndBoundSolver.save.getY()] = BranchAndBoundSolver.save.getX();
  selecty[BranchAndBoundSolver.save.getY()] = BranchAndBoundSolver.save.getY();
  System.out.println(selectx[BranchAndBoundSolver.save.getY()]+"    "+selecty[BranchAndBoundSolver.save.getY()]);
     for (int i = 1; i < BranchAndBoundSolver.save.getY()+1; i++) {
        
         selecty[BranchAndBoundSolver.save.getY()-i] = BranchAndBoundSolver.save.getY()-i;
        if(t%2!=0){
            t++;
            t*=2;
            t/=4;
            selectx[BranchAndBoundSolver.save.getY()-i] = t;
        }
        else{
              t*=2;
              t/=4;
            selectx[BranchAndBoundSolver.save.getY()-i] = t;
        }
         System.out.println(selectx[BranchAndBoundSolver.save.getY()-i]+"    "+selecty[BranchAndBoundSolver.save.getY()-i]);
        }
     int l =  BranchAndBoundSolver.save.getX();
        for (int i = BranchAndBoundSolver.save.getY()+1; i < BranchAndBoundSolver.c+1; i++) {
            l*=2;
             selecty[i] = i;
             selectx[i] = l;
            System.out.println(selectx[i]+"    "+selecty[i]);
        }
     
        for (resultbb position : BranchAndBoundSolver.nodetree) {
            c = max(c, position.getY());
        }    
        c++;     
        resultbb n;
        max = (int) pow(2,c)*64;   
        System.out.println(c);
      for (int i = 0; i < c+1; i++) {
            for (int j = 1; j < pow(2, i)+1; j++) {
               n = new resultbb();
               n.setX(j);
               n.setY(i);
         //       System.out.println(i+"  "+j);
               all.add(n);
            }
        }
    }
    int delete(int posi){
      
        for (int i = 0; i <c-posi; i++) {
            sum+=pow(2, i);
        }
        sum*=32;
     
        return sum;
    }
    @Override
    public void draw() {
        textSize((float) (12*space));
        background(255);
   
   
        int i = 0;
            if (keyPressed) {
    if (key == 'x') {
     space = space*1.1;
      
    }
    if (key == 'z') {
  space = space/1.1;
    }
      if (key == 'n') {
k++;
delay(150);
if(k==3)
    k=0;
    }}
              
          if(k==1)
                   for (resultbb position : all) {            
            sum = 0;
            sum = delete(position.getY());
            por = (int) ((pow(2,-position.getY()))*(max)*(pow(2,position.getY()-1)+1-position.getX())-sum);
            pol = (int) ((pow(2,-position.getY()))*(max)*(pow(2,position.getY()-1)-position.getX())+sum);
            int pp = pol;
        if(position.getX()>pow(2,position.getY()-1)){             
          
              stroke(0);
             if(position.getY()!=c)
              line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((pp-(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));
                 if(position.getY()!=c)
              if(i!=0)
              line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((pp+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));
                 if(position.getY()!=c)
               if(i==0){
                  line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));
               }     fill(0);
                      noStroke();
              ellipse((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy),(int) (space *size), (int) (space *size));
          
                
                        }
          else{
            stroke(0);
              strokeWeight((float) (4*space)); 
                if(position.getY()!=c)
              line((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por-(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space)+yy);
           
                 if(position.getY()!=c)
              line((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space)+yy);
                 fill(0);
                 noStroke();
              ellipse((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy),(int) (space *size) , (int) (space *size));
             }
         i++;
        } 
            i=0;
            int number = 1;
        for (resultbb position : BranchAndBoundSolver.nodetree) {            
            sum = 0;
            sum = delete(position.getY());
            por = (int) ((pow(2,-position.getY()))*(max)*(pow(2,position.getY()-1)+1-position.getX())-sum);
            pol = (int) ((pow(2,-position.getY()))*(max)*(pow(2,position.getY()-1)-position.getX())+sum);
            int pp = pol;
        if(position.getX()>pow(2,position.getY()-1)){   
              stroke(255, 0, 0);
              
              if(position.isL())
              line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((pp-(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));
              
              if(position.isR()&&i!=0)
              line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((pp+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));
              
              else if(i==0&&position.isR())
                 line((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space+yy));

                fill(230,164,40);
                  if(k==2)
                   way(position);
                    	noStroke();
              ellipse((float) (pol*space+800)+xx, (float) ((128*position.getY()+128)*space+yy),(int) (space *size), (int) (space *size));
               fill(0);
               strokeWeight((float) (6*space)); 
                   text(String.valueOf(number), (float) ((pol-size/2+2)*space+800+xx), (float) ((128*position.getY()+128-25)*space+yy)); 
                   strokeWeight((float) (4*space)); 
                   text(String.valueOf(String.format("%.2f", position.sum)), (float) ((pol-size/2+2)*space+800+xx), (float) ((128*position.getY()+128+5)*space+yy)); 
          }
          else{
              strokeWeight((float) (4*space)); 
              stroke(255, 0, 0);
              if(position.isL())
              line((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por-(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space)+yy);
              if(position.isR())
              line((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy), (float) ((por+(pow(2,-position.getY()-2))*(max))*space+800)+xx, (float) ((128*(position.getY()+1)+128)*space)+yy);
                    fill(230,164,40);
                   if(k==2)
                   way(position);
                   	noStroke();
              ellipse((float) (por*space+800)+xx, (float) ((128*position.getY()+128)*space+yy),(int) (space *size) , (int) (space *size));
              fill(0);
              strokeWeight((float) (6*space)); 
                  text(String.valueOf(number), (float) ((por-size/2+2)*space+xx+800), (float) ((128*position.getY()+128-25)*space+yy)); 
                  strokeWeight((float) (4*space)); 
                text(String.valueOf(String.format("%.2f", position.sum)), (float) ((por-size/2+2)*space+xx+800), (float) ((128*position.getY()+128+5)*space+yy)); 
          }
         i++;
         number++;
        }      
    }
    void way(resultbb position){
       
        for (int i = 0; i < selectx.length; i++) {
            if(position.getX()==selectx[i]&&position.getY()==selecty[i]){ 
          
              fill(random(255),random(255),0);
       }
        }
       
    }
   
    
    int por(int x,int y){
        return (int) ((pow(2,-y))*(max)*(pow(2,y-1)+1-x)+xx-sum);   
    }
    int pol(int x,int y){
        return (int) ((pow(2,-y))*(max)*(pow(2,y-1)-x)+xx+sum);   
    }
      public void keyPressed() {
    
   if (key == CODED) {
      
    if (keyCode == UP) {
      yy+=120;
    } else if (keyCode == DOWN) {
      yy-=120;
    }
      else if (keyCode == RIGHT) {
      xx-=120;
      }
      else if (keyCode == LEFT) {
      xx+=120;
    } 
}}  
    public static void main (String... args) {   
        BranchAndBoundSolver.main(null);
        ProcessingTest pt = new ProcessingTest();
        PApplet.runSketch(new String[]{"ProcessingTest"}, pt);
    }
}