package com.student.StudentManagement.enumurations;

public enum Diplomat {

   DEUST , LST,MST;


   public static Diplomat getMaxEnumValue() {
      Diplomat[] values = Diplomat.values();
      Diplomat maxEnumValue = values[0];
      for(int i=1; i<values.length; i++) {
         if(values[i].compareTo(maxEnumValue) > 0) {
            maxEnumValue = values[i];
         }
      }
      return maxEnumValue;
   }
}


