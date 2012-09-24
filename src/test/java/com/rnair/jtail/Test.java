package com.rnair.jtail;

public class Test {

	private static class Person{
		
		String name;
		
		Person(String name){
			this.name = name;
		}
		
		public String name(){
			return name;
		}
	}
	public static void print(final Person p){
		Runnable r = new Runnable(){

			public void run() {
				while(true){
				System.out.println(p.name());
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			
		};
		new Thread(r).start();
	}
	public static void main(String...a){
		Person ap = new Person("Rakesh");
		Person ap2 = new Person("Nair");
		print(ap);
		ap = ap2;
		System.out.println(ap.name);
	}
}
