package com.testngDemo;

import org.testng.Assert;

import org.testng.annotations.Test;

public class TestHelloWorld{
    @Test
    public void testEmailGenerator() {

        RandomEmailGenerator obj = new RandomEmailGenerator();
        String email = obj.generate();

        Assert.assertNotNull(email);
        Assert.assertEquals(email, "feedback@yiibai.com1");

    }


        public int strStr(String haystack, String needle) {

            int k=-1;

            int n=needle.length();
            if(n==0) return 0;

            for(int i=0;i<=haystack.length()-n;i++){
                System.out.println(haystack.substring(i,i+n));

                if(haystack.substring(i,i+n).equals(needle)){
                    k=i;
                    break;
                }
            }
            System.out.println(k);
            return k;


        }
        public  static void main(String[] args){
            //TestHelloWorld th= new TestHelloWorld();
            //th.strStr("aa","bba");

            System.out.println((char) (2));
        }


}


