/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.fudgemsg;

import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.MutableFudgeFieldContainer;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeDeserializationContext;
import org.fudgemsg.mapping.FudgeObjectDictionary;
import org.fudgemsg.mapping.FudgeSerializationContext;

import com.opengamma.math.interpolation.GridInterpolator2D;
import com.opengamma.math.interpolation.Interpolator1D;
import com.opengamma.math.interpolation.Interpolator1DFactory;

/**
 * 
 * 
 */
public class MathInterpolation {
  
  private static final FudgeBuilder<GridInterpolator2D> GRID_INTERPOLATOR_2D = new GridInterpolator2DBuilder();
  private static final FudgeBuilder<Interpolator1D> INTERPOLATOR_1D = new Interpolator1DBuilder();
  
  private MathInterpolation() {
  }
  
  public static class GridInterpolator2DBuilder extends FudgeBuilderBase<GridInterpolator2D> {
    
    private static final String X_FIELD_NAME = "x";
    private static final String Y_FIELD_NAME = "y";
    
    private GridInterpolator2DBuilder() {
    }
    
    @Override
    public void buildMessage(final FudgeSerializationContext context, final MutableFudgeFieldContainer message, final GridInterpolator2D object) {
      context.objectToFudgeMsg(message, X_FIELD_NAME, null, object.getXInterpolator ());
      context.objectToFudgeMsg(message, Y_FIELD_NAME, null, object.getYInterpolator ());
    }
    
    @Override
    public GridInterpolator2D buildObject(final FudgeDeserializationContext context, final FudgeFieldContainer message) {
      return new GridInterpolator2D(
          context.fieldValueToObject(Interpolator1D.class, message.getByName(X_FIELD_NAME)),
          context.fieldValueToObject(Interpolator1D.class, message.getByName(Y_FIELD_NAME)));
    }
    
  }
  
  public static class Interpolator1DBuilder implements FudgeBuilder<Interpolator1D> {
    
    private static final String TYPE_FIELD_NAME = "type";
    
    private Interpolator1DBuilder() {
    }

    @Override
    public MutableFudgeFieldContainer buildMessage(FudgeSerializationContext context, Interpolator1D object) {
      final MutableFudgeFieldContainer message = context.newMessage();
      message.add(0, Interpolator1D.class.getName());
      message.add(TYPE_FIELD_NAME, Interpolator1DFactory.getInterpolatorName(object));
      return message;
    }

    @Override
    public Interpolator1D buildObject(FudgeDeserializationContext context, FudgeFieldContainer message) {
      return Interpolator1DFactory.getInterpolator(message.getFieldValue(String.class, message.getByName(TYPE_FIELD_NAME)));
    }
    
  }
  
  /* package */ static void addBuilders(final FudgeObjectDictionary dictionary) {
    dictionary.addBuilder(GridInterpolator2D.class, GRID_INTERPOLATOR_2D);
    dictionary.getDefaultBuilderFactory().addGenericBuilder(Interpolator1D.class, INTERPOLATOR_1D);
  }
  
}