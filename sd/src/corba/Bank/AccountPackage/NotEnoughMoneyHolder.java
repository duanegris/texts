package Bank.AccountPackage;

/**
* Bank/AccountPackage/NotEnoughMoneyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Bank.idl
* mardi 23 octobre 2012 19 h 25 CEST
*/

public final class NotEnoughMoneyHolder implements org.omg.CORBA.portable.Streamable
{
  public Bank.AccountPackage.NotEnoughMoney value = null;

  public NotEnoughMoneyHolder ()
  {
  }

  public NotEnoughMoneyHolder (Bank.AccountPackage.NotEnoughMoney initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Bank.AccountPackage.NotEnoughMoneyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Bank.AccountPackage.NotEnoughMoneyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Bank.AccountPackage.NotEnoughMoneyHelper.type ();
  }

}