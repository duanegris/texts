package SimpleComputation;

/**
* SimpleComputation/OperationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Operation.idl
* mardi 16 octobre 2012 15 h 55 CEST
*/

public final class OperationHolder implements org.omg.CORBA.portable.Streamable
{
  public SimpleComputation.Operation value = null;

  public OperationHolder ()
  {
  }

  public OperationHolder (SimpleComputation.Operation initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SimpleComputation.OperationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SimpleComputation.OperationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SimpleComputation.OperationHelper.type ();
  }

}