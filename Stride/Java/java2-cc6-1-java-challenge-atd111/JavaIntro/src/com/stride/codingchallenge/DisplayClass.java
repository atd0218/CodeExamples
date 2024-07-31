package com.stride.codingchallenge;

public class DisplayClass <T extends Pasta> {

    public String displayOutput(T t) {
		return String.format("%nThe pasta dish named %s is made with %s pasta and %s %nit is cooked by %s and takes %d minutes to make%n",
		((Pasta) t).getDishName(), ((Pasta) t).getPasta(), ((Pasta) t).getPastaSauce(), 
        t instanceof BakedZiti ? ((BakedZiti) t).getCookingMethod() : ((ChickenAlfredo) t).getCookingMethod(), 
        t instanceof BakedZiti ? ((BakedZiti) t).getCookTime() : ((ChickenAlfredo) t).getCookTime());
	}
    
}
