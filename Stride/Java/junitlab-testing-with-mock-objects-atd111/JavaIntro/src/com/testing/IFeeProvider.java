package com.testing;

public interface IFeeProvider {

  public void setFee(double fee);
  public double feesDue();
  public void payFee(double fee);

}
