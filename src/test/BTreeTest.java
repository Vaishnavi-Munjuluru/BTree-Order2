package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bTree.BTree;
import main.Student;
import strategies.OrderByGpa;
import strategies.OrderByName;

class BTreeTest {

  BTree < Student > testTreeByGpa;
  BTree < Student > testTreeByName;

  @BeforeEach
  void setup() {
	
    testTreeByGpa = new BTree < Student > (new OrderByGpa());
    testTreeByGpa.add(new Student("Henry", 82712, (float) 3.2));
    testTreeByGpa.add(new Student("Stuart", 82722, (float) 2.5));
    testTreeByGpa.add(new Student("Patty", 89809, (float) 3.5));
    testTreeByGpa.add(new Student("Sean", 89855, (float) 2.6));
    testTreeByGpa.add(new Student("Hean", 439855, (float) 2.1));
    testTreeByGpa.add(new Student("Glory", 8921855, (float) 4.0));
    testTreeByGpa.add(new Student("Anthony", 82862, (float) 3.0));
    testTreeByGpa.add(new Student("Denver", 827876, (float) 2.0));
    testTreeByGpa.add(new Student("Marshel", 82711, (float) 2.4));
    testTreeByGpa.add(new Student("Fanah", 83762, (float) 3.6));
    testTreeByGpa.add(new Student("Albert", 8262, (float) 2.3));
    testTreeByGpa.add(new Student("Cathy", 8290, (float) 2.2));
    testTreeByGpa.add(new Student("Athy", 8290, (float) 1.2));
    testTreeByGpa.add(new Student("Albert", 8262, (float) 2.3));
    testTreeByGpa.add(new Student("Cathy", 8290, (float) 2.2));
    testTreeByGpa.add(new Student("Athy", 8290, (float) 1.2));

    testTreeByName = new BTree < Student > (new OrderByName());
    testTreeByName.add(new Student("Thony", 82862, (float) 32));
    testTreeByName.add(new Student("An", 82862, (float) 31));
    testTreeByName.add(new Student("Denver", 827876, (float) 41));
    testTreeByName.add(new Student("Marshel", 82711, (float) 42));
    testTreeByName.add(new Student("Fanah", 83762, (float) 46));
    testTreeByName.add(new Student("Albert", 8262, (float) 47));
    testTreeByName.add(new Student("Cathy", 8290, (float) 40));
    testTreeByName.add(new Student("Athy", 8290, (float) 45));
    testTreeByName.add(new Student("Nthony", 82862, (float) 55));
    testTreeByName.add(new Student("Anty", 82862, (float) 60));
    testTreeByName.add(new Student("Enver", 827876, (float) 80));
    testTreeByName.add(new Student("Arshel", 82711, (float) 70));
    testTreeByName.add(new Student("Anah", 83762, (float) 76));
    testTreeByName.add(new Student("Lbert", 8262, (float) 75));
    testTreeByName.add(new Student("Hony", 82862, (float) 89));
    testTreeByName.add(new Student("Aney", 82862, (float) 90));
    testTreeByName.add(new Student("Dever", 827876, (float) 50));
    testTreeByName.add(new Student("Mrshel", 82711, (float) 201));
    testTreeByName.add(new Student("Fana", 83762, (float) 200));
    testTreeByName.add(new Student("Bert", 8262, (float) 91));
    testTreeByName.add(new Student("Cath", 8290, (float) 92));
    testTreeByName.add(new Student("Anthon", 82862, (float) 120));
    testTreeByName.add(new Student("Ani", 82862, (float) 121));
    testTreeByName.add(new Student("Denv", 827876, (float) 150));
    testTreeByName.add(new Student("Marshe", 82711, (float) 100));
    testTreeByName.add(new Student("Marsl", 82711, (float) 30));
  }

  @Test
  void testAddOrderByGpa() {
    assertEquals(testTreeByGpa.add(new Student("Parisy", 82862, (float) 1.3)), testTreeByGpa.add(new Student("Anony", 82862, (float) 3.9)));
  }

  @Test
  void testAddOrderByName() {
    assertEquals(testTreeByName.add(new Student("Parisy", 82862, (float) 3.3)), testTreeByName.add(new Student("Anony", 82862, (float) 3.9)));
  }

  @Test
  void testNext() {
    Iterator < String > iterator = testTreeByGpa.iterator();
    assertNotNull(iterator.next());
  }

  @Test
  void testHasNext() {
    Iterator < String > iterator = testTreeByGpa.iterator();
    assertEquals(iterator.hasNext(), true);
  }
  
  @Test
  void testIsEmpty() {
	  assertEquals(testTreeByGpa.isEmpty(), false);
  }
  
  @Test
  void testToString() {
	  assertNotNull(testTreeByName.toString());
  }
  
  @Test
  void testSize() {
	  assertEquals(testTreeByGpa.size(),16);
  }

  @Test
  void testForEach() {
	  testTreeByGpa.forEach(item->assertNotNull(item.toString()));
  }
  
  @Test
  void testToArray() {
	  assertNotNull(testTreeByName.toArray());
  }
}
