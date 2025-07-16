/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab3;

/**
 *
 * @author Fariha
 */
class Student{
    int id;
    static String courseName="java";
    
    Student(int id){
        System.out.println(courseName);
        this.id=id;
}
     static void displayInfo()
    {
        System.out.println(courseName);
        //System.out.println(id);
    }
         
    
}


public class Lab3 {

    public static void main(String[] args) {
        
        Student s1=new Student(1);
        Student.displayInfo();
    }
}
