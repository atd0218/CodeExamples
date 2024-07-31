package exercises;

public class Coffee {

  private int temperature;

  public Coffee(int temperature) throws TooHotException {
    setTemperature(temperature);
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int num) throws TooHotException{

    if (num > 120) {
      throw new TooHotException("The Coffee is way to HOT!");
    }
    temperature = num;

  }
  
}
