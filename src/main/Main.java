package main;

import java.util.Iterator;

import bTree.BTree;
import strategies.OrderByGpa;
import strategies.OrderByName;

/******************************
 * 
 * Assignment2
 * Vaishnavi Munjuluru
 * vmunjuluru1967@sdsu.edu
 * 827140386
 * 
 ******************************/


public class Main {
    public static void main(String[] args) {
    	
    	BTree<Student> treeOrderByGpa = new BTree<Student>(new OrderByGpa());
    	 
    	treeOrderByGpa.add(new Student("Anthony", 82862,(float) 3.0));
    	treeOrderByGpa.add(new Student("An", 82862,(float) 1.6));
        treeOrderByGpa.add(new Student("Denver", 827876,(float) 2.0));
        treeOrderByGpa.add(new Student("Marshel", 82711,(float) 2.4));
        treeOrderByGpa.add(new Student("Fanah", 83762, (float)3.6));
        treeOrderByGpa.add(new Student("Albert", 8262,(float) 2.3));
        treeOrderByGpa.add(new Student("Cathy", 8290, (float) 2.2));
        treeOrderByGpa.add(new Student("Athy", 8290, (float) 3.1));
        treeOrderByGpa.add(new Student("Henry", 82712,(float) 3.2));
        treeOrderByGpa.add(new Student("Stuart", 82722,(float) 2.5));
        treeOrderByGpa.add(new Student("Patty", 89809, (float)3.5));
        treeOrderByGpa.add(new Student("Sean", 89855, (float)2.6));
        treeOrderByGpa.add(new Student("Hean", 439855, (float)2.1));
        treeOrderByGpa.add(new Student("Glory", 8921855, (float)4.0));
        
        System.out.println("Order By GPA\n"); //iterates in decreasing order as per the strategy condition
        Iterator iter = treeOrderByGpa.iterator();
        while(iter.hasNext()) {
         	System.out.println(iter.next().toString());
        } 
      
        BTree<Student> treeOrderByName = new BTree<Student>(new OrderByName());
       
        treeOrderByName.add(new Student("Thony", 82862, (float) 32));
        treeOrderByName.add(new Student("An", 82862, (float) 31));
        treeOrderByName.add(new Student("Denver", 827876, (float) 41));
        treeOrderByName.add(new Student("Marshel", 82711, (float) 42));
        treeOrderByName.add(new Student("Fanah", 83762, (float) 46));
        treeOrderByName.add(new Student("Albert", 8262, (float) 47));
        treeOrderByName.add(new Student("Cathy", 8290, (float) 40));
        treeOrderByName.add(new Student("Athy", 8290, (float) 45));
        treeOrderByName.add(new Student("Nthony", 82862, (float) 55));
        treeOrderByName.add(new Student("Anty", 82862, (float) 60));
        treeOrderByName.add(new Student("Enver", 827876, (float) 80));
        treeOrderByName.add(new Student("Arshel", 82711, (float) 70));
        treeOrderByName.add(new Student("Anah", 83762, (float) 76));
        treeOrderByName.add(new Student("Lbert", 8262, (float) 75));
        treeOrderByName.add(new Student("Hony", 82862, (float) 89));
        treeOrderByName.add(new Student("Aney", 82862, (float) 90));
        treeOrderByName.add(new Student("Dever", 827876, (float) 50));
        treeOrderByName.add(new Student("Mrshel", 82711, (float) 201));
        treeOrderByName.add(new Student("Fana", 83762, (float) 200));
        treeOrderByName.add(new Student("Bert", 8262, (float) 91));
        treeOrderByName.add(new Student("Cath", 8290, (float) 92));
        treeOrderByName.add(new Student("Anthon", 82862, (float) 120));
        treeOrderByName.add(new Student("Ani", 82862, (float) 121));
        treeOrderByName.add(new Student("Denv", 827876, (float) 150));
        treeOrderByName.add(new Student("Marshe", 82711, (float) 100));
        treeOrderByName.add(new Student("Marsl", 82711, (float) 30));
   
        System.out.println("\nOrder By Name\n"); //Iterates in decreasing order as forEach is for reverse in-order traversal)
        treeOrderByName.forEach(item->System.out.println(item.toString()));
    }
 }