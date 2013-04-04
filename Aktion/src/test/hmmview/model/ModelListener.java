package test.hmmview.model;

/**
 * A generic MVC view, or model listener.
 */
public interface ModelListener <M>
{
  void modelChanged (M model);
}
