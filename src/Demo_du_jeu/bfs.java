package Demo_du_jeu;

import java.util.*;
public class bfs{
	static class Node{
		int x,y;
		int k;
		Node(int x,int y,int k){  
            this.x=x;
            this.y=y;
            this.k=k;
		}

	}
	public static void main(String[] args) {
		String[][] map={{"1","1","1","0","0"},
                        {"1","0","1","1","0"},
                        {"1","0","1","1","1"},
                        {"1","1","0","1","0"},
                        {"0","1","1","1","1"}};
        int[] s={0,0};
        int[] e={4,1};
        bfs(5,5,map,s,e);
		               
	}

	public static void bfs(int n,int m,String[][] map,int[] start,int[] end){
            Queue<Node> que=new LinkedList<Node>();
            int[][] Mark=new int[n][m];
            int[] ox = {1,0,-1,0};
            int[] oy = {0,1,0,-1};
            que.offer(new Node(start[0],start[1],0));//temp change
            int result=-1;
            Node t;
            int step=1;
            while(!que.isEmpty()){
                  t=que.poll();
                  System.out.println("on step "+step+" : ");
                  step++;
                  System.out.println(" now your location is :(" +t.x+","+t.y+")");
                 for(int i=0;i<4;i++){
                 	  
                      if((t.x+ox[i])==end[0]&&(t.y+oy[i])==end[1]&&(result==-1||result>(t.k+1))){
                      	result=t.k+1;
                      	System.out.println("reach one");
                      	continue;
                      }
                      if((t.x+ox[i])>=0&&(t.x+ox[i])<n&&(t.y+oy[i])>=0&&(t.y+oy[i])<m){
                      	if(map[t.x+ox[i]][t.y+oy[i]]=="1"){
                      		System.out.println("i="+i);
                      
                      		if(Mark[t.x+ox[i]][t.y+oy[i]]==0){
                          	que.offer(new Node(t.x+ox[i],t.y+oy[i],t.k+1));
                          	Mark[t.x+ox[i]][t.y+oy[i]]=t.k+1;
                          	System.out.println("next location is:("+(t.x+ox[i])+","+(t.y+oy[i])+")");
                          }else if(Mark[t.x+ox[i]][t.y+oy[i]]>(t.k+1)){
                          	  System.out.println("location :(" +t.x+ox[i]+","+t.y+oy[i]+")have been achieced!");
                              que.offer(new Node(t.x+ox[i],t.y+oy[i],t.k+1));
                          }
                      	}
                          
                      }
                 }
            }
            System.out.println(result);
	}
}